package jobs.find.com.finddevjobs.models;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import jobs.find.com.finddevjobs.MainActivity;
import jobs.find.com.finddevjobs.db.JobOperations;

public class FindJobForMe implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String requestUrl = "http://jobs.github.com/positions.json";
    private final JobOperations operations;
    private List<JobModel> jobList = new ArrayList<>();
    private SaveJobTask saveJobTask;
    private String actualSearchKey;


    public FindJobForMe(JobOperations operations) {
        this.operations = operations;

    }


    public void searchJob(String searchKey, String location) {

        StringBuilder finalUrl = new StringBuilder();
        finalUrl.append(requestUrl);
        if (location != null) {
            finalUrl.append("?").append("location=").append(location.replace(" ", "+"));
        }
        if (searchKey != null) {
            finalUrl.append("&").append("description=").append(searchKey.replace(" ", "+"));
        }
        URL url;
        try {
            url = new URL(finalUrl.toString());
            System.out.println("finalUrl " + finalUrl.toString());
            actualSearchKey = searchKey;
            this.sendHTTPRequest(url.toString(), actualSearchKey);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    public List<JobModel> sendHTTPRequest(String url, String searchKey) {
    //    List<JobModel> list = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            request.addHeader("charset", "UTF-8");
            HttpResponse response = client.execute(request);
            response.addHeader("content-type", "application/json;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            //list = mapper.readValue(EntityUtils.toString(entity), List.class);
            jobList = mapper.readValue((EntityUtils.toString(entity)), new TypeReference<List<JobModel>>() {
            });
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (jobList != null && !jobList.isEmpty()) {
            System.out.println("Found job list size is " + jobList.size());
           // this.save(list, searchKey);
            saveJobTask = new SaveJobTask();
            saveJobTask.execute();

        }
        return jobList;
    }

//    private void save(List<JobModel> list, String searchKey) {
//        if (!list.isEmpty()) {
//            operations.open();
//            for (Iterator<JobModel> iterator = list.iterator(); iterator.hasNext(); ) {
//                JobModel entity = iterator.next();
//                if (entity.getTitle() != null && entity.getCompany() != null && entity.getLocation() != null)
//                    entity.setSearchedKey(searchKey);
//                operations.addNewJob(entity);
//            }
//            operations.close();
//        } else {
//            System.out.println("Job list is empty");
//        }
//
//    }

    class SaveJobTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                if (!jobList.isEmpty()) {
                    operations.open();
                    for (Iterator<JobModel> iterator = jobList.iterator(); iterator.hasNext(); ) {
                        JobModel entity = iterator.next();
                        if (entity.getTitle() != null && entity.getCompany() != null && entity.getLocation() != null)
                            entity.setSearchedKey(actualSearchKey);
                        operations.addNewJob(entity);
                    }
                    operations.close();
                } else {
                    System.out.println("Job list is empty");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }


}

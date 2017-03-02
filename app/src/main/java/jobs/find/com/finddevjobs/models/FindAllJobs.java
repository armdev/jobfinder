package jobs.find.com.finddevjobs.models;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jobs.find.com.finddevjobs.R;
import jobs.find.com.finddevjobs.db.JobOperations;

public class FindAllJobs extends ListActivity {

    private JobOperations operations;
    ArrayList<JobModel> jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_all_jobs);
        operations = new JobOperations(this);
        operations.open();
        jobs = operations.getJobListBySearchKey("python");
        operations.close();
        //ListView listView = (ListView)findViewById(R.id.list);
        //JobAdapter adapter = new JobAdapter(this,jobs);

        ArrayAdapter<JobModel> adapter = new ArrayAdapter<JobModel>(this,
                android.R.layout.simple_list_item_1, jobs);
        //listView.setAdapter(adapter);
        setListAdapter(adapter);

    }

    // https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
}

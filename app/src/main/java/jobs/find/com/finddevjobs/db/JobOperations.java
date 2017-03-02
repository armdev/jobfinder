package jobs.find.com.finddevjobs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jobs.find.com.finddevjobs.models.JobModel;

/**
 * Created by armenar on 2/27/2017.
 */

public class JobOperations {
    public static final String LOGTAG = "JOB_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            GithubJobModel.ID,
            GithubJobModel.CREATED,
            GithubJobModel.JOBID,
            GithubJobModel.TITLE,
            GithubJobModel.LOCATION,
            GithubJobModel.TYPE,
            GithubJobModel.DESCRIPTION,
            GithubJobModel.HOW_TO_APPLY,
            GithubJobModel.COMPANY,
            GithubJobModel.COMPANY_URL,
            GithubJobModel.COMPANY_LOGO,
            GithubJobModel.URL,
            GithubJobModel.SAVED_DATE,
            GithubJobModel.KEY
    };

    public JobOperations(Context context) {
        dbhandler = new JobDBHandler(context);
    }

    public JobOperations() {

    }

    public void open() {
        //Log.i(LOGTAG, "Database Opened");
        database = dbhandler.getWritableDatabase();
    }

    public void close() {
       // Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    public JobModel addNewJob(JobModel jobModel) {
        ContentValues values = new ContentValues();
        values.put(GithubJobModel.JOBID, jobModel.getJobId());
        values.put(GithubJobModel.CREATED, jobModel.getCreated_at());
        values.put(GithubJobModel.TITLE, jobModel.getTitle());
        values.put(GithubJobModel.LOCATION, jobModel.getLocation());
        values.put(GithubJobModel.TYPE, jobModel.getType());
        values.put(GithubJobModel.DESCRIPTION, jobModel.getDescription());
        values.put(GithubJobModel.HOW_TO_APPLY, jobModel.getHow_to_apply());
        values.put(GithubJobModel.COMPANY, jobModel.getCompany());
        values.put(GithubJobModel.COMPANY_URL, jobModel.getCompany_url());
        values.put(GithubJobModel.COMPANY_LOGO, jobModel.getCompany_logo());
        values.put(GithubJobModel.URL, jobModel.getUrl());
        values.put(GithubJobModel.SAVED_DATE, String.valueOf(new Date(System.currentTimeMillis())));
        values.put(GithubJobModel.KEY, jobModel.getSearchedKey());
        long insertid = database.insert(GithubJobModel.TABLE_NAME, null, values);
        jobModel.setId(insertid);
        return jobModel;
    }


    public JobModel getJobByJobId(String jobId) {
        Cursor cursor = database.query(GithubJobModel.TABLE_NAME, allColumns, GithubJobModel.JOBID + "=?", new String[]{String.valueOf(jobId)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        JobModel e = new JobModel(
                Long.parseLong(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getString(11),
                cursor.getString(12),
                cursor.getString(13));
        return e;
    }


    public JobModel getJobById(long id) {
        Cursor cursor = database.query(GithubJobModel.TABLE_NAME, allColumns, GithubJobModel.ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        JobModel e = new JobModel(
                Long.parseLong(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getString(11),
                cursor.getString(12),
                cursor.getString(13));
        return e;
    }

    public List<JobModel> getAllJobs() {

        Cursor cursor = database.query(GithubJobModel.TABLE_NAME, allColumns, null, null, null, null, null);

        List<JobModel> jobList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                JobModel job = new JobModel();
                job.setId(cursor.getLong(cursor.getColumnIndex(GithubJobModel.ID)));
                job.setJobId(cursor.getString(cursor.getColumnIndex(GithubJobModel.JOBID)));
                job.setCreated_at(cursor.getString(cursor.getColumnIndex(GithubJobModel.CREATED)));
                job.setTitle(cursor.getString(cursor.getColumnIndex(GithubJobModel.TITLE)));
                job.setLocation(cursor.getString(cursor.getColumnIndex(GithubJobModel.LOCATION)));
                job.setType(cursor.getString(cursor.getColumnIndex(GithubJobModel.TYPE)));
                job.setDescription(cursor.getString(cursor.getColumnIndex(GithubJobModel.DESCRIPTION)));
                job.setHow_to_apply(cursor.getString(cursor.getColumnIndex(GithubJobModel.HOW_TO_APPLY)));
                job.setCompany(cursor.getString(cursor.getColumnIndex(GithubJobModel.COMPANY)));
                job.setCompany_url(cursor.getString(cursor.getColumnIndex(GithubJobModel.COMPANY_URL)));
                job.setCompany_logo(cursor.getString(cursor.getColumnIndex(GithubJobModel.COMPANY_LOGO)));
                job.setUrl(cursor.getString(cursor.getColumnIndex(GithubJobModel.URL)));
                job.setDateInserted(cursor.getString(cursor.getColumnIndex(GithubJobModel.SAVED_DATE)));
                job.setSearchedKey(cursor.getString(cursor.getColumnIndex(GithubJobModel.KEY)));

                jobList.add(job);
            }
        }

        return jobList;
    }

    // Updating Employee
    public int updateJobByJobId(JobModel jobModel) {
        ContentValues values = new ContentValues();
        values.put(GithubJobModel.JOBID, jobModel.getJobId());
        values.put(GithubJobModel.CREATED, jobModel.getCreated_at());
        values.put(GithubJobModel.TITLE, jobModel.getTitle());
        values.put(GithubJobModel.LOCATION, jobModel.getLocation());
        values.put(GithubJobModel.TYPE, jobModel.getType());
        values.put(GithubJobModel.DESCRIPTION, jobModel.getDescription());
        values.put(GithubJobModel.HOW_TO_APPLY, jobModel.getHow_to_apply());
        values.put(GithubJobModel.COMPANY, jobModel.getCompany());
        values.put(GithubJobModel.COMPANY_URL, jobModel.getCompany_url());
        values.put(GithubJobModel.COMPANY_LOGO, jobModel.getCompany_logo());
        values.put(GithubJobModel.URL, jobModel.getUrl());
        values.put(GithubJobModel.SAVED_DATE, String.valueOf(new Date(System.currentTimeMillis())));
        values.put(GithubJobModel.KEY, jobModel.getSearchedKey());
        // updating row
        return database.update(GithubJobModel.TABLE_NAME, values,
                GithubJobModel.JOBID + "=?", new String[]{String.valueOf(jobModel.getJobId())});
    }

    public void remove(JobModel job) {

        database.delete(GithubJobModel.TABLE_NAME, GithubJobModel.JOBID + "=" + job.getJobId(), null);
    }

    public ArrayList<JobModel> getJobListBySearchKey(String searchKey) {

        String orderBy = GithubJobModel.SAVED_DATE + " DESC";

        Cursor cursor = database.query(GithubJobModel.TABLE_NAME, allColumns, GithubJobModel.KEY + "=?",
                new String[]{String.valueOf(searchKey)}, null, null, orderBy);

        ArrayList<JobModel> jobList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                JobModel job = new JobModel();
                job.setId(cursor.getLong(cursor.getColumnIndex(GithubJobModel.ID)));
                job.setJobId(cursor.getString(cursor.getColumnIndex(GithubJobModel.JOBID)));
                job.setCreated_at(cursor.getString(cursor.getColumnIndex(GithubJobModel.CREATED)));
                job.setTitle(cursor.getString(cursor.getColumnIndex(GithubJobModel.TITLE)));
               // System.out.println("Title:::: " + job.getTitle());
                job.setLocation(cursor.getString(cursor.getColumnIndex(GithubJobModel.LOCATION)));
                job.setType(cursor.getString(cursor.getColumnIndex(GithubJobModel.TYPE)));
                job.setDescription(cursor.getString(cursor.getColumnIndex(GithubJobModel.DESCRIPTION)));
                job.setHow_to_apply(cursor.getString(cursor.getColumnIndex(GithubJobModel.HOW_TO_APPLY)));
                job.setCompany(cursor.getString(cursor.getColumnIndex(GithubJobModel.COMPANY)));
                job.setCompany_url(cursor.getString(cursor.getColumnIndex(GithubJobModel.COMPANY_URL)));
                job.setCompany_logo(cursor.getString(cursor.getColumnIndex(GithubJobModel.COMPANY_LOGO)));
                job.setUrl(cursor.getString(cursor.getColumnIndex(GithubJobModel.URL)));
                job.setDateInserted(cursor.getString(cursor.getColumnIndex(GithubJobModel.SAVED_DATE)));
                job.setSearchedKey(cursor.getString(cursor.getColumnIndex(GithubJobModel.KEY)));

                jobList.add(job);
            }
        }

        return jobList;
    }

    // https://www.androidtutorialpoint.com/storage/android-sqlite-database-tutorial/
    // http://www.vogella.com/tutorials/AndroidListView/article.html

}

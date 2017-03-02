package jobs.find.com.finddevjobs;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import jobs.find.com.finddevjobs.db.JobOperations;
import jobs.find.com.finddevjobs.models.FindAllJobs;
import jobs.find.com.finddevjobs.models.FindJobForMe;

public class MainActivity extends AppCompatActivity {
    private FindJobForMe findJob;
    private JustFindJobtask justFindJobtask;
    private JobOperations operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        operations = new JobOperations(MainActivity.this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        findJob = new FindJobForMe(operations);
    }

    public void onAllClick(View v) {
        Intent i = new Intent(MainActivity.this, FindAllJobs.class);
        startActivity(i);
    }

    public void onClick(View view){
        operations.open();
        System.out.println("current list size " + operations.getAllJobs().size());
        operations.close();
        justFindJobtask = new JustFindJobtask();
        justFindJobtask.execute();
    }

    class JustFindJobtask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                findJob = new FindJobForMe(operations);
                findJob.searchJob("python", "new york");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

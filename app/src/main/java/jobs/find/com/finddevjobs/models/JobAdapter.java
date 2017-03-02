package jobs.find.com.finddevjobs.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import jobs.find.com.finddevjobs.R;

import java.util.ArrayList;

/**
 * Created by armenar on 2/28/2017.
 */

public class JobAdapter extends ArrayAdapter<JobModel> {
    public JobAdapter(Context context, ArrayList<JobModel> jobs) {
        super(context, 0, jobs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        JobModel job = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_find_all_jobs, parent, false);
        }


        return convertView;
    }


}

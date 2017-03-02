package jobs.find.com.finddevjobs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by armenar on 2/27/2017.
 */

public class JobDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myjobs.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CREATE =
            "CREATE TABLE " + GithubJobModel.TABLE_NAME + " (" + GithubJobModel.ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + GithubJobModel.JOBID + " TEXT,"
                    + GithubJobModel.CREATED + " TEXT,"
                    + GithubJobModel.TITLE + " TEXT,"
                    + GithubJobModel.LOCATION + " TEXT,"
                    + GithubJobModel.TYPE + " TEXT,"
                    + GithubJobModel.DESCRIPTION + " TEXT,"
                    + GithubJobModel.HOW_TO_APPLY + " TEXT,"
                    + GithubJobModel.COMPANY + " TEXT,"
                    + GithubJobModel.COMPANY_URL + " TEXT,"
                    + GithubJobModel.COMPANY_LOGO + " TEXT,"
                    + GithubJobModel.URL + " TEXT,"
                    + GithubJobModel.SAVED_DATE + " TEXT,"
                    + GithubJobModel.KEY + " TEXT);";

    public JobDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + GithubJobModel.TABLE_NAME);
        db.execSQL(TABLE_CREATE);
    }

}

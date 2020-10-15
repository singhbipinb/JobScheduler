package com.example.jobschedule;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Schedule(View view) {

        ComponentName componentName = new ComponentName(this, MyJobSchedulerService.class);
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NOT_ROAMING)
                .setPersisted(true)
                .setPeriodic(1000 * 60 * 15)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        int res = jobScheduler.schedule(jobInfo);

        if (res == jobScheduler.RESULT_SUCCESS)
            Toast.makeText(this, "Job Scheduled Successfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Job Scheduling Failed", Toast.LENGTH_LONG).show();

    }

    public void cancel(View view) {

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(1);
        Toast.makeText(this, "Job Cancelled", Toast.LENGTH_LONG).show();


    }
}
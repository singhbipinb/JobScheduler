package com.example.jobschedule;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

public class MyJobSchedulerService extends JobService {

    final String TAG = "JobService";
    boolean jobcan = false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

//        Toast.makeText(this,"Schedule Job Started",Toast.LENGTH_LONG).show();
        bgTask(jobParameters);

        return true;
    }

    public void bgTask(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    Log.d(TAG, "Task running: " + i);
                    if (jobcan) {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d(TAG, "Job running finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        Toast.makeText(this, "Schedule Job Stopped", Toast.LENGTH_LONG).show();
        jobcan = true;
        return true;
    }
}

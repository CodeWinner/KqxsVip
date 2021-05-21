package com.studio.bin.kqxsvip;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

public class GuideActivity extends AppCompatActivity {
    private Button btnNext;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        btnNext = findViewById(R.id.btnNext);
        progressBar = findViewById(R.id.progressBar);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                showChooseActivity();
                }

        });

    }
    public void showChooseActivity(){

            Intent intentWC = new Intent(GuideActivity.this, ChooseOptionActivity.class);
            startActivity(intentWC);

            GuideActivity.this.finish();
            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
        }
//    public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {
//
//        private String latestVersion;
//        private String currentVersion;
//        private Context context;
//        public ForceUpdateAsync(String currentVersion, Context context){
//            this.currentVersion = currentVersion;
//            this.context = context;
//        }
//
//        @Override
//        protected JSONObject doInBackground(String... params) {
//
//            try {
//                //context.getPackageName()
//                latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + context.getPackageName() + "&hl=en")
//                        .timeout(30000)
//                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//                        .referrer("http://www.google.com")
//                        .get()
//                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
//                        .first()
//                        .ownText();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return new JSONObject();
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject jsonObject) {
//            progressBar.setVisibility(View.GONE);
//            Log.i("VERSION","latestVersion : " + latestVersion);
//            Log.i("VERSION","crVersion : " + currentVersion);
//            if(latestVersion!=null){
//                Log.i("VERSION","latestVersion : " + latestVersion);
//                Log.i("VERSION","latestVersion : " + latestVersion);
//                if(!currentVersion.equalsIgnoreCase(latestVersion)){
//                            showUpdateActivity();
//                }else {
//                    showChooseActivity();
//                }
//            }
//            super.onPostExecute(jsonObject);
//        }
//
//        public void showUpdateActivity(){
//
//            Intent intentWC = new Intent(GuideActivity.this, UpdateActivity.class);
//            startActivity(intentWC);
//
//            GuideActivity.this.finish();
//            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
//        }
//
//        public void showChooseActivity(){
//
//            Intent intentWC = new Intent(GuideActivity.this, ChooseOptionActivity.class);
//            startActivity(intentWC);
//
//            GuideActivity.this.finish();
//            overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
//        }
//    }
}

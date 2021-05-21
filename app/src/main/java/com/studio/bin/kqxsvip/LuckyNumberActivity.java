package com.studio.bin.kqxsvip;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LuckyNumberActivity extends AppCompatActivity {
    private TextView txtCountProcess,txtProcess,txtDesp;
    private ProgressBar progressBarCaculatorLucky;
    private Button btnStartLucky;
    private ImageButton imageButtonContinueLucky;
    public AlertDialog.Builder alertDialogBuilder;
    public boolean isClickStart = false;
    // Analys
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);

        txtCountProcess = findViewById(R.id.txtCountProcess);
        txtProcess = findViewById(R.id.txtProcess);
        txtDesp = findViewById(R.id.txtDesp);
        progressBarCaculatorLucky = findViewById(R.id.progressBarCaculatorLucky);
        btnStartLucky = findViewById(R.id.btnStartLucky);
        imageButtonContinueLucky = findViewById(R.id.imageButtonContinueLucky);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        alertDialogBuilder = new AlertDialog.Builder(
                this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);

        btnStartLucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClickStart) {
                    isClickStart = true;

                    new RunProcess(txtCountProcess, progressBarCaculatorLucky, txtProcess, txtDesp).execute();

                } else {
                    Intent intent = new Intent(LuckyNumberActivity.this, ChooseOptionActivity.class);
                    startActivity(intent);
                    LuckyNumberActivity.this.finish();
                    overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
                }

            }
        });

        imageButtonContinueLucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RunProcess(txtCountProcess, progressBarCaculatorLucky, txtProcess, txtDesp).execute();
            }
        });

    }

    private void firebaseAnalysClick(String event, String type) {

        // [START image_view_event]
        Bundle bundle = new Bundle();
        bundle.putString("value", event);
        bundle.putString("type", type);
        mFirebaseAnalytics.logEvent(event, bundle);
        bundle.clear();
    }

    public class RunProcess extends AsyncTask<Void, Integer, Boolean> {
        private TextView txtCountProcess;
        private TextView txtDesp;
        private ProgressBar progressBarCaculatorLucky;
        private TextView txtProcess;

        public String countProcessString = "60";
        public String notifiProcess = "Xin hãy chờ...";

        public int countProcessUpdate = 0;

        public int maxvalue = 100;

        public RunProcess(TextView txtCountProcess, ProgressBar progressBarCaculator, TextView txtProcess, TextView txtDesp) {
            this.txtCountProcess = txtCountProcess;
            this.progressBarCaculatorLucky = progressBarCaculator;
            this.txtProcess = txtProcess;
            this.txtDesp = txtDesp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtCountProcess.setText(maxvalue + "");
            txtProcess.setText(notifiProcess);
            progressBarCaculatorLucky.setMax(maxvalue);
            progressBarCaculatorLucky.setProgress(0);
            imageButtonContinueLucky.setVisibility(View.GONE);
            btnStartLucky.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            int maxPrcess = maxvalue;
            final int jumpTime = 1;
            int process = jumpTime;

            for (int i = maxPrcess; i > -1; i--) {
               for (int j = 0; j < 50; j++) {
                   try {
                       publishProgress(-1, jumpTime);
                       Thread.sleep(5);

                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
                try {

                    publishProgress(i, jumpTime);
                    Thread.sleep(jumpTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            if (values[0] == -1) {
                txtDesp.setText("Thực hiện phép thử thứ : " + AcessData.CAL_NUMBER);
            } else {
                countProcessString = values[0] + "";
                txtCountProcess.setText(countProcessString);
                progressBarCaculatorLucky.setProgress(maxvalue - Integer.parseInt(countProcessString));
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {

                txtProcess.setText("Con số may mắn của bạn hôm nay là : " + AcessData.LUCKY_NUMBER);
                btnStartLucky.setText("OK");
                btnStartLucky.setVisibility(View.VISIBLE);

                firebaseAnalysClick("lucky_number_" + String.valueOf(AcessData.LUCKY_NUMBER), "lucky_number");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                SharedPreferences share = getSharedPreferences("KQXS", MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putInt("LUCKY_NUMBER", AcessData.LUCKY_NUMBER);
                editor.putString("DATE_EXE", currentDateandTime);
                editor.commit();
            }
        }
    }

}

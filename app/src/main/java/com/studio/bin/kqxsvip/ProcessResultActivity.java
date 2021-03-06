package com.studio.bin.kqxsvip;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Random;

public class ProcessResultActivity extends AppCompatActivity {
    private static final int NUM_PROCESS_MIN = 30;
    private static final int NUM_PROCESS_MAX = 36;

    private TextView txtCountProcess;
    private ProgressBar progressBarCaculator;
    private TextView txtProcess;
    private ImageButton imageButtonContinue;

    private Button btnNextResult;
    // True is Next and False is back
    private Boolean doBackOrNext = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_result);


        txtCountProcess = findViewById(R.id.txtCountProcess);
        progressBarCaculator = findViewById(R.id.progressBarCaculator);
        txtProcess = findViewById(R.id.txtProcess);
        btnNextResult = findViewById(R.id.btnNextResult);
        imageButtonContinue = findViewById(R.id.imageButtonContinue);
        new RunProcess(txtCountProcess, progressBarCaculator, txtProcess).execute();

        imageButtonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RunProcess(txtCountProcess, progressBarCaculator, txtProcess).execute();
            }
        });

        btnNextResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProcessResultActivity.this, ShowResultActivity.class);
                startActivity(intent);
                ProcessResultActivity.this.finish();
                overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_right);
            }
        });
    }

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec(command).waitFor() == 0);
    }

    public int getRandomIntInBetween(int min, int max) {

        Random r = new Random();
        return min + r.nextInt(max - min);
    }

    public int[] getListTime2s(int numMax) {
        int[] arrNum = new int[numMax];
        for (int i = 0; i < numMax; i++) {
            Random r = new Random();
            int sNum = r.nextInt(99);
            arrNum[i] = sNum;
        }
        return arrNum;
    }

    public boolean checkInArr(int number, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (number == arr[i]) {
                return true;
            }
        }
        return false;
    }

    public class RunProcess extends AsyncTask<Void, Integer, Boolean> {
        private TextView txtCountProcess;
        private ProgressBar progressBarCaculator;
        private TextView txtProcess;

        public String countProcessString = "0%";
        public String notifiProcess = "B???t ?????u t??nh to??n .";

        public int countProcessUpdate = 0;
        public int jumpTimeUpdate = 0;
        public int processUpdate = 0;

        boolean isShowAds = false;

        public RunProcess(TextView txtCountProcess, ProgressBar progressBarCaculator, TextView txtProcess) {
            this.txtCountProcess = txtCountProcess;
            this.progressBarCaculator = progressBarCaculator;
            this.txtProcess = txtProcess;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtCountProcess.setText(countProcessString);
            txtProcess.setText(notifiProcess);
            progressBarCaculator.setProgress(0);
            imageButtonContinue.setVisibility(View.GONE);
            isShowAds = false;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            int maxPrcess = getRandomIntInBetween(NUM_PROCESS_MIN, NUM_PROCESS_MAX);
            int jumpTime = maxPrcess * 1000 / 100;
            int process = jumpTime;
            int[] listTime2s = getListTime2s(5);

            for (int i = 1; i < 101; i++) {
                if (i == 99 || checkInArr(i, listTime2s) == true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {

                    publishProgress(i, jumpTime, process * i);
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
            countProcessUpdate = values[0];
            jumpTimeUpdate = values[1];
            processUpdate = values[2];
            progressBarCaculator.setMax(100);

            notifiProcess = (countProcessUpdate >= 1 && countProcessUpdate < 3) ? "B???t ?????u t??nh to??n . ."
                    : (countProcessUpdate >= 3 && countProcessUpdate < 5) ? "B???t ?????u t??nh to??n . . ."
                    : (countProcessUpdate >= 5 && countProcessUpdate < 7) ? "Xin h??y ch??? "
                    : (countProcessUpdate >= 9 && countProcessUpdate < 9) ? "Xin h??y ch??? "
                    : (countProcessUpdate >= 9 && countProcessUpdate < 11) ? "Xin h??y ch??? . "
                    : (countProcessUpdate >= 11 && countProcessUpdate < 13) ? "Xin h??y ch??? . ."
                    : (countProcessUpdate >= 13 && countProcessUpdate < 15) ? "Xin h??y ch??? . . ."
                    : (countProcessUpdate >= 15 && countProcessUpdate < 19) ? "H??y ?????m b???o k???t n???i Internet."
                    : (countProcessUpdate >= 19 && countProcessUpdate < 26) ? "????y l?? phi??n b???n VIP."
                    : (countProcessUpdate >= 26 && countProcessUpdate < 33) ? "T???c ????? g???p 4 l???n so v???i phi??n b???n th??ng th?????ng."
                    : (countProcessUpdate >= 33 && countProcessUpdate < 39) ? "Th???c hi???n app ng??y nhi???u h??n 3 l???n."
                    : (countProcessUpdate >= 39 && countProcessUpdate < 45) ? "Gi??p k???t qu??? ch??nh x??c h??n."
                    : (countProcessUpdate >= 45 && countProcessUpdate < 51) ? "H??y ?????m b???o k???t n???i Internet."
                    : (countProcessUpdate >= 51 && countProcessUpdate < 53) ? "Xin h??y ch??? "
                    : (countProcessUpdate >= 53 && countProcessUpdate < 56) ? "Xin h??y ch??? ."
                    : (countProcessUpdate >= 56 && countProcessUpdate < 59) ? "Xin h??y ch??? . ."
                    : (countProcessUpdate >= 59 && countProcessUpdate < 62) ? "Xin h??y ch??? . . ."
                    : (countProcessUpdate >= 62 && countProcessUpdate < 64) ? "Xin h??y ch??? ."
                    : (countProcessUpdate >= 64 && countProcessUpdate < 67) ? "Xin h??y ch??? . ."
                    : (countProcessUpdate >= 67 && countProcessUpdate < 69) ? "Xin h??y ch??? . . ."
                    : (countProcessUpdate >= 69 && countProcessUpdate < 72) ? "X??? l?? k???t qu??? ."
                    : (countProcessUpdate >= 72 && countProcessUpdate < 74) ? "X??? l?? k???t qu??? . ."
                    : (countProcessUpdate >= 74 && countProcessUpdate < 77) ? "?????ng b??? h??a . "
                    : (countProcessUpdate >= 77 && countProcessUpdate < 80) ? "?????ng b??? h??a . ."
                    : (countProcessUpdate >= 80 && countProcessUpdate < 82) ? "?????ng b??? h??a . . ."
                    : (countProcessUpdate >= 82 && countProcessUpdate < 85) ? "?????ng b??? h??a . "
                    : (countProcessUpdate >= 85 && countProcessUpdate < 88) ? "?????ng b??? h??a . ."
                    : (countProcessUpdate >= 88 && countProcessUpdate < 94) ? "?????ng b??? h??a . . ."
                    : (countProcessUpdate >= 94 && countProcessUpdate < 101) ? "Ho??n th??nh." : "";


            txtProcess.setText(notifiProcess);
            countProcessString = countProcessUpdate + "%";
            txtCountProcess.setText(countProcessString);

            progressBarCaculator.setProgress(countProcessUpdate);

//            if (countProcessUpdate == 10  && mInterstitialAd.isLoaded()) {
//                mInterstitialAd.show();
//            }
//            if(countProcessUpdate == 80 && mInterstitialAd_video.isLoaded()) {
//                mInterstitialAd_video.show();
//            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                countProcessString = 100 + "%";
                txtCountProcess.setText(countProcessString);
                txtProcess.setText(notifiProcess);
                AcessData.PROCESS_SHOW = 1;
                btnNextResult.setVisibility(View.VISIBLE);

            } else {
                notifiProcess = "Kh??ng th??? ho??n th??nh. Ki???m tra k???t n???i internet...";
                txtCountProcess.setText("Oops!");
                txtProcess.setText(notifiProcess);
                imageButtonContinue.setVisibility(View.VISIBLE);
                AcessData.PROCESS_SHOW = 0;

            }
        }
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}

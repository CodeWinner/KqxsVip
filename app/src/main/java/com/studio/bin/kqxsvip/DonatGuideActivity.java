package com.studio.bin.kqxsvip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Arrays;
import java.util.List;

public class DonatGuideActivity extends AppCompatActivity {

    public Button btnYesDonate,btnNoDonate;
    private TextView txtGuideDonate;
    public int CLICK_YES = 0;
    // Analys
    private FirebaseAnalytics mFirebaseAnalytics;

    private String SKU = "kqxs_binnk_1995_v.1.0.1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donat_guide);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        btnYesDonate = findViewById(R.id.btnYesDonate);
        btnNoDonate = findViewById(R.id.btnNoDonate);
        txtGuideDonate = findViewById(R.id.txtGuideDonate);


        btnNoDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAnalysClick("UPGRATE_VIP_NO", "UPGRATE_VIP");
                Intent intent = new Intent(DonatGuideActivity.this, ChooseOptionActivity.class);
                startActivity(intent);
                DonatGuideActivity.this.finish();
                overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
            }
        });

        btnYesDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (CLICK_YES == 0) {
//                    firebaseAnalysClick("UPGRATE_VIP_OK", "UPGRATE_VIP");
//                    btnYesDonate.setText("OK");
//                    txtGuideDonate.setText("Cám ơn bạn đã trả lời khảo sát !\nChúng tôi luôn lắng nghe, đồng hành cùng bạn\nTất cả ý kiến của bạn đều được chúng tôi ghi nhận\nHãy chờ các tính năng mới ở những phiên bản mới tiếp theo.\nXin cám ơn !");
//                } else {
//                    Intent intent = new Intent(DonatGuideActivity.this, ChooseOptionActivity.class);
//                    startActivity(intent);
//                    DonatGuideActivity.this.finish();
//                    overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_left);
//                }
              //  CLICK_YES ++;


                //    billingClient.launchBillingFlow(DonatGuideActivity.this, billingFlowParams).getResponseCode();


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

}

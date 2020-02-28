package com.cnst.cmrtcattendancepredictor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    float p=0;
    int h=0;
    int d=0;
    float q=0;
    float newper=0;
    TextView textView1;
    TextView textView;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         textView=findViewById(R.id.textView);
         textView1=findViewById(R.id.textView2);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7032150033140802/5085709830");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

         textView.setVisibility(View.INVISIBLE);
         textView1.setVisibility(View.INVISIBLE);



    }


    @SuppressLint("SetTextI18n")
    public void update(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();}

        EditText percentage=findViewById(R.id.per);

        EditText hours=findViewById(R.id.hours);

        EditText days=findViewById(R.id.days);

        if (percentage.equals("")){percentage.setError("ENTER PERCENTAGE");
        if (hours.equals("")){hours.setError("ENTER CLASSES HELD");
        if (days.equals("")){days.setError("ENTER NO OF DAYS");}}}else {
            if (percentage != null && hours != null && days != null) {
                p = Float.parseFloat(percentage.getText().toString());
                h = Integer.parseInt(hours.getText().toString());
                d = Integer.parseInt(days.getText().toString());
                q = ((p * h) / 100);
                float x = h + (d * 6);
                float y = (q * 100);
                newper = (y / x);
                Button button = findViewById(R.id.bees);
                button.setVisibility(View.INVISIBLE);
                String done = Float.toString(newper);
                textView.setVisibility(View.VISIBLE);
                textView1.setText(done + "%");
                textView1.setVisibility(View.VISIBLE);

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                assert inputManager != null;
                inputManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void bees(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Upon tapping okay , you will be sent to College website.there enter your username and password, by default your username and password is your rollnumber.\n There make a note of present percentage and Classes Held and return to this app.")
                .setPositiveButton("okay!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      beesopen();  // FIRE ZE MISSILES!
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void beesopen() {
        String url = "https://cmrtcerp.com/BEESERP/Login.aspx";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void pp(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("PRIVACY POLICY:");
        builder.setMessage("Our app will not collect any type of data from your device expect Android advertising to serve ads.")

                .setPositiveButton("okay!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();  // FIRE ZE MISSILES!
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

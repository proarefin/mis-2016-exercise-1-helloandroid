package com.example.arefin.myfrstapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivitty extends AppCompatActivity {


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitty);
        Button button = (Button) findViewById(R.id.btnPress);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }



        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    TextView iv = (TextView) findViewById(R.id.textView);
                    iv.setText("Hello Word");

                    EditText txt = (EditText)findViewById(R.id.txtUrl);

                    String str = txt.getText().toString();

                    Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG);

                    toast.show();

                    try {
                        connect(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });
        }


    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    public void connect(String rl) throws IOException {

        URL url = new URL(rl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {


            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

    }
}




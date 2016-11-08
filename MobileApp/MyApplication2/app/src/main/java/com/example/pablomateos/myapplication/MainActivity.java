package com.example.pablomateos.myapplication;

import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.AsyncTask;

import android.app.AlertDialog;
import android.content.ContentValues;
import java.util.List;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private class MyTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://192.168.2.60/concursotapas2/server/signup.php?name=" + params[0] + "&email=" + params[1] + "&pwd=" + params[2]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in);
                int data = isw.read();
                while (data != -1) {
                    char current = (char) data;
                    data = isw.read();

                    //System.out.print(current);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return null;
        }
    }

    Button myButton;
    EditText textName;
    EditText textPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button)findViewById(R.id.buttonCaca);
        textName = (EditText)findViewById(R.id.textName);
        textPwd = (EditText)findViewById(R.id.textPwd);

        myButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //get message from message box
                String strName = textName.getText().toString();
                String strPwd = textPwd.getText().toString();

                //check whether the msg empty or not
                if(strName.length()>0) {
                    new MyTask().execute(strName,"",strPwd);
                } else {
                    //display message if text field is empty
                    Toast.makeText(getBaseContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

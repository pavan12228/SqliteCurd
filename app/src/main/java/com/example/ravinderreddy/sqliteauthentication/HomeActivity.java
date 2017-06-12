package com.example.ravinderreddy.sqliteauthentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    String fname,pwd;
    TextView username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        Bundle bundle = getIntent().getExtras();
        Log.d("bundle",""+bundle);
        if (bundle != null) {
            username.setText(bundle.getString("fname"));
            password.setText(bundle.getString("pwd"));

        }
    }
}

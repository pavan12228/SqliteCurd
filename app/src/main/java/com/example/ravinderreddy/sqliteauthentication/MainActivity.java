package com.example.ravinderreddy.sqliteauthentication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, pwd;
    Button submit,singup;
    String spwd;
    String firstname;
    DbHandlers dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        dbHandler=new DbHandlers(getApplicationContext());
        name = (EditText) findViewById(R.id.editTextUserName);
        pwd= (EditText) findViewById(R.id.editTextPassword);
        singup= (Button) findViewById(R.id.buttonSignupAccount);
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
            }
        });


        Cursor cursor= dbHandler.retrieveData();

        if(cursor!=null){
            if(cursor.moveToFirst()) {
                do {
                    firstname = cursor.getString(cursor.getColumnIndex("firstname"));
                    spwd = cursor.getString(cursor.getColumnIndex("password"));
                }while (cursor.moveToNext());
            } }


        submit= (Button) findViewById(R.id.buttonCreateAccount);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String etname= name.getText().toString().trim();
            String etpwd= pwd.getText().toString().trim();

                if (etname.equals(firstname) && etpwd.equals(spwd)){
                    Toast.makeText(MainActivity.this, "succesfully logged in...", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    intent.putExtra("fname",firstname);
                    intent.putExtra("pwd",spwd);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Logged in failed........", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

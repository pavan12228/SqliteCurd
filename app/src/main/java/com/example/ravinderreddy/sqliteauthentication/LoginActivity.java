package com.example.ravinderreddy.sqliteauthentication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText EtUsernName, Etpwd;
    Button btnSignin, btnSinup,btnViewData;
    String Dbfirstname, Dbpwd;
    DbHandlers dbHandler;
    private int dbuserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHandler = new DbHandlers(getApplicationContext());
        btnSignin = (Button) findViewById(R.id.btn_signin);
        btnSinup = (Button) findViewById(R.id.btn_singup);
        btnViewData= (Button) findViewById(R.id.viewData);
        btnSignin.setOnClickListener(this);
        btnSinup.setOnClickListener(this);
        btnViewData.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_signin:
                EtUsernName = (EditText) findViewById(R.id.editTextUserName);
                String Sname = EtUsernName.getText().toString().trim();
                Etpwd = (EditText) findViewById(R.id.editTextPassword);
                String Spwd = Etpwd.getText().toString().trim();
                Cursor cursor = dbHandler.retrieveData();

                if (cursor.moveToFirst()) {
                    do {
                        dbuserId = cursor.getInt(cursor.getColumnIndex("User_id"));
                        Dbfirstname = cursor.getString(cursor.getColumnIndex("firstname"));
                        Dbpwd = cursor.getString(cursor.getColumnIndex("password"));
                        if (Sname.equals(Dbfirstname) && Spwd.equals(Dbpwd)) {
                            Toast.makeText(LoginActivity.this, "succesfully logged in...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                            intent.putExtra("fname", Dbfirstname);
                            intent.putExtra("pwd", Dbpwd);
                            intent.putExtra("user_id", dbuserId);
                            startActivity(intent);
                        }
                    } while (cursor.moveToNext());
                }

                /*if (Sname.equals(Dbfirstname) && Spwd.equals(Dbpwd)) {
                    Toast.makeText(LoginActivity.this, "succesfully logged in...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserDetailsActivity.class);
                    intent.putExtra("fname", Dbfirstname);
                    intent.putExtra("pwd", Dbpwd);
                    intent.putExtra("user_id", dbuserId);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Username/Password incorrect", Toast.LENGTH_LONG).show();
//                    EtUsernName.setText("");
//                    Etpwd.setText("");
                }*/
                break;
            case R.id.btn_singup:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                break;
            case R.id.viewData:
                startActivity(new Intent(getApplicationContext(), ListViewActivity.class));
                break;

        }


    }
}

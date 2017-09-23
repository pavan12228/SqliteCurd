package com.example.ravinderreddy.sqliteauthentication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ravinder Reddy on 22-07-2017.
 */

public class PasswordUpdateActivity extends AppCompatActivity {
    EditText  newpwd;
    Button updatebtn;
    DbHandlers dbHandlers;
    private String spwd;
    int dbuserId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandlers = new DbHandlers(getApplicationContext());
        setContentView(R.layout.activity_pwd__update);
        newpwd = (EditText) findViewById(R.id.cofirmpwd);
        updatebtn = (Button) findViewById(R.id.Update_pwd_btn);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHandlers.retrieveData();
                if(cursor!=null) {
                    if (cursor.moveToFirst()) {
                        do {
                            dbuserId = cursor.getInt(cursor.getColumnIndex("User_id"));
                            spwd = cursor.getString(cursor.getColumnIndex("password"));

                        } while (cursor.moveToNext());
                    }
                }


                String nPwd = newpwd.getText().toString().trim();
                dbHandlers.updateRec(dbuserId,nPwd);
                if (cursor.getCount() > 0) {
                    Toast.makeText(PasswordUpdateActivity.this, "updated password succesfully", Toast.LENGTH_SHORT).show();
                    Log.d("updated password","+id+"+":\t"+nPwd);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                } else {
                    Toast.makeText(PasswordUpdateActivity.this, "update failed!!!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}

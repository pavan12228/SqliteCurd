package com.example.ravinderreddy.sqliteauthentication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.ravinderreddy.sqliteauthentication.R.id.user_id;

public class UserDetailsActivity extends AppCompatActivity {


    TextView username, password,textView_id;
    Button updateBtnPwd, deleteBtn;
    DbHandlers dbHandlers;
    private int db_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbHandlers = new DbHandlers(getApplicationContext());
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        textView_id = (TextView) findViewById(user_id);
        Bundle bundle = getIntent().getExtras();
        Log.d("bundle", "" + bundle);
        if (bundle != null) {
            username.setText(bundle.getString("fname"));
            password.setText(bundle.getString("pwd"));
            db_id=bundle.getInt("user_id");
            textView_id.setText(""+db_id);

        }
        updateBtnPwd = (Button) findViewById(R.id.Update_pwd);
        updateBtnPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PasswordUpdateActivity.class));
            }
        });


        deleteBtn = (Button) findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHandlers.retrieveData();

                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        do {
                            db_id = cursor.getInt(cursor.getColumnIndex("User_id"));

                        } while (cursor.moveToNext());
                    }
                }
                dbHandlers.deleteData(db_id);
                    username.setText("");
                    password.setText("");
                    Log.v("deleted_id", "" + db_id );
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));


            }
        });
    }
}

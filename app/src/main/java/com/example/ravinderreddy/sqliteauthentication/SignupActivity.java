package com.example.ravinderreddy.sqliteauthentication;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    EditText name, dob, email, mobile, pass, phNum, confpass;
    Button save;
    DbHandlers db;
    DatePickerDialog datePickerDialog;
    private String date;
    private String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initViews();
        intObjects();
        initListners();

    }

    private void intObjects() {
        db = new DbHandlers(getApplicationContext());
    }

    private void initViews() {
        name = (EditText) findViewById(R.id.editTextUserName);
        email = (EditText) findViewById(R.id.editTextEmail);
        mobile = (EditText) findViewById(R.id.editTextMobileNumber);
        dob = (EditText) findViewById(R.id.editTextDob);
        pass = (EditText) findViewById(R.id.editTextPassword);
        confpass = (EditText) findViewById(R.id.editTextConfirmPassword);
        save = (Button) findViewById(R.id.buttonCreateAccount);

    }



    @TargetApi(Build.VERSION_CODES.N)
    private void datepicker() {
        Calendar calendar = Calendar.getInstance();
        final int mYear = calendar.get(Calendar.YEAR);
        final int mMonth = calendar.get(Calendar.MONTH);
        final int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(SignupActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        time = "" + i + "-" + i2 + "-" + (i1 + 1);
                        dob.setText(time);
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dob.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                datePickerDialog.show();
                return true;
            }
        });

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
            }

        });


    }




    private void initListners() {
      datepicker();


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String edfirst = name.getText().toString();
                String etemail = email.getText().toString();
                String etmobile = mobile.getText().toString();
                time = dob.getText().toString();
                String edpass = pass.getText().toString();
                String edConf = confpass.getText().toString();

                if (edfirst.length() < 4 | edfirst.length() > 8) {
                    Toast.makeText(SignupActivity.this,"Please enter username between 4 and 8  charecters long", Toast.LENGTH_SHORT).show();
                    } else if (!signupEmail(etemail)) {
                    Toast.makeText(SignupActivity.this, "please enter valid email address", Toast.LENGTH_SHORT).show();
                } else if (etmobile.length() != 10) {
                    Toast.makeText(SignupActivity.this, "please enter valid mobile number", Toast.LENGTH_SHORT).show();
                } else if (edpass.length() < 4 | edpass.length() > 10) {
                    Toast.makeText(SignupActivity.this, "please enter  password between 4 and 10  characters long", Toast.LENGTH_SHORT).show();
                } else if (!edpass.equals(edConf)) {
                    Toast.makeText(SignupActivity.this, "password not match!!!", Toast.LENGTH_SHORT).show();
                }
                 else
                 {
                     ModelClass modelClass=new ModelClass();
                     modelClass.setName(edfirst);
                     modelClass.setEmail(etemail);
                     modelClass.setMobilenum(etmobile);
                     modelClass.setDob(time);
                     modelClass.setPwd(edpass);
                     db.insert(modelClass);
                     Cursor cursor= db.retrieveData();
                     Log.d("count",""+cursor.getCount());
                     if (cursor.getCount()>0){
                         Toast.makeText(SignupActivity.this, "Succesfully created account!!!", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                     }
                }
            }


        });

    }

    public boolean signupEmail(String sEmail) {

        String emailPattern =
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(sEmail);

        return matcher.matches();

    }

}

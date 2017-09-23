package com.example.ravinderreddy.sqliteauthentication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravinder Reddy on 23-07-2017.
 */

public class ListViewActivity extends AppCompatActivity
{
    ListView listView;
    CustomBaseAdapter customBaseAdapter;
    DbHandlers dbHandlers;
    List<ModelClass> modelClassList=new ArrayList<>();
    private String Dbfirstname,Dbpwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dbHandlers=new DbHandlers(getApplicationContext());
        listView= (ListView) findViewById(R.id.lvitems);
        Cursor cursor = dbHandlers.retrieveData();

        if (cursor.moveToFirst()) {
            do {
               // dbuserId = cursor.getInt(cursor.getColumnIndex("User_id"));
                Dbfirstname = cursor.getString(cursor.getColumnIndex("firstname"));
                Dbpwd = cursor.getString(cursor.getColumnIndex("mobile"));
                ModelClass modelClass=new ModelClass();
                modelClass.setName(Dbfirstname);
                modelClass.setMobilenum(Dbpwd);
                modelClassList.add(modelClass);


            } while (cursor.moveToNext());
        }

        customBaseAdapter=new CustomBaseAdapter(getApplicationContext(),modelClassList);
        listView.setAdapter(customBaseAdapter);
    }
}

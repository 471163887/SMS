package com.example.fury.sqlite.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class ShowStudent extends ActionBarActivity {

    private SQLiteDatabase db;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);
        DatabaseHelper dbHelper= new DatabaseHelper(ShowStudent.this,
                "stu_manager.db", null, 1);
        db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.show_student_listview);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("刷新");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {
                if (id == 0) {
                    Intent intent = new Intent(ShowStudent.this, ShowStudent.class);
                    startActivity(intent);
                    finish();
                }
            }

        });

        Cursor cursor = db.query("student",null, null, null, null, null, "snum ASC");
        if(cursor.moveToFirst()){
            for(int i=0;i<cursor.getCount();i++){
                cursor.moveToPosition(i);

                String Snum=cursor.getString(0);
                String Sclass=cursor.getString(1);
                String Sname=cursor.getString(2);
                String Ssex=cursor.getString(3);
                String Sphone=cursor.getString(4);

                int age = cursor.getInt(5);
                String fommat = sprintfStudent(Snum,Sclass,Sname,Ssex,Sphone,age);
                dataList.add(fommat);
            }
        } else {
            Toast.makeText(ShowStudent.this, "暂无学生信息", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        db.close();
    }
    static public String sprintfStudent(String snum, String sclass, String sname,
                                 String ssex,String sphone,int sage){
        String s;
        s = String.format("%-15s|%-15s|%-15s\n%-15s|%-15s|%-15s",
                snum,sclass,sname,ssex,sphone,sage);
        return s;
    }
}

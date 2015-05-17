package com.example.fury.sqlite.Activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

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
        Log.d("nimeiya", "我要打开数据库了哦！");
        DatabaseHelper dbHelper= new DatabaseHelper(ShowStudent.this,
                "stu_manager.db", null, 1);
        db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.show_student_listview);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("哈喽");

        Log.d("nimeiya","我要对数据库排序了哦！");
        //String sortStudent = "select * from Student order by Snum asc";
        //db.execSQL(sortStudent);
        Cursor cursor = db.query("student",null, null, null, null, null, "snum ASC");
        //判断游标是否为空
        if(cursor.moveToFirst()){
            //遍历游标
            for(int i=0;i<cursor.getCount();i++){
                //cursor.move(i);
                cursor.moveToPosition(i);
                String Snum=cursor.getString(0);
                String Sclass=cursor.getString(1);
                String Sname=cursor.getString(2);
                String Ssex=cursor.getString(3);
                String Sphone=cursor.getString(4);
                int age = cursor.getInt(5);

                String fommat = sprintfStudent(Snum,Sclass,Sname,Ssex,Sphone,age);
                Log.d("nimeiya",fommat);
                dataList.add(fommat);
            }
        }
        Log.d("nimeiya","执行OK！");
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

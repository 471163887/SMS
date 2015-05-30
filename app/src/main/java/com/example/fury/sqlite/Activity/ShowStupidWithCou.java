package com.example.fury.sqlite.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class ShowStupidWithCou extends ActionBarActivity {
    private SQLiteDatabase db;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stupid_with_cou);

        DatabaseHelper dbHelper= new DatabaseHelper(this,
                "stu_manager.db", null, 1);
        db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.stupid_listview);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("哈喽");

        String InnerJoin = "select  student.sname,  scores.student_num, scores.course_num," +
                " scores.score, courses.sname"+
                " from student inner join scores " +
                " on student.snum = scores.student_num" +
                " inner join courses " +
                " on courses.cnum = scores.course_num" +
                " order by course_num asc";

        Cursor cursor= db.rawQuery(InnerJoin, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String sname = cursor.getString(0);
                String course_num = cursor.getString(2);
                String score = cursor.getString(3);
                String cname = cursor.getString(4);

                int failLine = Integer.parseInt(score);
                if (failLine < 60) {
                    String format = sprintfLoser(course_num, cname, sname);
                    dataList.add(format);
                }
                cursor.moveToNext();
            }
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        db.close();
    }
    static public String sprintfLoser(String s1,String s2,String s3){
        String s;
        s = String.format("%-15s|%15s     |%15s", s1,s2,s3);
        return s;
    }
}

package com.example.fury.sqlite.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class ShowScoWithCouCla extends ActionBarActivity {
    private SQLiteDatabase db;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sco_with_cou_cla);

        DatabaseHelper dbHelper= new DatabaseHelper(this,
                "stu_manager.db", null, 1);
        db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.cla_cou_listview);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("哈喽");

        String InnerJoin = "select  student.sclass, scores.course_num,  scores.score, scores.student_num" +
                " from student inner join scores " +
                " on student.snum = scores.student_num" +
                " order by course_num asc, student.sclass asc";

        Cursor cursor= db.rawQuery(InnerJoin, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String sclass = cursor.getString(0);
                String course_num = cursor.getString(1);
                String score = cursor.getString(2);
                Log.d("nimeiya 班级：",sclass);
                Log.d("nimeiya 课程号：",course_num);
                Log.d("nimeiya 分数：",score);
                String format = sprintfScoWithCouCla(sclass,course_num,score);
                dataList.add(format);
                cursor.moveToNext();
            }
        }

        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        db.close();
    }
    static public String sprintfScoWithCouCla(String sclass,String course_num,String socre){
        String s;
        s = String.format("%-15s|%15s     |%15s", sclass,course_num,socre);
        return s;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_sco_with_cou_cla, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

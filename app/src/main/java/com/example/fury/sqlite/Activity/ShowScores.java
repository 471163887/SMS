package com.example.fury.sqlite.Activity;

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
import android.widget.ListView;
import android.widget.Toast;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class ShowScores extends ActionBarActivity {
    private SQLiteDatabase db;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_scores);

        DatabaseHelper dbHelper= new DatabaseHelper(ShowScores.this,
                "stu_manager.db", null, 1);
        db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.show_scores_listview);
        Log.d(getPackageName(), listView != null ? "listView is not null!" : "listView is null!");
        adapter = new ArrayAdapter<String>(ShowScores.this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("刷新");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {
                if (id == 0) {
                    Intent intent = new Intent(ShowScores.this, ShowScores.class);
                    startActivity(intent);
                    finish();
                }
            }

        });

        Cursor cursor = db.query("Scores",null, null, null, null, null, "course_num ASC, student_num ASC");

        if(cursor.moveToFirst()){
            for(int i=0;i<cursor.getCount();i++){
                cursor.moveToPosition(i);
                String snum=cursor.getString(0);
                String cnum=cursor.getString(1);
                String score= cursor.getString(2);
                String fommat = sprintfScores(snum, cnum, score);
                dataList.add(fommat);
            }
        }else {
            Toast.makeText(ShowScores.this, "暂无成绩信息", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        db.close();
    }
    static public String sprintfScores(String cnum, String cname,String ccredit){
        String s;
        s = String.format("%-15s|%-15s|%15s",cnum,cname,ccredit);
        return s;
    }
}

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
        dataList.add("哈喽");
        adapter.notifyDataSetChanged();
        listView.setSelection(0);

        Cursor cursor = db.query("Scores",null, null, null, null, null, "course_num ASC");

        if(cursor.moveToFirst()){
            for(int i=0;i<cursor.getCount();i++){

                //cursor.move(i);
                cursor.moveToPosition(i);
                String snum=cursor.getString(0);
                String cnum=cursor.getString(1);
                String score= cursor.getString(2);
                String fommat = sprintfScores(snum, cnum, score);
                Log.d("nimeiya", fommat);
                dataList.add(fommat);
            }
        }
        db.close();
    }
    static public String sprintfScores(String cnum, String cname,String ccredit){
        String s;
        s = String.format("%-15s|%-15s|%15s",cnum,cname,ccredit);
        return s;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_scores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
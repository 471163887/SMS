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

public class ShowCoureses extends ActionBarActivity {
    private SQLiteDatabase db;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_coureses);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        DatabaseHelper dbHelper= new DatabaseHelper(ShowCoureses.this,
                "stu_manager.db", null, 1);
        db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.show_courses_listview);
        Log.d(getPackageName(), listView != null ? "listView is not null!" : "listView is null!");
        adapter = new ArrayAdapter<String>(ShowCoureses.this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("刷新");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {
                if (id == 0) {
                    Log.d("nimeiya", "可以点不？");
                    Intent intent = new Intent(ShowCoureses.this, ShowCoureses.class);
                    startActivity(intent);
                    finish();
                }
            }

        });

        Cursor cursor = db.query("Courses",null, null, null, null, null, "Cnum ASC");

        if(cursor.moveToFirst()){
            for(int i=0;i<cursor.getCount();i++){
                cursor.moveToPosition(i);
                String cnum=cursor.getString(0);
                String cname=cursor.getString(1);
                int ccredit = cursor.getInt(2);
                String fommat = sprintfCourses(cnum, cname, ccredit);
                Log.d("nimeiya", fommat);
                dataList.add(fommat);
            }
        } else {
            Toast.makeText(ShowCoureses.this, "暂无课程信息", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        db.close();
    }
    static public String sprintfCourses(String cnum, String cname,int ccredit){
        String s;
        s = String.format("%-15s|%-15s|%15s",cnum,cname,ccredit);
        return s;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_coureses, menu);
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

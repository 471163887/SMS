package com.example.fury.sqlite.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fury.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class DataStatisticsActivity extends ActionBarActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_statistics);

        listView = (ListView) findViewById(R.id.staticties_listview);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("1.查看所有学生信息");
        dataList.add("2.查看所有课程信息");
        dataList.add("3.查看成绩表");
        dataList.add("4.按班级+课程排序学生表 成绩表");
        dataList.add("5.按课程+班级排序学生表 输出成绩表");
        dataList.add("6.按班级，输出不及格名单");
        dataList.add("7.按课程，输出补考名单");
        adapter.notifyDataSetChanged();
        listView.setSelection(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {

                if (id == 0) {
                    Log.d("nimeiya", "可以点不？");
                    Intent intent = new Intent(DataStatisticsActivity.this, ShowStudent.class);
                    startActivity(intent);
                } else if (id == 1) {
                    Log.d("nimeiya", "111111？");
                    Intent intent = new Intent(DataStatisticsActivity.this, ShowCoureses.class);
                    startActivity(intent);
                } else if (id == 2) {
                    Intent intent = new Intent(DataStatisticsActivity.this, ShowScores.class);
                    startActivity(intent);
                } else if (id == 3) {
                    Intent intent = new Intent(DataStatisticsActivity.this, ShowScoWithClaCou.class);
                    startActivity(intent);
                } else if (id == 4) {
                    Intent intent = new Intent(DataStatisticsActivity.this, ShowScoWithCouCla.class);
                    startActivity(intent);
                } else if (id == 5) {
                    Intent intent = new Intent(DataStatisticsActivity.this, ShowLoserWithCla.class);
                    startActivity(intent);
                } else if (id == 6) {
                    Intent intent = new Intent(DataStatisticsActivity.this, ShowStupidWithCou.class);
                    startActivity(intent);
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_statistics, menu);
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

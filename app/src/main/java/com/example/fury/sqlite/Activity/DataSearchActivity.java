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

public class DataSearchActivity extends ActionBarActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_search);

        listView = (ListView) findViewById(R.id.list_view);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("1.通过学生学号来查询 学生的基本信息、成绩信息");
        dataList.add("2.通过学生姓名来查询 学生的基本信息、成绩信息");
        dataList.add("3.通过课程号来查询 课程的信息、已选了该课程的学生信息");
        dataList.add("4.通过课程名来查询 课程的信息、已选了该课程的学生信息");
        adapter.notifyDataSetChanged();
        listView.setSelection(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {

                if (id == 0) {
                    Intent intent = new Intent(DataSearchActivity.this, InfWithStuNum.class);
                    startActivity(intent);
                } else if (id == 1) {
                    Intent intent = new Intent(DataSearchActivity.this, InfWithStuName.class);
                    startActivity(intent);
                } else if (id == 2) {
                    Intent intent = new Intent(DataSearchActivity.this, InfWithCouNum.class);
                    startActivity(intent);
                } else if (id == 3) {
                    Intent intent = new Intent(DataSearchActivity.this, InfWithCouName.class);
                    startActivity(intent);
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_search, menu);
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

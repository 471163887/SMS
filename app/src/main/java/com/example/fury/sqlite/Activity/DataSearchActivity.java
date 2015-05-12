package com.example.fury.sqlite.Activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

    private int currentLevel;
    public static final int LEVEL_SEARCH = 0;
    public static final int LEVEL_STUDENT = 1;
    public static final int LEVEL_COURSE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_search);

        listView = (ListView) findViewById(R.id.list_view);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("1.按学生学号查找 学生基本信息、成绩信息");
        dataList.add("2.按学生姓名查找 学生基本信息、成绩信息");
        dataList.add("3.按课程编号查找 课程信息、选课学生信息");
        dataList.add("4.按课程名查找   课程信息、选课学生信息");
        adapter.notifyDataSetChanged();
        listView.setSelection(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {

                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
					/*
					String provinceCode = provinceList.get(position).getProvinceCode();
					Intent intent2 = new Intent(ChooseAreaActivity.this, WeatherActivity.class);
					intent2.putExtra("provinceCode", provinceCode);
					startActivity(intent2);
					finish();
					return;*/
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(position);
                    queryCounties();
                } else if (currentLevel == LEVEL_COUNTY) {
                    String countyCode = countyList.get(position).getCountyCode();
                    Intent intent = new Intent(ChooseAreaActivity.this, WeatherActivity.class);
                    intent.putExtra("county_code", countyCode);
                    startActivity(intent);
                    finish();
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

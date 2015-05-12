package com.example.fury.sqlite.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.fury.sqlite.Fragment.AddCourseFragment;
import com.example.fury.sqlite.Fragment.AddScoreFragment;
import com.example.fury.sqlite.Fragment.AddStuFragment;

import com.example.fury.sqlite.R;

public class MtnDataInsert extends ActionBarActivity implements View.OnClickListener {

    private Button addStu;
    private Button addCourse;
    private Button addScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtn_data_insert);

        addStu = (Button) findViewById(R.id.btn_std);
        addStu.setOnClickListener(MtnDataInsert.this);
        addCourse = (Button)findViewById(R.id.btn_course);
        addCourse.setOnClickListener(MtnDataInsert.this);
        addScore = (Button)findViewById(R.id.btn_score);
        addScore.setOnClickListener(MtnDataInsert.this);
        Log.d("nimeiya","hi");


    }
    public void onClick(View v)
    {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction ft = fm.beginTransaction();
        Log.d("nimeiya","hi2");
        switch (v.getId())
        {
            case R.id.btn_std:
                AddStuFragment addStuFragment = new AddStuFragment(MtnDataInsert.this);
                ft.replace(R.id.add_std, addStuFragment);
                Log.d("nimeiya", "hi3");
                // 使用当前Fragment的布局替代id_content的控件
                //transaction.replace(R.id.id_content, mWeixin);
                break;
            case R.id.btn_course:
                AddCourseFragment addCourseFragment = new AddCourseFragment(MtnDataInsert.this);
                ft.replace(R.id.add_std, addCourseFragment);
                Log.d("nimeiya", "hi4");
                //transaction.replace(R.id.id_content, mFriend);
                break;
            case R.id.btn_score:
                AddScoreFragment addScoreFragment = new AddScoreFragment(MtnDataInsert.this);
                ft.replace(R.id.add_std, addScoreFragment);
                Log.d("nimeiya", "hi5");
                //transaction.replace(R.id.id_content, mFriend);
                break;
        }
        // transaction.addToBackStack();
        // 事务提交
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mtn_data_insert, menu);
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

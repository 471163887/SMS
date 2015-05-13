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
import com.example.fury.sqlite.Fragment.DeleteFragment;
import com.example.fury.sqlite.R;

public class MtnDataDelete extends ActionBarActivity implements View.OnClickListener {

    private Button deleteStudent;
    private Button deleteCourse;
    private Button deleteScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtn_data_delete);

        deleteStudent = (Button)findViewById(R.id.btn_std);
        deleteStudent.setOnClickListener(MtnDataDelete.this);
        deleteCourse = (Button)findViewById(R.id.btn_course);
        deleteCourse.setOnClickListener(MtnDataDelete.this);
        deleteScore = (Button)findViewById(R.id.btn_score);
        deleteScore.setOnClickListener(MtnDataDelete.this);
    }
    public void onClick(View v)
    {
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction ft = fm.beginTransaction();
        Log.d("nimeiya", "hi2");

        switch (v.getId())
        {
            case R.id.btn_std:
                DeleteFragment deleteFragment = new DeleteFragment(MtnDataDelete.this,0);
                ft.replace(R.id.del_frag, deleteFragment);
                Log.d("nimeiya", "hi3");
                // 使用当前Fragment的布局替代id_content的控件
                //transaction.replace(R.id.id_content, mWeixin);
                break;
            case R.id.btn_course:
                DeleteFragment deleteFragment1 = new DeleteFragment(MtnDataDelete.this,1);
                ft.replace(R.id.del_frag, deleteFragment1);
                Log.d("nimeiya", "hi4");
                break;
            case R.id.btn_score:
                DeleteFragment deleteFragment2 = new DeleteFragment(MtnDataDelete.this,2);
                ft.replace(R.id.del_frag, deleteFragment2);
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
        getMenuInflater().inflate(R.menu.menu_mtn_data_delete, menu);
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

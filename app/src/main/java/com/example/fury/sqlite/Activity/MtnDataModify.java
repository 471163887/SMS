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

import com.example.fury.sqlite.Fragment.DeleteFragment;
import com.example.fury.sqlite.Fragment.ModifyFragment;
import com.example.fury.sqlite.R;

public class MtnDataModify extends ActionBarActivity implements View.OnClickListener {
    private Button modifyStudent;
    private Button modifyCourse;
    private Button modifyScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtn_data_modify);

        modifyStudent = (Button)findViewById(R.id.btn_std);
        modifyStudent.setOnClickListener(MtnDataModify.this);
        modifyCourse = (Button)findViewById(R.id.btn_course);
        modifyCourse.setOnClickListener(MtnDataModify.this);
        modifyScore = (Button)findViewById(R.id.btn_score);
        modifyScore.setOnClickListener(MtnDataModify.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mtn_data_modify, menu);
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

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
        // ¿ªÆôFragmentÊÂÎñ
        FragmentTransaction ft = fm.beginTransaction();
        Log.d("nimeiya", "hi2");
        switch (v.getId())
        {
            case R.id.btn_std:
                ModifyFragment modifyFragment = new ModifyFragment(MtnDataModify.this,0);
                ft.replace(R.id.modify_frag, modifyFragment);
                Log.d("nimeiya", "hi3");
                break;
            case R.id.btn_course:
                ModifyFragment modifyFragment1 = new ModifyFragment(MtnDataModify.this,1);
                ft.replace(R.id.modify_frag, modifyFragment1);
                Log.d("nimeiya", "hi4");
                break;
            case R.id.btn_score:
                ModifyFragment modifyFragment2 = new ModifyFragment(MtnDataModify.this,2);
                ft.replace(R.id.modify_frag, modifyFragment2);
                Log.d("nimeiya", "hi5");
                break;
        }
        ft.commit();
    }
}

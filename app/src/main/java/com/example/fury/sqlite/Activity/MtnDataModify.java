package com.example.fury.sqlite.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import com.example.fury.sqlite.Fragment.ModifyFragment;
import com.example.fury.sqlite.R;

public class MtnDataModify extends TabActivity implements View.OnClickListener {
    private Button modifyStudent;
    private Button modifyCourse;
    private Button modifyScore;

    private TabHost tabHost;

    private Intent showStudent;
    private Intent showCoureses;
    private Intent showScores;
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

        tabHost = getTabHost();
        initIntent();
        addSpec();
    }
    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();
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
    private void initIntent() {
        showStudent = new Intent(this, ShowStudent.class);
        showCoureses = new Intent(this, ShowCoureses.class);
        showScores = new Intent(this, ShowScores.class);
    }
    private void addSpec() {
        tabHost.addTab(this.buildTagSpec("tab_student",
                R.string.show_student,R.drawable.add, showStudent)); //
        tabHost.addTab(this.buildTagSpec("tab_coureses",
                R.string.show_coureses, R.drawable.delete, showCoureses));
        tabHost.addTab(this.buildTagSpec("tab_scores",
                R.string.show_Scores, R.drawable.query, showScores));
    }
    private TabHost.TabSpec buildTagSpec(String tagName, int tagLable,
                                         int icon, Intent content) {
        return tabHost
                .newTabSpec(tagName)
                .setIndicator(getResources().getString(tagLable),
                        getResources().getDrawable(icon)).setContent(content);
    }
}

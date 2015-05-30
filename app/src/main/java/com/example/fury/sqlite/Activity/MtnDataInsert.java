package com.example.fury.sqlite.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;

import android.widget.TabHost;

import com.example.fury.sqlite.Fragment.AddCourseFragment;
import com.example.fury.sqlite.Fragment.AddScoreFragment;
import com.example.fury.sqlite.Fragment.AddStuFragment;

import com.example.fury.sqlite.R;

public class MtnDataInsert extends TabActivity implements View.OnClickListener {

    private TabHost tabHost;

    private Intent showStudent;
    private Intent showCoureses;
    private Intent showScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtn_data_insert);

        Button addStu = (Button) findViewById(R.id.btn_std);
        addStu.setOnClickListener(MtnDataInsert.this);
        Button addCourse = (Button) findViewById(R.id.btn_course);
        addCourse.setOnClickListener(MtnDataInsert.this);
        Button addScore = (Button) findViewById(R.id.btn_score);
        addScore.setOnClickListener(MtnDataInsert.this);

        tabHost = getTabHost();
        initIntent();
        addSpec();


    }
    public void onClick(View v)
    {
        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId())
        {
            case R.id.btn_std:
                AddStuFragment addStuFragment = new AddStuFragment(MtnDataInsert.this);
                ft.replace(R.id.add_std, addStuFragment);
                break;
            case R.id.btn_course:
                AddCourseFragment addCourseFragment = new AddCourseFragment(MtnDataInsert.this);
                ft.replace(R.id.add_std, addCourseFragment);
                break;
            case R.id.btn_score:
                AddScoreFragment addScoreFragment = new AddScoreFragment(MtnDataInsert.this);
                ft.replace(R.id.add_std, addScoreFragment);
                break;
        }
        // transaction.addToBackStack();
        ft.commit();
    }
    private void initIntent() {
        showStudent = new Intent(this, ShowStudent.class);
        showCoureses = new Intent(this, ShowCoureses.class);
        showScores = new Intent(this, ShowScores.class);
    }
    private void addSpec() {
        tabHost.addTab(this.buildTagSpec("tab_student",
                R.string.show_student,R.drawable.add, showStudent));
        tabHost.addTab(this.buildTagSpec("tab_coureses",
                R.string.show_coureses,R.drawable.delete, showCoureses));
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

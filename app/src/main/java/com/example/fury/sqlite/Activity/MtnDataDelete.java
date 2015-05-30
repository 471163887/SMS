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

import com.example.fury.sqlite.Fragment.DeleteFragment;
import com.example.fury.sqlite.R;

public class MtnDataDelete extends TabActivity implements View.OnClickListener {

    private Button deleteStudent;
    private Button deleteCourse;
    private Button deleteScore;

    private TabHost tabHost;

    private Intent showStudent;
    private Intent showCoureses;
    private Intent showScores;

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
                DeleteFragment deleteFragment = new DeleteFragment(MtnDataDelete.this,0);
                ft.replace(R.id.del_frag, deleteFragment);
                break;
            case R.id.btn_course:
                DeleteFragment deleteFragment1 = new DeleteFragment(MtnDataDelete.this,1);
                ft.replace(R.id.del_frag, deleteFragment1);
                break;
            case R.id.btn_score:
                DeleteFragment deleteFragment2 = new DeleteFragment(MtnDataDelete.this,2);
                ft.replace(R.id.del_frag, deleteFragment2);
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
                R.string.show_student,R.drawable.add, showStudent));
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
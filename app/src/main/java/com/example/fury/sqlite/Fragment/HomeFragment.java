package com.example.fury.sqlite.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fury.sqlite.Activity.DataMaintenanceActivity;
import com.example.fury.sqlite.Activity.DataSearchActivity;
import com.example.fury.sqlite.Activity.DataStatisticsActivity;
import com.example.fury.sqlite.Activity.WelcomeActivity;
import com.example.fury.sqlite.MainActivity;
import com.example.fury.sqlite.R;
import com.example.fury.sqlite.util.RefreshableListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private View currentView;
    private LinearLayout openMenu;
    private RefreshableListView mListView;
    private View listHeaderView;
    private ImageView head_pic;

    private TextView student_inf;
    private TextView student_course;
    private TextView student_score;
    private TextView returnWelcome;
    public HomeFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.fragment_home,
                container, false);
        mListView = (RefreshableListView) currentView
                .findViewById(R.id.mineList);
        openMenu = (LinearLayout) currentView
                .findViewById(R.id.linear_above_toHome);
        listHeaderView = getActivity().getLayoutInflater().inflate(
                R.layout.home_head_view, null);

        head_pic = (ImageView) listHeaderView
                .findViewById(R.id.iv_home_head);
        openMenu.setOnClickListener(this);
        student_inf = (TextView)currentView.findViewById(R.id.student_inf);
        student_course = (TextView)currentView.findViewById(R.id.student_course);
        student_score = (TextView)currentView.findViewById(R.id.student_score);
        returnWelcome = (TextView)currentView.findViewById(R.id.retrunwelcome);
        student_inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataSearchActivity.class);
                startActivity(intent);
            }
        });
        student_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataStatisticsActivity.class);
                startActivity(intent);
            }
        });
        student_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataMaintenanceActivity.class);
                startActivity(intent);
            }
        });
        returnWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
            }
        });

        setListener();
        return currentView;
    }
    public void setListener() {



        head_pic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.linear_above_toHome):
                openMenu.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        final SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) getActivity()
                                .findViewById(R.id.slidingpanellayout);
                        if (slidingPaneLayout.isOpen()) {
                            slidingPaneLayout.closePane();
                        } else {
                            slidingPaneLayout.openPane();
                        }
                    }
                });

                break;
        }
    }
}

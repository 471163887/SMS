package com.example.fury.sqlite.Fragment;


import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fury.sqlite.Activity.HomePageActivity;
import com.example.fury.sqlite.R;
import com.example.fury.sqlite.util.Tools;

/**
 * A simple {@link Fragment} subclass.

public class MenuFragment extends Fragment  {

    private View currentView;
    private ImageView iv_login;
    private Button bt_abouts, bt_gift, bt_home, bt_invitation, bt_orders, bt6;
    private Drawable head_iocn;

    public MenuFragment() {

    }
    public View getCurrentView() {
        return currentView;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}*/
public class MenuFragment extends Fragment implements View.OnClickListener {

    private View currentView;
    private ImageView iv_login;
    private Button bt_abouts, bt_gift, bt_home, bt_invitation, bt_orders, bt6;
    private Drawable head_iocn;

    public View getCurrentView() {
        return currentView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        currentView = inflater.inflate(R.layout.fragment_menu,
                container, false);
        bt_abouts = (Button) currentView.findViewById(R.id.btn_abouts);
        bt_gift = (Button) currentView.findViewById(R.id.btn_gift);
        bt_home = (Button) currentView.findViewById(R.id.btn_home);
        bt_invitation = (Button) currentView.findViewById(R.id.btn_invitation);
        iv_login = (ImageView) currentView.findViewById(R.id.iv_login);
        //initRoundImage();
        bt_orders = (Button) currentView.findViewById(R.id.btn_order);
        bt_abouts.setOnClickListener(this);
        bt_gift.setOnClickListener(this);
        bt_home.setOnClickListener(this);
        bt_invitation.setOnClickListener(this);
        bt_orders.setOnClickListener(this);
        iv_login.setOnClickListener(this);
        return currentView;
    }

    @SuppressWarnings("deprecation")
    private void initRoundImage() {
        Tools tools = new Tools();
        iv_login.setBackgroundDrawable(new BitmapDrawable(tools.toRoundBitmap(getActivity(), "ali_head.jpg")));
        iv_login.getBackground().setAlpha(0);
        iv_login.setImageBitmap(tools.toRoundBitmap(getActivity(), "ali_head.jpg"));
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        FragmentTransaction ft = getFragmentManager().beginTransaction();// 开始一个事物
        switch (v.getId()) {
            case R.id.btn_home:

                break;
            case R.id.btn_order:

                break;
            case R.id.btn_gift:

                break;
            case R.id.btn_invitation:

                break;
            case R.id.btn_abouts:

                break;
            case R.id.iv_login:

                break;
        }
        ((HomePageActivity) getActivity()).getSlidingPaneLayout().closePane();
    }
}

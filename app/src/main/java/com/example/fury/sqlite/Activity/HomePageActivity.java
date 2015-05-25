package com.example.fury.sqlite.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.fury.sqlite.Fragment.HomeFragment;
import com.example.fury.sqlite.Fragment.MenuFragment;
import com.example.fury.sqlite.R;
import com.example.fury.sqlite.util.InjectView;
import com.example.fury.sqlite.util.Injector;

import android.support.v4.widget.SlidingPaneLayout.PanelSlideListener;

public class HomePageActivity extends Activity {
    @InjectView(R.id.slidingpanellayout)
    private SlidingPaneLayout slidingPaneLayout;
    private MenuFragment menuFragment;
    private HomeFragment contentFragment;
    private DisplayMetrics displayMetrics = new DisplayMetrics();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        setContentView(R.layout.activity_home_page);
        Injector.get(this).inject();

        menuFragment = new MenuFragment();
        contentFragment = new HomeFragment();
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.slidingpane_menu, menuFragment);
        transaction.replace(R.id.slidingpane_content, contentFragment);
        transaction.commit();
        slidingPaneLayout.setPanelSlideListener(new PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
            }
            @Override
            public void onPanelOpened(View panel) {
            }
            @Override
            public void onPanelClosed(View panel) {
            }
        });
    }
    /**
     * @return the slidingPaneLayout
     */
    public SlidingPaneLayout getSlidingPaneLayout() {
        return slidingPaneLayout;
    }
}

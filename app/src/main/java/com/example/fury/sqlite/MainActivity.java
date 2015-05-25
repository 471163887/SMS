package com.example.fury.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.example.fury.sqlite.Activity.DataMaintenanceActivity;
import com.example.fury.sqlite.Activity.DataSearchActivity;
import com.example.fury.sqlite.Activity.DataStatisticsActivity;
import com.example.fury.sqlite.util.InjectView;
import com.example.fury.sqlite.util.Injector;

public class MainActivity extends Activity {

    private Button maintenance = null;
    private Button search = null;
    private Button statistics = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//-------------------------跳转按键--------------------------------------------------
        maintenance = (Button)findViewById(R.id.To_DataMaintenance);
        maintenance.setOnClickListener(new MaintenanceOnClickListener());
        search = (Button)findViewById(R.id.To_DataSearch);
        search.setOnClickListener(new SearchOnClickListener());
        statistics = (Button)findViewById(R.id.To_DataStatistics);
        statistics.setOnClickListener(new StatisticsOnClickListener());
    }

    //=========================Activity Jump=======================================

    public class MaintenanceOnClickListener implements OnClickListener{

        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,DataMaintenanceActivity.class);
            startActivity(intent);
        }
    }
    public class SearchOnClickListener implements OnClickListener{

        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,DataSearchActivity.class);
            startActivity(intent);
        }
    }
    public class StatisticsOnClickListener implements OnClickListener{

        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,DataStatisticsActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

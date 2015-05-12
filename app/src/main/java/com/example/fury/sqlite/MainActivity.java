package com.example.fury.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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


public class MainActivity extends ActionBarActivity {

    private Button create_database = null;
    private Button update_database = null;
    private Button insert = null;
    private Button update = null;
    private Button query = null;
    private Button delete = null;
    private Button delete_database = null;

    private Button maintenance = null;
    private Button search = null;
    private Button statistics = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        create_database = (Button)findViewById(R.id.create_database);
        create_database.setOnClickListener(new CreateDatabaseOnClickListener());
        update_database = (Button)findViewById(R.id.update_database);
        update_database.setOnClickListener(new UpdateDatabaseOnClickListener());
        insert = (Button)findViewById(R.id.insert);
        insert.setOnClickListener(new InsertOnClickListener());
        update = (Button)findViewById(R.id.update);
        update.setOnClickListener(new UpdateOnClickListener());
        query = (Button)findViewById(R.id.query);
        query.setOnClickListener(new QueryOnClickListener());
        delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new DeleteOnClickListener());
        delete_database = (Button)findViewById(R.id.delete_database);
        delete_database.setOnClickListener(new Delete_databaseOnClickListener());
        */
//-------------------------跳转按键--------------------------------------------------
        maintenance = (Button)findViewById(R.id.To_DataMaintenance);
        maintenance.setOnClickListener(new MaintenanceOnClickListener());
        search = (Button)findViewById(R.id.To_DataSearch);
        search.setOnClickListener(new SearchOnClickListener());
        statistics = (Button)findViewById(R.id.To_DataStatistics);
        statistics.setOnClickListener(new StatisticsOnClickListener());


    }
    /*
    public class CreateDatabaseOnClickListener implements OnClickListener{

        public void onClick(View v) {
            //创建一个DatabaseHelper类的对象，该类是单独一个java文件,这里采用2个参数的构造函数，建立的数据
            //库的名字为tornadomeet.db
            DatabaseHelper database_helper = new DatabaseHelper(MainActivity.this, "tornadomeet.db");
            //只有调用getReadableDatabase()或者getWriteableDatabase()函数后才能返回一个SQLiteDatabase对象
            SQLiteDatabase db = database_helper.getReadableDatabase();
        }
    }
    public class UpdateDatabaseOnClickListener implements OnClickListener{

        public void onClick(View v) {
            DatabaseHelper database_helper = new DatabaseHelper(MainActivity.this, "tornadomeet.db", 2);
            SQLiteDatabase db = database_helper.getReadableDatabase();
        }
    }

    public class InsertOnClickListener implements OnClickListener{

        public void onClick(View v) {
            // 生成contentvallues对象，该对象用来存数据的
            ContentValues values = new ContentValues();
            values.put("id", 1);//注意值的类型要匹配
            values.put("name", "tornado");
            DatabaseHelper database_helper = new DatabaseHelper(MainActivity.this, "tornadomeet.db");
            SQLiteDatabase db = database_helper.getWritableDatabase();//这里是获得可写的数据库
            db.insert("user1", null, values);
        }
    }
    public class UpdateOnClickListener implements OnClickListener{

        public void onClick(View v) {
            DatabaseHelper database_helper = new DatabaseHelper(MainActivity.this, "tornadomeet.db");
            SQLiteDatabase db = database_helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "tornadomeet");
            //参数1为表名，参数2为更新后的值，参数3表示满足条件的列名称，参数4为该列名下的值
            db.update("user1", values, "id=?", new String[]{"1"});
        }
    }

    public class QueryOnClickListener implements OnClickListener{

        public void onClick(View v) {
            // TODO Auto-generated method stub
            DatabaseHelper database_helper = new DatabaseHelper(MainActivity.this, "tornadomeet.db");
            SQLiteDatabase db = database_helper.getWritableDatabase();
            //查询的语法，参数1为表名；参数2为表中的列名；参数3为要查询的列名；参数时为对应列的值；该函数返回的是一个游标
            Cursor cursor = db.query("user1", new String[]{"id", "name"}, "id=?", new String[]{"1"},  null, null, null);
            //遍历每一个记录
            while(cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));//返回列名为name的值
                System.out.println("query---->" + name);
            }
        }
    }
    public class DeleteOnClickListener implements OnClickListener{

        public void onClick(View v) {
            // TODO Auto-generated method stub
            DatabaseHelper database_helper = new DatabaseHelper(MainActivity.this, "tornadomeet.db");
            SQLiteDatabase db = database_helper.getWritableDatabase();
            //直接删除名为tornadomeet对应的那条记录
            db.delete("user1", "name=?" ,new String[]{"tornadomeet"});
        }

    }
    public class Delete_databaseOnClickListener implements OnClickListener{

        public void onClick(View v) {
            DatabaseHelper database_helper = new DatabaseHelper(MainActivity.this, "tornadomeet.db");
            boolean deleteSusseed = false;
            deleteSusseed = database_helper.deleteDatabase(MainActivity.this, "tornadomeet.db");
            if(deleteSusseed)
                System.out.println("susseed delete database!");
            else System.out.println("faile to delete!");
        }

    }*/
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

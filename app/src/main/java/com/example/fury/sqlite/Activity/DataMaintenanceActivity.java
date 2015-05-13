package com.example.fury.sqlite.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.MainActivity;
import com.example.fury.sqlite.R;

public class DataMaintenanceActivity extends ActionBarActivity {

    private Button create;
    private Button insert;
    private Button delete;
    private Button modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_maintenance);

        create = (Button) findViewById(R.id.create_database);
        create.setOnClickListener(new CreateOnClickListener());
        insert = (Button) findViewById(R.id.data_insert);
        insert.setOnClickListener(new InsertOnClickListener());
        delete = (Button) findViewById(R.id.data_delete);
        delete.setOnClickListener(new DeleteOnClickListener());
        modify = (Button) findViewById(R.id.data_modify);
        modify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DataMaintenanceActivity.this, MtnDataModify.class);
                startActivity(intent);
            }
        });
    }
    public class CreateOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            //创建一个DatabaseHelper类的对象，该类是单独一个java文件,这里采用2个参数的构造函数，建立的数据
            DatabaseHelper database_helper = new DatabaseHelper(DataMaintenanceActivity.this, "stu_manager.db");
            SQLiteDatabase db = database_helper.getReadableDatabase();
            Toast.makeText(DataMaintenanceActivity.this,"Create Database Successed !",Toast.LENGTH_LONG).show();
        }
    }
    public class InsertOnClickListener implements View.OnClickListener {

        public void onClick(View v) {
            Intent intent=new Intent(DataMaintenanceActivity.this, MtnDataInsert.class);
            startActivity(intent);
        }
    }
    public class DeleteOnClickListener implements View.OnClickListener {

        public void onClick(View v) {
            Intent intent=new Intent(DataMaintenanceActivity.this, MtnDataDelete.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data_maintenance, menu);
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

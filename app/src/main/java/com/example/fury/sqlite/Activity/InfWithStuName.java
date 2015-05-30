package com.example.fury.sqlite.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.R;
import com.example.fury.sqlite.model.Scores;
import com.example.fury.sqlite.model.Student;

import java.util.ArrayList;
import java.util.List;

public class InfWithStuName extends ActionBarActivity {
    private Button query;
    private SQLiteDatabase db;

    private EditText et_snum;
    private String selectCondition;

    private LinearLayout linearLayout;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_with_stu_name);

        query = (Button)findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper= new DatabaseHelper(InfWithStuName.this,
                        "stu_manager.db", null, 1);
                db = dbHelper.getWritableDatabase();

                et_snum = (EditText)findViewById(R.id.et_snum);
                selectCondition = et_snum.getText().toString();

                Cursor cursor = db.query("Student", new String[]{"Snum","Sclass","Sname","Ssex","Sphone","Sage"},
                        "Sname=?", new String[]{selectCondition}, null, null, null);
                if(cursor.moveToNext()){
                    Student student = new Student();
                    student.setSnum(cursor.getString(cursor.getColumnIndex("Snum")));
                    student.setSclass(cursor.getString(cursor.getColumnIndex("Sclass")));
                    student.setSname(cursor.getString(cursor.getColumnIndex("Sname")));
                    student.setSsex(cursor.getString(cursor.getColumnIndex("Ssex")));
                    student.setSphone(cursor.getString(cursor.getColumnIndex("Sphone")));
                    student.setSage(cursor.getInt(cursor.getColumnIndex("Sage")));

                    listView = (ListView) findViewById(R.id.list_view);
                    adapter = new ArrayAdapter<String>(InfWithStuName.this,
                            android.R.layout.simple_list_item_1, dataList);
                    listView.setAdapter(adapter);

                    dataList.add("学号：" + student.getSnum());
                    dataList.add("姓名：" + student.getSname());
                    dataList.add("班级：" + student.getSclass());
                    dataList.add("性别：" + student.getSsex());
                    dataList.add("年龄：" + student.getSage());
                    dataList.add("手机号：" + student.getSphone());
                    dataList.add("==============成绩表==============");

                    cursor = db.query("Scores", new String[]{"student_num","course_num","Score"},
                            "student_num=?", new String[]{student.getSnum()}, null, null, null);

                    while (cursor.moveToNext()) {

                        Scores scores= new Scores();
                        scores.setSnum(cursor.getString(cursor.getColumnIndex("student_num")));
                        scores.setCnum(cursor.getString(cursor.getColumnIndex("course_num")));
                        scores.setScore(cursor.getString(cursor.getColumnIndex("Score")));

                        dataList.add("学号：" + scores.getSnum());
                        dataList.add("课程号：" + scores.getCnum());
                        dataList.add("分数：" + scores.getScore());
                    }
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);

                    linearLayout = (LinearLayout) findViewById(R.id.mylinearlayout);
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(InfWithStuName.this, "未发现该课程！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

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
import com.example.fury.sqlite.model.OptionalCourses;
import com.example.fury.sqlite.model.Scores;
import com.example.fury.sqlite.model.Student;

import java.util.ArrayList;
import java.util.List;

public class InfWithCouNum extends ActionBarActivity {
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
        setContentView(R.layout.activity_inf_with_cou_num);

        query = (Button)findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(InfWithCouNum.this,
                        "stu_manager.db", null, 1);
                db = dbHelper.getWritableDatabase();

                et_snum = (EditText) findViewById(R.id.et_snum);
                selectCondition = et_snum.getText().toString();

                Cursor cursor = db.query("Courses", new String[]{"Cnum", "Sname", "Ccredit"},
                        "Cnum=?", new String[]{selectCondition}, null, null, null);
                if (cursor.moveToNext()) {
                    OptionalCourses optionalCourses = new OptionalCourses();
                    optionalCourses.setCnum(cursor.getString(cursor.getColumnIndex("Cnum")));
                    optionalCourses.setCname(cursor.getString(cursor.getColumnIndex("Sname")));
                    optionalCourses.setCcredit(cursor.getInt(cursor.getColumnIndex("Ccredit")));

                    listView = (ListView) findViewById(R.id.list_view);
                    adapter = new ArrayAdapter<String>(InfWithCouNum.this,
                            android.R.layout.simple_list_item_1, dataList);
                    listView.setAdapter(adapter);

                    dataList.add("课程号：" + optionalCourses.getCnum());
                    dataList.add("课程名：" + optionalCourses.getCname());
                    dataList.add("学分：" + optionalCourses.getCcredit());
                    dataList.add("==============选课的学生信息==============");

                    cursor = db.query("Scores", new String[]{"student_num", "course_num", "Score"},
                            "course_num=?", new String[]{optionalCourses.getCnum()}, null, null, null);

                    while (cursor.moveToNext()) {
                        Scores scores = new Scores();
                        scores.setSnum(cursor.getString(cursor.getColumnIndex("student_num")));

                        Cursor cursor2 = db.query("Student", new String[]{"Snum", "Sclass", "Sname", "Ssex", "Sphone", "Sage"},
                                "Snum=?", new String[]{scores.getSnum()}, null, null, null);

                        if (cursor2.moveToNext()) {
                            Student student = new Student();
                            student.setSnum(cursor2.getString(cursor2.getColumnIndex("Snum")));
                            student.setSclass(cursor2.getString(cursor2.getColumnIndex("Sclass")));
                            student.setSname(cursor2.getString(cursor2.getColumnIndex("Sname")));
                            student.setSsex(cursor2.getString(cursor2.getColumnIndex("Ssex")));
                            student.setSphone(cursor2.getString(cursor2.getColumnIndex("Sphone")));
                            student.setSage(cursor2.getInt(cursor2.getColumnIndex("Sage")));

                            dataList.add("学号：" + student.getSnum());
                            dataList.add("姓名：" + student.getSname());
                            dataList.add("班级：" + student.getSclass());
                            dataList.add("性别：" + student.getSsex());
                            dataList.add("年龄：" + student.getSage());
                            dataList.add("电话：" + student.getSphone());
                        }
                    }
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);

                    linearLayout = (LinearLayout) findViewById(R.id.mylinearlayout);
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(InfWithCouNum.this, "未发现该课程", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
}

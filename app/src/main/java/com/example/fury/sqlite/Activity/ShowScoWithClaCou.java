package com.example.fury.sqlite.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.R;

import java.util.ArrayList;
import java.util.List;

public class ShowScoWithClaCou extends ActionBarActivity {
    private SQLiteDatabase db;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<String>();
    /*
    public static final String CREATE_CLACOUTABLE = "create table clacouscore("
            +"class text,"
            +"cnum text,"
            +"socre integer)";*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sco_with_cla_cou);

        DatabaseHelper dbHelper= new DatabaseHelper(ShowScoWithClaCou.this,
                "stu_manager.db", null, 1);
        db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.cla_cou_listview);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        dataList.add("哈喽");

        //=======================创建特殊表=====================
        //db.execSQL(CREATE_CLACOUTABLE);

        String findIntersect = "select distinct snum from student " +
                "intersect select distinct student_num from scores order by snum asc;";

        Cursor cursor;
        //Cursor cursor2;
        cursor = db.rawQuery(findIntersect, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String Snum = cursor.getString(0);    //此为两表的桥梁 学号！
                String Sclass="";
                //1.通过学号 查询学生所在的班级
                Log.d("nimeiya 学号：", Snum);

                String  queryClass = "select sclass from student where snum = "+cursor.getString(0);
                Cursor cursor2 = db.rawQuery(queryClass, null);
                if (cursor2.moveToFirst()){
                    Sclass = cursor2.getString(0);
                    Log.d("nimeiya 班级：", Sclass);
                }
                //2.通过学号 查询成绩表的 课程号  成绩  按课程号排序
                String findCnumScore = "select score,course_num from scores where student_num = "
                        + Snum +" order by course_num asc;";
                Cursor cursor3 = db.rawQuery(findCnumScore, null);
                if (cursor3.moveToFirst()){
                    for(int j=0; j<cursor3.getCount(); j++) {
                        String score = cursor3.getString(0);
                        String course_num = cursor3.getString(1);
                        Log.d("nimeiya 成绩：", score);
                        Log.d("nimeiya 课程号：", course_num);
                        String fommat = sprintfScoWithClaCou(Sclass,course_num,score);
                        dataList.add(fommat);
                        cursor3.moveToNext();
                    }
                }
                cursor.moveToNext();
            }
        }
        //Cursor cursor = db.query("student",null, null, null, null, null, "Sclass ASC");
        //Cursor cursor2 = db.query("Scores", null, null, null, null, null, "course_num ASC");
        /*
        if(cursor.moveToFirst()){
            for(int i=0;i<cursor.getCount();i++){
                //cursor.move(i);
                cursor.moveToPosition(i);
                String Snum=cursor.getString(0);
                String Sclass=cursor.getString(1);

                ContentValues values = new ContentValues();

                values.put("class", Sclass);
                values.put("cnum", );
                values.put("score", );

                String fommat = sprintfScoWithClaCou(Sclass);
                Log.d("nimeiya", fommat);
                dataList.add(fommat);
            }
        }*/
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        db.close();
    }
    static public String sprintfScoWithClaCou(String sclass,String course_num,String socre){
        String s;
        s = String.format("%-15s|%15s     |%15s", sclass,course_num,socre);
        return s;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_sco_with_cla_cou, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

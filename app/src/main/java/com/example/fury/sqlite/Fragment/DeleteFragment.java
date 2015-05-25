package com.example.fury.sqlite.Fragment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteFragment extends Fragment {

    private Context mContext;
    private int mEvent;
    private View mView;

    private EditText et_snum;
    private EditText et_cnum;
    private TextView snum;
    private TextView cnum;
    private Button commit;

    private String Snum;
    private String Cnum;

    public DeleteFragment() {
        // Required empty public constructor
    }
    public DeleteFragment(Context context,int event) {
        mContext = context;
        mEvent = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_delete, container, false);
        switch (mEvent){
            case 0:
                commit = (Button)mView.findViewById(R.id.delete);
                commit.setVisibility(View.VISIBLE);
                snum = (TextView)mView.findViewById(R.id.snum);
                snum.setVisibility(View.VISIBLE);
                et_snum = (EditText)mView.findViewById(R.id.et_snum);
                et_snum.setVisibility(View.VISIBLE);
                break;
            case 1:
                commit = (Button)mView.findViewById(R.id.delete);
                commit.setVisibility(View.VISIBLE);
                cnum = (TextView)mView.findViewById(R.id.cnum);
                cnum.setVisibility(View.VISIBLE);
                et_cnum = (EditText)mView.findViewById(R.id.et_cnum);
                et_cnum.setVisibility(View.VISIBLE);
                break;
            case 2:
                commit = (Button)mView.findViewById(R.id.delete2);
                commit.setVisibility(View.VISIBLE);
                snum = (TextView)mView.findViewById(R.id.snum);
                snum.setVisibility(View.VISIBLE);
                et_snum = (EditText)mView.findViewById(R.id.et_snum);
                et_snum.setVisibility(View.VISIBLE);
                cnum = (TextView)mView.findViewById(R.id.cnum2);
                cnum.setVisibility(View.VISIBLE);
                et_cnum = (EditText)mView.findViewById(R.id.et_cnum2);
                et_cnum.setVisibility(View.VISIBLE);
                break;
            default:
                return mView;
        }
        //=====================通过学号删除学生==========================
        if(mEvent == 0){
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snum = et_snum.getText().toString();
                    if(Snum.equals("")) {
                        Toast.makeText(mContext, "输入信息不完整！", Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    DatabaseHelper dbHelper = new DatabaseHelper(mContext,
                            "stu_manager.db", null, 1);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("Student", new String[]{"Snum"},
                            "Snum=?", new String[]{Snum}, null, null, null);
                    if(!cursor.moveToFirst()){
                        Toast.makeText(mContext, " 该学号不存在！\n 未执行删除操作！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //删除学生前应先删除  学生与课程之间的关系。
                    db.delete("scores", "student_num=?", new String[]{Snum});
                    db.delete("Student", "Snum=?", new String[]{Snum});
                    Log.d("nimeiya", Snum);
                    Log.d("nimeiya", "Snum删不掉");
                    Toast.makeText(mContext, "删除成功！", Toast.LENGTH_SHORT).show();
                }
            });

        }
        //====================通过课程号删除课程===========================
        if(mEvent == 1){
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cnum = et_cnum.getText().toString();
                    if(Cnum.equals("")) {
                        Toast.makeText(mContext, "输入信息不完整！", Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    DatabaseHelper dbHelper = new DatabaseHelper(mContext,
                            "stu_manager.db", null, 1);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("Courses", new String[]{"Sname"},
                            "Cnum=?", new String[]{Cnum}, null, null, null);
                    if(!cursor.moveToFirst()){
                        Toast.makeText(mContext, "该课程号不存在！\n 未执行删除操作！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    db.delete("scores", "course_num=?", new String[]{Cnum});
                    db.delete("Courses", "Cnum=?", new String[]{Cnum});

                    Toast.makeText(mContext, "删除成功！", Toast.LENGTH_SHORT).show();
                }
            });
        }

        //=====================课程号和学号删除成绩表==========================
        if(mEvent == 2){
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cnum = et_cnum.getText().toString();
                    Snum = et_snum.getText().toString();
                    if(Cnum.equals("") ||Snum.equals("")) {
                        Toast.makeText(mContext, "输入信息不完整！", Toast.LENGTH_SHORT).show();
                        return ;
                    }

                    DatabaseHelper dbHelper = new DatabaseHelper(mContext,
                            "stu_manager.db", null, 1);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    //=========================容错============================
                    Cursor cursor = db.query("Scores", new String[]{"course_num", "student_num", "score"},
                            "course_num=? and student_num=?", new String[]{Cnum, Snum}, null, null, null);
                    if(!cursor.moveToFirst()){
                        Toast.makeText(mContext, " 该成绩不存在！ \n 未执行删除操作！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String delStatment = "Delete From Scores Where student_num = " + Snum
                            +" And course_num = " + Cnum;
                    db.execSQL(delStatment);
                    Log.d("nimeiya", Cnum);
                    Log.d("nimeiya", Snum);
                    Toast.makeText(mContext, "删除成功！", Toast.LENGTH_SHORT).show();
                }
            });

        }
        return mView;
    }


}

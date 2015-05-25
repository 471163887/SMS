package com.example.fury.sqlite.Fragment;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.R;
import com.example.fury.sqlite.model.OptionalCourses;
import com.example.fury.sqlite.model.Scores;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddScoreFragment extends Fragment {

    private Button commit;
    private View mView;
    private Context mContext;
    private SQLiteDatabase db;

    private EditText et_snum;
    private EditText et_cnum;
    private EditText et_score;
    private String cnum;
    private String snum;
    private String score;

    public AddScoreFragment() {
        // Required empty public constructor
    }

    public AddScoreFragment(Context context){
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_add_score, container, false);
        commit = (Button)mView.findViewById(R.id.btn_confirm);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et_cnum = (EditText)mView.findViewById(R.id.et_cnum);
                cnum = et_cnum.getText().toString().trim();
                et_snum = (EditText)mView.findViewById(R.id.et_snum);
                snum = et_snum.getText().toString();
                et_score = (EditText)mView.findViewById(R.id.et_score);
                score = et_score.getText().toString();


                if(cnum.equals("") || snum.equals("") || score.equals(""))
                {
                    Toast.makeText(mContext, "输入信息不完整！", Toast.LENGTH_SHORT).show();
                    return ;
                }
                Toast.makeText(mContext, "输入正确！", Toast.LENGTH_SHORT).show();

                Scores scores = new Scores( snum, cnum, score);

                DatabaseHelper dbHelper= new DatabaseHelper(mContext,
                        "stu_manager.db", null, 1);
                db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Scores", new String[]{"course_num", "student_num", "score"},
                        "course_num=? and student_num=?", new String[]{cnum,snum}, null, null, null);
                if(cursor.moveToFirst()){
                    Toast.makeText(mContext, "该成绩已存在！ \n 插入此成绩信息失效！", Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues values = new ContentValues();

                values.put("student_num", scores.getSnum());
                values.put("course_num", scores.getCnum());
                values.put("score", scores.getScore());


                db.insert("scores", null, values);
                Toast.makeText(mContext, "插入数据库成功！", Toast.LENGTH_SHORT).show();

            }
        });
        return mView;
    }


}

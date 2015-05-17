package com.example.fury.sqlite.Fragment;


import android.content.Context;
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
public class ModifyFragment extends Fragment {

    private Context mContext;
    private int mEvent;
    private View mView;

    private EditText et_snum;
    private EditText et_cnum;

    private EditText et_modify_sclass;
    private EditText et_modify_sname;
    private EditText et_modify_ssex;
    private EditText et_modify_sphone;
    private EditText et_modify_sage;
    private EditText et_modify_cname;
    private EditText et_modify_ccredit;
    private EditText et_modify_score;
    private EditText et_modify_cnum;

    private TextView snum;
    private TextView cnum;
    private Button commit;
    private Button commit_modify;

    private String Snum;
    private String Cnum;

    public ModifyFragment() {
    }
    public ModifyFragment(Context context, int event) {
        mContext = context;
        mEvent = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_modify, container, false);
        switch (mEvent){
            case 0:
                commit = (Button)mView.findViewById(R.id.query);
                commit.setVisibility(View.VISIBLE);
                snum = (TextView)mView.findViewById(R.id.snum);
                snum.setVisibility(View.VISIBLE);
                et_snum = (EditText)mView.findViewById(R.id.et_snum);
                et_snum.setVisibility(View.VISIBLE);
                break;
            case 1:
                commit = (Button)mView.findViewById(R.id.query);
                commit.setVisibility(View.VISIBLE);
                cnum = (TextView)mView.findViewById(R.id.cnum);
                cnum.setVisibility(View.VISIBLE);
                et_cnum = (EditText)mView.findViewById(R.id.et_cnum);
                et_cnum.setVisibility(View.VISIBLE);
                break;
            case 2:
                commit = (Button)mView.findViewById(R.id.query);
                commit.setVisibility(View.VISIBLE);
                snum = (TextView)mView.findViewById(R.id.snum);
                snum.setVisibility(View.VISIBLE);
                et_snum = (EditText)mView.findViewById(R.id.et_snum);
                et_snum.setVisibility(View.VISIBLE);
                break;
            default:
                return mView;
        }
        //=====================通过学号修改学生信息==========================
        if(mEvent == 0){
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean susseedQuery = false;
                    Snum = et_snum.getText().toString();

                    commit_modify = (Button)mView.findViewById(R.id.bnt_modify);
                    commit_modify.setVisibility(View.VISIBLE);

                    et_modify_sclass = (EditText)mView.findViewById(R.id.et_modify_sclass);
                    et_modify_sname = (EditText)mView.findViewById(R.id.et_modify_sname);
                    et_modify_ssex = (EditText)mView.findViewById(R.id.et_modify_ssex);
                    et_modify_sphone = (EditText)mView.findViewById(R.id.et_modify_sphone);
                    et_modify_sage = (EditText)mView.findViewById(R.id.et_modify_sage);

                    et_modify_sclass.setVisibility(View.VISIBLE);
                    et_modify_sname.setVisibility(View.VISIBLE);
                    et_modify_ssex.setVisibility(View.VISIBLE);
                    et_modify_sphone.setVisibility(View.VISIBLE);
                    et_modify_sage.setVisibility(View.VISIBLE);

                    Toast.makeText(mContext, "存在该学生！", Toast.LENGTH_SHORT).show();

                    commit_modify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String modifySclass = et_modify_sclass.getText().toString();
                            String modifySname = et_modify_sname.getText().toString();
                            String modifySsex = et_modify_ssex.getText().toString();
                            String modifySphone = et_modify_sphone.getText().toString();
                            int modifySage = Integer.parseInt(et_modify_sage.getText().toString());

                            DatabaseHelper dbHelper = new DatabaseHelper(mContext, "stu_manager.db", null, 1);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();

                            String upDate = "UPDATE Student SET Sclass = '" + modifySclass
                                    + "', Ssex ='" + modifySsex
                                    + "', Sphone='" + modifySphone
                                    + "',Sage = '" + modifySage
                                    + "', Sname = '" + modifySname
                                    + "' where Snum = " + Snum;
                            //db.update("user1", values, "id=?", new String[]{"1"});
                            db.execSQL(upDate);
                            Log.d("nimeiya", Snum);
                            Toast.makeText(mContext, "修改学生信息成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }
        //=====================通过课程号修改课程信息==========================
        if(mEvent == 1){
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean susseedQuery = false;
                    Cnum = et_cnum.getText().toString();

                    commit_modify = (Button)mView.findViewById(R.id.bnt_modify);
                    commit_modify.setVisibility(View.VISIBLE);

                    et_modify_cname = (EditText)mView.findViewById(R.id.et_modify_cname);
                    et_modify_ccredit = (EditText)mView.findViewById(R.id.et_modify_ccredit);

                    et_modify_cname.setVisibility(View.VISIBLE);
                    et_modify_ccredit.setVisibility(View.VISIBLE);

                    Toast.makeText(mContext, "存在该课程！", Toast.LENGTH_SHORT).show();

                    commit_modify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String modifyCname = et_modify_cname.getText().toString();
                            int modifyCcredit = Integer.parseInt(et_modify_ccredit.getText().toString());

                            DatabaseHelper dbHelper = new DatabaseHelper(mContext, "stu_manager.db", null, 1);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();

                            String upDate = "UPDATE Courses SET Sname = '" + modifyCname
                                    + "', Ccredit = '" + modifyCcredit
                                    + "' where Cnum = " + Cnum;
                            db.execSQL(upDate);
                            Log.d("nimeiya", Cnum);
                            Toast.makeText(mContext, "修改课程信息成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        //====================通过学号修改成绩表==========================
        if(mEvent == 2){
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean susseedQuery = false;
                    Snum = et_snum.getText().toString();

                    commit_modify = (Button)mView.findViewById(R.id.bnt_modify);
                    commit_modify.setVisibility(View.VISIBLE);

                    et_modify_score = (EditText)mView.findViewById(R.id.et_modify_score);
                    et_modify_cnum = (EditText)mView.findViewById(R.id.et_modify_cnum);
                    et_modify_score.setVisibility(View.VISIBLE);
                    et_modify_cnum.setVisibility(View.VISIBLE);


                    Toast.makeText(mContext, "存在该学生！", Toast.LENGTH_SHORT).show();

                    commit_modify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String modifyCnum = et_modify_cnum.getText().toString();
                            String modifyScore = et_modify_score.getText().toString();

                            DatabaseHelper dbHelper = new DatabaseHelper(mContext, "stu_manager.db", null, 1);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();

                            String upDate = "UPDATE Scores SET Score = '" + modifyScore
                                    + "' where student_num = " + Snum
                                    + " AND course_num = " + modifyCnum;
                            db.execSQL(upDate);
                            Log.d("nimeiya", Snum);
                            Toast.makeText(mContext, "修改学生信息成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        return mView;
    }
}

package com.example.fury.sqlite.Fragment;


import android.content.ContentValues;
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
import android.widget.Toast;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.R;
import com.example.fury.sqlite.model.OptionalCourses;
import com.example.fury.sqlite.model.Student;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCourseFragment extends Fragment {

    private Button commit;
    private View mView;
    private Context mContext;
    private SQLiteDatabase db;

    private EditText et_cnum;
    private EditText et_cname;
    private EditText et_credit;
    private String cnum;
    private String cname;
    private int credit;

    public AddCourseFragment() {
        // Required empty public constructor
    }
    public AddCourseFragment(Context context){
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_add_course, container, false);
        commit = (Button)mView.findViewById(R.id.btn_confirm);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et_cnum = (EditText)mView.findViewById(R.id.et_cnum);
                cnum = et_cnum.getText().toString().trim();

                et_cname = (EditText)mView.findViewById(R.id.et_cname);
                cname = et_cname.getText().toString();



                et_credit = (EditText)mView.findViewById(R.id.et_credit);
                credit = Integer.parseInt(et_credit.getText().toString());


                if(et_credit.equals(null) || et_cname.equals(null) || et_cnum.equals(null))
                {
                    Toast.makeText(mContext, "输入不完整！", Toast.LENGTH_SHORT).show();
                    return ;
                }
                Toast.makeText(mContext, "输入正确！", Toast.LENGTH_SHORT).show();

                OptionalCourses optionalCourses = new OptionalCourses( credit, cnum, cname);

                DatabaseHelper dbHelper= new DatabaseHelper(mContext,
                        "stu_manager.db", null, 1);
                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put("Sname", optionalCourses.getCname());
                values.put("Cnum", optionalCourses.getCnum());
                values.put("Ccredit", optionalCourses.getCcredit());


                db.insert("Courses", null, values);
                Toast.makeText(mContext, "插入数据库成功！", Toast.LENGTH_SHORT).show();

            }
        });
        return mView;
    }


}

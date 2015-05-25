package com.example.fury.sqlite.Fragment;

import android.content.ContentValues;
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
                String tempCredit = et_credit.getText().toString();


                if(cnum.equals("") || cname.equals("") || tempCredit.equals(""))
                {
                    Toast.makeText(mContext, "输入信息不完整！", Toast.LENGTH_SHORT).show();
                    return ;
                }
                credit = Integer.parseInt(tempCredit);
                Toast.makeText(mContext, "合法的输入！", Toast.LENGTH_SHORT).show();

                OptionalCourses optionalCourses = new OptionalCourses( credit, cnum, cname);

                DatabaseHelper dbHelper= new DatabaseHelper(mContext,
                        "stu_manager.db", null, 1);
                db = dbHelper.getWritableDatabase();

                Cursor cursor = db.query("courses", new String[]{"Cnum"},
                        "Cnum=? ", new String[]{cnum}, null, null, null);
                if(cursor.moveToFirst()){
                    Toast.makeText(mContext, "该课程已存在！ \n 插入此成绩信息失效！", Toast.LENGTH_SHORT).show();
                    return;
                }

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

package com.example.fury.sqlite.Fragment;

import android.app.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import com.example.fury.sqlite.model.Student;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddStuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddStuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddStuFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
//===============自定义==============
    private Button commit;
    private View mView;

    private EditText et_snum;
    private EditText et_sname;
    private EditText et_sclass;
    private EditText et_ssex;
    private EditText et_sage;
    private EditText et_sphone;

    private String Snum;
    private String Sname;
    private String Sclass;
    private String Ssex;
    private int Sage;
    private String Sphone;

    private Context mContext;

    private SQLiteDatabase db;

    //public Student student; //private 为何不行？


    public static AddStuFragment newInstance(String param1, String param2) {
        AddStuFragment fragment = new AddStuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);


        return fragment;
    }

    public AddStuFragment() {
        // Required empty public constructor
    }

    public AddStuFragment(Context context){
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_add_stu, container, false);

        commit = (Button)mView.findViewById(R.id.btn_confirm);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_snum = (EditText)mView.findViewById(R.id.et_snum);
                et_sname = (EditText)mView.findViewById(R.id.et_sname);
                et_sclass = (EditText)mView.findViewById(R.id.et_sclass);
                et_ssex = (EditText)mView.findViewById(R.id.et_ssex);
                et_sphone = (EditText)mView.findViewById(R.id.et_sphone);
                et_sage = (EditText)mView.findViewById(R.id.et_sage);

                Snum = et_snum.getText().toString();
                Sname = et_sname.getText().toString();
                Sclass = et_sclass.getText().toString();
                Ssex = et_ssex.getText().toString();
                Sphone = et_sphone.getText().toString().trim();
                String tempSage = et_sage.getText().toString();

                if(Snum.equals("") || Sname.equals("") || Sclass.equals("")
                        || Ssex.equals("")|| Sphone.equals("")|| tempSage.equals(""))
                {
                    Toast.makeText(mContext, "输入信息不完整！", Toast.LENGTH_SHORT).show();
                    return ;
                }
                Sage = Integer.parseInt(et_sage.getText().toString());


                    Toast.makeText(mContext, "输入正确！", Toast.LENGTH_SHORT).show();
                    Log.d("nimeiya", Snum);
                    Log.d("nimeiya", Sname);
                    Log.d("nimeiya", Ssex);
                    Log.d("nimeiya", Sclass);
                    Log.d("nimeiya", Sphone);
                    Log.d("nimeiya", et_sage.getText().toString());
                    Student student = new Student(Sage,Snum,Sname,Sphone,Ssex,Sclass);

                    DatabaseHelper dbHelper= new DatabaseHelper(mContext,
                            "stu_manager.db", null, 1);
                    db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("student", new String[]{"Snum"},
                            "Snum=?", new String[]{Snum}, null, null, null);
                    if(cursor.moveToFirst()){
                        Toast.makeText(mContext, "该学生已存在！ \n 插入此成绩信息失效！", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ContentValues values = new ContentValues();

                    values.put("Snum", student.getSnum());
                    values.put("Sclass", student.getSclass());
                    values.put("Sname", student.getSname());
                    values.put("Ssex", student.getSsex());
                    values.put("Sphone", student.getSphone());
                    values.put("Sage", student.getSage());

                    db.insert("Student", null, values);
                    Toast.makeText(mContext, "插入数据库成功！", Toast.LENGTH_SHORT).show();
                }
        });
        return mView;
    }
/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }*/

}

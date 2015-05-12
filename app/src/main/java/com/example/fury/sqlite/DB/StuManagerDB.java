package com.example.fury.sqlite.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.fury.sqlite.DatabaseHelper;
import com.example.fury.sqlite.model.Student;

/**
 * Created by flt on 2015/5/10.
 */
public class StuManagerDB {
    /**
     * ���ݿ���
     */
    public static final String DB_NAME = "stu_";
    /**
     * ���ݿ�汾
     */
    public static final int VERSION = 1;
    private static StuManagerDB stuManagerDB;

    private SQLiteDatabase db;
    /**
     * ���캯��˽�л�  ������
     */
    private StuManagerDB(Context context){
        DatabaseHelper dbHelper= new DatabaseHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * ��ȡStuManagerDB��ʵ��
     */
    public synchronized static StuManagerDB getInstance(Context context){
        if(stuManagerDB == null)
            stuManagerDB = new StuManagerDB(context);
        return stuManagerDB;
    }

    /**
     * ��Studentʵ���洢�����ݿ�
     */
    public void saveStudent(Student student){
        if(student != null){
            ContentValues values = new ContentValues();
            values.put("Snum", student.getSnum());
            values.put("Sname", student.getSname());
            values.put("Sclass", student.getSclass());
            values.put("Ssex", student.getSsex());
            values.put("Sage", student.getSage());
            values.put("Sphone", student.getSphone());
            db.insert("Student", null, values);
        }
    }
}

package com.example.fury.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by flt on 2015/4/30.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int  VERSON = 1;//默认的数据库版本
    //继承SQLiteOpenHelper类的类必须有自己的构造函数
    //该构造函数4个参数，直接调用父类的构造函数。其中第一个参数为该类本身；第二个参数为数据库的名字；
    //第3个参数是用来设置游标对象的，这里一般设置为null；参数四是数据库的版本号。
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int verson){
        super(context, name, factory, verson);
    }

    //该构造函数有3个参数，因为它把上面函数的第3个参数固定为null了
    public DatabaseHelper(Context context, String name, int verson){
        this(context, name, null, verson);
    }
    //该构造函数只有2个参数，在上面函数 的基础山将版本号固定了
    public DatabaseHelper(Context context, String name){
        this(context, name, VERSON);
    }
    /**
     * 删除数据库
     * @param context
     * @return
     */
    public boolean deleteDatabase(Context context,String name) {
        return context.deleteDatabase(name);
    }

    public static final String CREATE_STUDENT = "create table Student("
            +"Snum text,"
            +"Sclass text,"
            +"Sname text,"
            +"Ssex text,"
            +"Sphone text,"
            +"Sage integer)";

    public static final String CREATE_SCORE = "create table Scores("
            +"student_num text,"
            +"course_num text,"
            +"Score text)";

    public static final String CREATE_OPTIONALCOURSES = "create table Courses("
            +"Cnum text,"
            +"Sname text,"
            +"Ccredit integer)";
    //该函数在数据库第一次被建立时调用
    @Override
    public void onCreate(SQLiteDatabase arg0) {
        System.out.println("create a sqlite database");
        //execSQL()为执行参数里面的SQL语句，因此参数中的语句需要符合SQL语法,这里是创建一个表
        arg0.execSQL(CREATE_STUDENT);
        arg0.execSQL(CREATE_SCORE);
        arg0.execSQL(CREATE_OPTIONALCOURSES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        System.out.println("update a sqlite database");
    }

}

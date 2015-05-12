package com.example.fury.sqlite.model;

/**
 * Created by flt on 2015/5/2.
 */
public class Student {

    private int Sage;
    private String Snum;
    private String Sname;
    private String Sphone;
    private String Ssex;
    private String Sclass;


    public Student(){

    }
    public Student(int Sage, String Snum, String Sname,
                   String Sphone, String Ssex,String Sclass){
        this.Sage = Sage;
        this.Snum = Snum;
        this.Sname = Sname;
        this.Sclass = Sclass;
        this.Ssex = Ssex;
        this.Sphone = Sphone;
    }
    public int getSage() {
        return Sage;
    }

    public void setSage(int sage) {
        this.Sage = sage;
    }



    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSphone() {
        return Sphone;
    }

    public void setSphone(String sphone) {
        Sphone = sphone;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public String getSclass() {
        return Sclass;
    }

    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    public String getSnum() {
        return Snum;
    }

    public void setSnum(String snum) {
        Snum = snum;
    }
}

package com.example.fury.sqlite.model;

/**
 * Created by flt on 2015/5/2.
 */
public class OptionalCourses {
    private int Ccredit;
    private String Cnum;
    private String Cname;

    public OptionalCourses(int Ccredit, String Cnum, String Cname){
        this.Ccredit = Ccredit;
        this.Cnum = Cnum;
        this.Cname = Cname;
    }


    public String getCnum() {
        return Cnum;
    }

    public void setCnum(String cnum) {
        Cnum = cnum;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public int getCcredit() {
        return Ccredit;
    }

    public void setCcredit(int ccredit) {
        Ccredit = ccredit;
    }
}

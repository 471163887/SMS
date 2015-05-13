package com.example.fury.sqlite.model;

/**
 * Created by flt on 2015/5/2.
 */
public class Scores {
    private String Snum;
    private String Cnum;
    private String Score;

    public Scores(){
    }
    public Scores(String Snum, String Cnum, String Score){
        this.Snum = Snum;
        this.Cnum = Cnum;
        this.Score = Score;

    }

    public String getCnum() {
        return Cnum;
    }

    public void setCnum(String cnum) {
        Cnum = cnum;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getSnum() {
        return Snum;
    }

    public void setSnum(String snum) {
        Snum = snum;
    }
}

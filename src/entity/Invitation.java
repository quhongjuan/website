package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Invitation {
    private int tId;
    private String pText;
    private int pId;
    private String tText;
    private String tAuthor;
    private Timestamp tTime;

    public Invitation(){}

    public Invitation(int pid,String tauthor,String ttext,Timestamp ttime) throws SQLException{
        tAuthor=tauthor;
        pId=pid;
        tText=ttext;
        tTime=ttime;
    }

    public Invitation(int tId, int pId,  String tAuthor,String tText, Timestamp tTime) {
        this.tId = tId;
        this.pId = pId;
        this.tText = tText;
        this.tAuthor = tAuthor;
        this.tTime = tTime;
    }


    public String getpText() {
        return pText;
    }

    public void setpText(String pText) {
        this.pText = pText;
    }

    public String gettAuthor() {
        return tAuthor;
    }

    public void settAuthor(String tAuthor) {
        this.tAuthor = tAuthor;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String gettText() {
        return tText;
    }

    public void settText(String tText) {
        this.tText = tText;
    }

    public Timestamp gettTime() {
        return tTime;
    }

    public void settTime(Timestamp tTime) {
        this.tTime = tTime;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "tId=" + tId +
                ", pText='" + pText + '\'' +
                ", pId=" + pId +
                ", tText='" + tText + '\'' +
                ", tAuthor='" + tAuthor + '\'' +
                ", tTime=" + tTime +
                '}';
    }
}

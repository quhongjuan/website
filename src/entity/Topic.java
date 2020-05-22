package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Topic {
    private int pid;
    private String pText;
    private String pAuthor;
    private Timestamp pTime;
    private int pCount;

    public Topic(){
    }
    public Topic(int id,String text,String author,Timestamp time,int count) throws SQLException{
        pid=id;
        pAuthor=author;
        pText=text;
        pTime=time;
        pCount=count;
    }

    public Topic(String pText, String pAuthor, Timestamp pTime, int pCount) {
        this.pText = pText;
        this.pAuthor = pAuthor;
        this.pTime = pTime;
        this.pCount = pCount;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getpText() {
        return pText;
    }

    public void setpText(String pText) {
        this.pText = pText;
    }

    public String getpAuthor() {
        return pAuthor;
    }

    public void setpAuthor(String pAuthor) {
        this.pAuthor = pAuthor;
    }

    public Date getpTime() {
        return pTime;
    }

    public void setpTime(Timestamp pTime) {
        this.pTime = pTime;
    }

    public int getpCount() {
        return pCount;
    }

    public void setpCount(int pCount) {
        this.pCount = pCount;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "pid=" + pid +
                ", pText='" + pText + '\'' +
                ", pName='" + pAuthor + '\'' +
                ", pTime=" + pTime +
                ", pCount=" + pCount +
                '}';
    }
}

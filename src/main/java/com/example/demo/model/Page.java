package com.example.demo.model;

import java.util.Date;

public class Page {

    private String user;
    private String title;
    private String url;
    private int grade;
    private String dateInsert;

    public Page(){}

    public Page(String user, String title, String url, int grade) {
        this.user = user;
        this.title = title;
        this.url = url;
        this.grade = grade;
    }

    public Page(String user, String title, String url, int grade, String dateInsert) {
        this.user = user;
        this.title = title;
        this.url = url;
        this.grade = grade;
        this.dateInsert = dateInsert;
    }

    public String getDateInsert() { return dateInsert; }

    public String getYearFromDate(String date){
      String[] arr = {};
      arr = date.split("/");
      return arr[0];
    };

    public void setDateInsert(String dateInsert) { this.dateInsert = dateInsert; }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

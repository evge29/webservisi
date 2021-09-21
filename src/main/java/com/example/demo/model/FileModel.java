package com.example.demo.model;

public class FileModel {
    private String usersPath;
    private String usersRatingPath;
    private String pagesPath;

    public FileModel() {
    }

    public FileModel(String usersPath, String usersRatingPath, String pagesPath) {
        this.usersPath = usersPath;
        this.usersRatingPath = usersRatingPath;
        this.pagesPath = pagesPath;
    }

    public String getUsersPath() {
        return usersPath;
    }

    public void setUsersPath(String usersPath) {
        this.usersPath = usersPath;
    }

    public String getUsersRatingPath() {
        return usersRatingPath;
    }

    public void setUsersRatingPath(String usersRatingPath) {
        this.usersRatingPath = usersRatingPath;
    }

    public String getPagesPath() {
        return pagesPath;
    }

    public void setPagesPath(String pagesPath) {
        this.pagesPath = pagesPath;
    }
}

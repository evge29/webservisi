package com.example.demo.service;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {
    public FileService(){}



    public Boolean createFiles(String users, String userRatings, String pages) throws IOException {

        File userFile = new File(users);
        File ratingsFile = new File(userRatings);
        File pageFile = new File(pages);

        if(!userFile.exists()){
            userFile.createNewFile();

        }else{
            System.out.println( userFile + " File already exists");
        }

        if(!ratingsFile.exists()){
            ratingsFile.createNewFile();
        }else{
            System.out.println( ratingsFile + " File already exists");
        }

        if(!pageFile.exists()){
            pageFile.createNewFile();
        }else{
            System.out.println( pageFile + " File already exists");
        }
        return true;
    }
}

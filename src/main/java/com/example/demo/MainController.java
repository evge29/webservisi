package com.example.demo;

import com.example.demo.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class MainController {

    @Autowired
    private PageService pageService;

    @GetMapping("/mywebs")
    public void getPageByUserInsert(String UserInsert) throws IOException {
        try{
            pageService.getPageByUserInsert(UserInsert);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/thisyear")
    public void getThisYearPage() throws IOException {
        try{
            pageService.getPageFromThisYear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/thisdate")
    public void getPageByDate(String datum) throws IOException {
        try{
            if(datum == null){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                datum = String.valueOf(dtf.format(now));
            }

            pageService.getPageByDate(datum);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/myratings")
    public void getRatedPageByUser(String username) throws IOException {
        try{
            pageService.getRatedPageByUser(username);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

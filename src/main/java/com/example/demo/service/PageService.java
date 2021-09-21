package com.example.demo.service;

import com.example.demo.model.FileModel;
import com.example.demo.model.Page;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PageService {

    FileModel fileModel = new FileModel();
    Page page = new Page();
    UserService userService = new UserService();
    FileService fileService = new FileService();
    public void addNewPage(String pageData) throws IOException {
        String[] arrOfStr = pageData.split(" ");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String pages = s + "\\src\\main\\java\\com\\example\\demo\\files\\pages";
        String fileName = pages;

       // String fileName = "C:\\Users\\HP\\Desktop\\Github\\WebPageService\\src\\main\\java\\com\\example\\demo\\files\\pages";

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String st;
        String content = "";
        while ((st = br.readLine()) != null){
            content += st + "\n";
        }
        String username = userService.getUsernameLogged();

        if(content.length() > 0){
            content = content + "{ Username: " + arrOfStr[0] + ", Title: " + arrOfStr[1] + ", Url: " + arrOfStr[2] + ", Grade: 0, DateInsert: "+dtf.format(now)+" }, \n";
        } else {
            content = "{ Username: " + arrOfStr[0] + ", Title: " + arrOfStr[1] + ", Url: " + arrOfStr[2] + ", Grade: 0, DateInsert: "+dtf.format(now)+" }, \n";
        }

        FileWriter fw = new FileWriter(fileName);

        fw.write(content);
        fw.flush();
        fw.close();

    }

    public String getAllPage() throws IOException {
        //String fileName = "C:\\Users\\HP\\Desktop\\Github\\WebPageService\\src\\main\\java\\com\\example\\demo\\files\\pages";

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String pages = s + "\\src\\main\\java\\com\\example\\demo\\files\\pages";
        String fileName = pages;

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String st;
        ArrayList<Page> lista = new ArrayList<>();

        while ((st = br.readLine()) != null){
            Page page = new Page();

            String[] fragments = st.split(",");

            String[] userName = fragments[0].split(" ");
            String[] title = fragments[1].split(" ");
            String[] url = fragments[2].split(" ");
            String[] grade = fragments[3].split(" ");
            String[] dateInsert = fragments[4].split(" ");

            page.setUser(userName[2]);
            page.setTitle(title[2]);
            page.setUrl(url[2]);
            page.setGrade(Integer.parseInt(grade[2]));
            page.setDateInsert(dateInsert[2]);

            lista.add(page);
        }

        String result = "";

        for(int i = 0; i < lista.size(); i++){
            result +=" " + i + " UserName: " + lista.get(i).getUser() + ", Url: " + lista.get(i).getUrl() + ", Title: " + lista.get(i).getTitle() + ", Grade: " + lista.get(i).getGrade() + ", DateInsert: " +lista.get(i).getDateInsert() + "\n";
        }

        return result;
    }

    public ArrayList<Page> getListOfPage() throws IOException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String pages = s + "\\src\\main\\java\\com\\example\\demo\\files\\pages";
        String fileName = pages;

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String st;
        ArrayList<Page> lista = new ArrayList<>();

        while ((st = br.readLine()) != null){
            Page page = new Page();

            String[] fragments = st.split(",");

            String[] userName = fragments[0].split(" ");
            String[] title = fragments[1].split(" ");
            String[] url = fragments[2].split(" ");
            String[] grade = fragments[3].split(" ");
            String[] dateInsert = fragments[4].split(" ");

            page.setUser(userName[2]);
            page.setTitle(title[2]);
            page.setUrl(url[2]);
            page.setGrade(Integer.parseInt(grade[2]));
            page.setDateInsert(dateInsert[2]);

            lista.add(page);
        }

        String result = "";

        for(int i = 0; i < lista.size(); i++){
            result +=" " + i + " UserName: " + lista.get(i).getUser() + ", Url: " + lista.get(i).getUrl() + ", Title: " + lista.get(i).getTitle() + ", Grade: " + lista.get(i).getGrade() + ", DateInsert: " +lista.get(i).getDateInsert() + "\n";
        }

        return lista;
    }

    public void deletePage(Integer id) throws IOException {
        //String fileName = "C:\\Users\\HP\\Desktop\\Github\\WebPageService\\src\\main\\java\\com\\example\\demo\\files\\pages";

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String pages = s + "\\src\\main\\java\\com\\example\\demo\\files\\pages";
        String fileName = pages;

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String st;
        String content = "";

        int i = 0;

        while ((st = br.readLine()) != null){
            if(i == id){
            } else {
                content += st + "\n";
            }
            i++;
        }

        FileWriter fw = new FileWriter(fileName);

        fw.write(content);
        fw.flush();
        fw.close();


        System.out.println("Страницата е успешно избришана");
    }

    public void vote(String voteData, FileModel fileModel) throws IOException {

        String[] arrOfStr = voteData.split(" ");

        if(Integer.parseInt(arrOfStr[1]) < 0  || Integer.parseInt(arrOfStr[1]) > 10){
                System.out.println("Внесовте невалидна оцена, дозволени оцени 0-10");
                return;
        }


        String fileName = fileModel.getPagesPath();
        String fileName2 = fileModel.getUsersRatingPath();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedReader br2 = new BufferedReader(new FileReader(fileName2));

        String st;
        String st2;
        String content = "";
        String content2 = "";
        int i = 0;

        String[] username = {};
        String[] title = {};
        String[] url = {};
        String[] grade = {};
        String[] date = {};

        while ((st = br.readLine()) != null){
            if(i == Integer.parseInt(arrOfStr[0])){
                String[] pageForVote = st.split(",");
                username = pageForVote[0].split(" ");
                title = pageForVote[1].split(" ");
                url = pageForVote[2].split(" ");
                grade = pageForVote[3].split(" ");
                date = pageForVote[4].split(" ");
            } else {
                content += st + "\n";
            }
            i++;
        }


        while ((st2 = br2.readLine()) != null){
               content2 += st2 + "\n";
            }

        content = content + "{ Username: " + username[2] + ", Title: " + title[2] + ", Url: " + url[2] + ", Grade: " + arrOfStr[1] + ", DateInsert: "+date[2]+" }, \n";
        content2 = content2 + "{ Username: " + username[2] + ", Title: " + title[2] + ", Url: " + url[2] + ", Grade: " + arrOfStr[1] + ", DateInsert: "+date[2]+" }; \n";

        FileWriter fw2 = new FileWriter(fileName2);
        fw2.write(content2);
        fw2.flush();
        fw2.close();

        FileWriter fw = new FileWriter(fileName);

        fw.write(content);
        fw.flush();
        fw.close();


        System.out.println("Вашата оцена е зачувана");

    }

    public void getSortedByRating(Integer rating) throws IOException {
        ArrayList<Page> list = new ArrayList<>();
        ArrayList<Page> filteredList = new ArrayList<>();

        list = getListOfPage();

        filteredList = (ArrayList<Page>) list.stream().filter(o -> o.getGrade() > rating).collect(Collectors.toList());


        String result = "";
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.size() > 0)
                result +=" " + i + " UserName: " + filteredList.get(i).getUser() + ", Url: " + filteredList.get(i).getUrl() + ", Title: " + filteredList.get(i).getTitle() + ", Grade: " + filteredList.get(i).getGrade() + ", DateInsert: " + filteredList.get(i).getDateInsert() + "\n";
            else
                result = "";
        }

        System.out.println(result);
    }

    public void getPageByLowestRating() throws IOException {
        List<Page> list = getListOfPage();

        List<Page> filteredList  = list.stream()
                .sorted(Comparator.comparing(Page::getGrade))
                .collect(Collectors.toList());

        String result = "";
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.size() > 0)
                result +=" " + i + " UserName: " + filteredList.get(i).getUser() + ", Url: " + filteredList.get(i).getUrl() + ", Title: " + filteredList.get(i).getTitle() + ", Grade: " + filteredList.get(i).getGrade() + ", DateInsert: " + filteredList.get(i).getDateInsert() + "\n";
            else
                result = "";
        }

        System.out.println(result);
    }

    public void getPageByHighestRating() throws IOException {
        List<Page> list = getListOfPage();

        List<Page> filteredList  = list.stream()
                .sorted(Comparator.comparing(Page::getGrade).reversed())
                .collect(Collectors.toList());

        String result = "";
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.size() > 0)
                result +=" " + i + " UserName: " + filteredList.get(i).getUser() + ", Url: " + filteredList.get(i).getUrl() + ", Title: " + filteredList.get(i).getTitle() + ", Grade: " + filteredList.get(i).getGrade() + ", DateInsert: " + filteredList.get(i).getDateInsert() + "\n";
            else
                result = "";
        }

        System.out.println(result);
    }

    public void getNewest() throws IOException {
        List<Page> list = getListOfPage();

        List<Page> filteredList  = list.stream()
                .sorted(Comparator.comparing(Page::getDateInsert).reversed())
                .collect(Collectors.toList());

        String result = "";
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.size() > 0)
                result +=" " + i + " UserName: " + filteredList.get(i).getUser() + ", Url: " + filteredList.get(i).getUrl() + ", Title: " + filteredList.get(i).getTitle() + ", Grade: " + filteredList.get(i).getGrade() + ", DateInsert: " + filteredList.get(i).getDateInsert() + "\n";
            else
                result = "";
        }

        System.out.println(result);
    }

    public void getOldest() throws IOException {
        List<Page> list = getListOfPage();

        List<Page> filteredList  = list.stream()
                .sorted(Comparator.comparing(Page::getDateInsert))
                .collect(Collectors.toList());

        String result = "";
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.size() > 0)
                result +=" " + i + " UserName: " + filteredList.get(i).getUser() + ", Url: " + filteredList.get(i).getUrl() + ", Title: " + filteredList.get(i).getTitle() + ", Grade: " + filteredList.get(i).getGrade() + ", DateInsert: " + filteredList.get(i).getDateInsert() + "\n";
            else
                result = "";
        }

        System.out.println(result);
    }

    public void getPageByUserInsert(String user) throws IOException {
        List<Page> list = getListOfPage();

        List<Page> filteredList  = (List<Page>) list.stream().filter(o-> o.getUser().equals(user)).collect(Collectors.toList());

        String result = "";
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.size() > 0)
                result +=" " + i + " UserName: " + filteredList.get(i).getUser() + ", Url: " + filteredList.get(i).getUrl() + ", Title: " + filteredList.get(i).getTitle() + ", Grade: " + filteredList.get(i).getGrade() + ", DateInsert: " + filteredList.get(i).getDateInsert() + "\n";
            else
                result = "";
        }

        System.out.println(result);
    }

    public void getPageFromThisYear() throws IOException {
        List<Page> list = getListOfPage();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime thisYear = LocalDateTime.now();

        List<Page> filteredList  = (List<Page>) list.stream().filter(o-> o.getYearFromDate(o.getDateInsert()).equals(String.valueOf(dtf.format(thisYear)))).collect(Collectors.toList());

        String result = "";
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.size() > 0)
                result +=" " + i + " UserName: " + filteredList.get(i).getUser() + ", Url: " + filteredList.get(i).getUrl() + ", Title: " + filteredList.get(i).getTitle() + ", Grade: " + filteredList.get(i).getGrade() + ", DateInsert: " + filteredList.get(i).getDateInsert() + "\n";
            else
                result = "";
        }

        System.out.println(result);

    }

    public void getPageByDate(String datum) throws IOException {
        List<Page> list = getListOfPage();


        List<Page> filteredList  = (List<Page>) list.stream().filter(o-> o.getDateInsert().equals(datum)).collect(Collectors.toList());

        String result = "";
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.size() > 0)
                result +=" " + i + " UserName: " + filteredList.get(i).getUser() + ", Url: " + filteredList.get(i).getUrl() + ", Title: " + filteredList.get(i).getTitle() + ", Grade: " + filteredList.get(i).getGrade() + ", DateInsert: " + filteredList.get(i).getDateInsert() + "\n";
            else
                result = "";
        }

        System.out.println(result);
    }

    public ArrayList<Page> getAllRatedPage() throws IOException {
       // String fileName = "C:\\Users\\HP\\Desktop\\Github\\WebPageService\\src\\main\\java\\com\\example\\demo\\files\\userRatings";

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String pages = s + "\\src\\main\\java\\com\\example\\demo\\files\\userRatings";
        String fileName = pages;
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String st;
        ArrayList<Page> lista = new ArrayList<>();

        while ((st = br.readLine()) != null){
            Page page = new Page();

            String[] fragments = st.split(",");

            String[] userName = fragments[0].split(" ");
            String[] title = fragments[1].split(" ");
            String[] url = fragments[2].split(" ");
            String[] grade = fragments[3].split(" ");
            String[] dateInsert = fragments[4].split(" ");

            page.setUser(userName[2]);
            page.setTitle(title[2]);
            page.setUrl(url[2]);
            page.setGrade(Integer.parseInt(grade[2]));
            page.setDateInsert(dateInsert[2]);

            lista.add(page);
        }

        String result = "";

        for(int i = 0; i < lista.size(); i++){
            result +=" " + i + " UserName: " + lista.get(i).getUser() + ", Url: " + lista.get(i).getUrl() + ", Title: " + lista.get(i).getTitle() + ", Grade: " + lista.get(i).getGrade() + ", DateInsert: " +lista.get(i).getDateInsert() + "\n";
        }

        return lista;
    }

    public void getRatedPageByUser(String username) throws IOException {

        List<Page> list = getAllRatedPage();

        List<Page> filteredList = list.stream().filter(o -> o.getUser().equals(username)).collect(Collectors.toList());

        String result = "";
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.size() > 0)
                result +=" " + i + " UserName: " + filteredList.get(i).getUser() + ", Url: " + filteredList.get(i).getUrl() + ", Title: " + filteredList.get(i).getTitle() + ", Grade: " + filteredList.get(i).getGrade() + ", DateInsert: " + filteredList.get(i).getDateInsert() + "\n";
            else
                result = "";
        }

        System.out.println(result);
    }
}

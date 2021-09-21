package com.example.demo;
import com.example.demo.model.FileModel;
import com.example.demo.model.Users;
import com.example.demo.service.FileService;
import com.example.demo.service.PageService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(DemoApplication.class, args);

        FileModel fileModel = new FileModel();
        UserService userService = new UserService();
        PageService pageService = new PageService();
        FileService fileService = new FileService();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String data = "";
        String loginData = null;
        String registrationData = null;
        String unRegisterData = null;
        String addPageDate = null;
        int idForDelete = 0;
        String voteData = null;
        int rating = 0;


        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String users2 = s + "\\src\\main\\java\\com\\example\\demo\\files\\users";
        String userRatings = s + "\\src\\main\\java\\com\\example\\demo\\files\\userRatings";
        String pages = s + "\\src\\main\\java\\com\\example\\demo\\files\\pages";

        fileModel.setPagesPath(pages);
        fileModel.setUsersPath(users2);
        fileModel.setUsersRatingPath(userRatings);

        fileService.createFiles(users2, userRatings, pages);

        System.out.println("Изберете број за тестирање: \n 1. Регистрација, \n 2. Логин, \n 3. Бришење на корисник, \n 4. Додавање нова страница,\n 5. Избриши постоечка страница, \n 6. Гласање за страница, \n"
                + " 7. Филтрирај според повисок рејтинг од дадениот, \n 8. Филтрирај според најнизок рејтинг, \n 9. Филтрирај според највисок рејтинг, \n 10. Филтрирај според најнови внесени , \n 11. Филтрирај според најстари внесени");

        String username = "";

        while (true){
            Integer n = Integer.parseInt(br.readLine());
            Users users = new Users();
        if(n == 1){
            System.out.println("Вие избравте 1 - Регистрација");
            System.out.println("Внесете ги податоците во следниов формат: \"Username Password1 Password2\"");
            registrationData = br.readLine();
            userService.registration(registrationData);
        }else if(n == 2){
            System.out.println("Вие избравте 2 - Логин");
            System.out.println("Внесете ги податоците во следниов формат: \"Username Password\"");
            loginData = br.readLine();
            userService.login(loginData);
            username = userService.getUsernameLogged();
        }else if(n == 3){
            if(username != ""){
            System.out.println("Вие избравте 3 - Бришење на корисник");
            System.out.println("Внесете ги податоците во следниов формат: \"Username Password1 Password2\"");
            unRegisterData = br.readLine();
            userService.deleteUser(unRegisterData);
            } else {
                System.out.println("Не сте логирани");
                return;
            }
        }else if(n == 4) {
            if(username != ""){
            System.out.println("Вие избравте 4 - Додавање нова страница");
            System.out.println("Внесете ги податоците во следниов формат: \"Title Url\"");
            addPageDate = br.readLine();
            data = username +" " + addPageDate;
            //addPageDate += username +" "+addPageDate;
            pageService.addNewPage(data);
            } else {
                System.out.println("Не сте логирани");
                return;
            }
        }else if(n == 5){
            if(username != ""){
            System.out.println("Вие избравте 5 - Бришење на постоечка страница");
            System.out.println(pageService.getAllPage());
            System.out.println("Која страница сакате да ја избришете? Внесете ИД");
            idForDelete = Integer.parseInt(br.readLine());
            pageService.deletePage(idForDelete);
            } else {
                System.out.println("Не сте логирани");
                return;
            }
        }else if(n == 6) {
            if(username != ""){
            System.out.println("Вие избравте 6 - Гласање за страница");
            System.out.println(pageService.getAllPage());
            System.out.println("За која страница сакате да гласате? Внесете ИД и оцена во формат: \"ID Grade\"");
            voteData = br.readLine();
            pageService.vote(voteData, fileModel);
            } else {
                System.out.println("Не сте логирани");
                return;
            }
        }else if (n == 7) {
            if(username != ""){
            System.out.println("Вие избравте 7 - Филтрирање според рејтинг повисок од дадениот");
            System.out.println("Внесете рејтинг: ");
            rating = Integer.parseInt(br.readLine());
            pageService.getSortedByRating(rating);
            } else {
                System.out.println("Не сте логирани");
                return;
            }
        }else if (n == 8) {
            if(username != ""){
            System.out.println("Вие избравте 8 - Филтрирање според најнизок рејтинг");
            pageService.getPageByLowestRating();
            } else {
                System.out.println("Не сте логирани");
                return;
            }
        }else if (n == 9) {
            if(username != ""){
            System.out.println("Вие избравте 9 - Филтрирање според највисок рејтинг");
            pageService.getPageByHighestRating();
            } else {
                System.out.println("Не сте логирани");
                return;
            }
        }else if (n == 10) {
            if(username != ""){
          System.out.println("Вие избравте 10 - Филтрирање според најнови внесени");
          pageService.getNewest();
            } else {
                System.out.println("Не сте логирани");
                return;
            }
        }else if (n == 11) {
            if(username != " "){
            System.out.println("Вие избравте 11 - Филтрирање според најстари внесени");
            pageService.getOldest();
            } else {
                System.out.println("Не сте логирани");
                return;
            }
        } else {
            System.out.println("Невалиден внес, вие избравте: " + n);
        }
            System.out.println("Изберете број за тестирање:" );

        }

//        Users users = new Users();
//        if(n == 1){
//            System.out.println("Вие избравте 1 - Регистрација");
//            System.out.println("Внесете ги податоците во следниов формат: \"Username Password1 Password2\"");
//            registrationData = br.readLine();
//            userService.registration(registrationData);
//        }else if(n == 2){
//            System.out.println("Вие избравте 2 - Логин");
//            System.out.println("Внесете ги податоците во следниов формат: \"Username Password\"");
//            loginData = br.readLine();
//            userService.login(loginData);
//        }else if(n == 3){
//            System.out.println("Вие избравте 3 - Бришење на корисник");
//            System.out.println("Внесете ги податоците во следниов формат: \"Username Password1 Password2\"");
//            unRegisterData = br.readLine();
//            userService.deleteUser(unRegisterData);
//        }else if(n == 4) {
//            System.out.println("Вие избравте 4 - Додавање нова страница");
//            System.out.println("Внесете ги податоците во следниов формат: \"Username Title Url\"");
//            addPageDate = br.readLine();
//            pageService.addNewPage(addPageDate);
//        }else if(n == 5){
//            System.out.println("Вие избравте 5 - Бришење на постоечка страница");
//            System.out.println(pageService.getAllPage());
//            System.out.println("Која страница сакате да ја избришете? Внесете ИД");
//            idForDelete = Integer.parseInt(br.readLine());
//            pageService.deletePage(idForDelete);
//        }else if(n == 6) {
//            System.out.println("Вие избравте 6 - Гласање за страница");
//            System.out.println(pageService.getAllPage());
//            System.out.println("За која страница сакате да гласате? Внесете ИД и оцена во формат: \"ID Grade\"");
//            voteData = br.readLine();
//            pageService.vote(voteData);
//        }else if (n == 7) {
//            System.out.println("Вие избравте 7 - Филтрирање според рејтинг повисок од дадениот");
//            System.out.println("Внесете рејтинг: ");
//            rating = Integer.parseInt(br.readLine());
//            pageService.getSortedByRating(rating);
//        }else if (n == 8) {
//            System.out.println("Вие избравте 8 - Филтрирање според најнизок рејтинг");
//            pageService.getPageByLowestRating();
//        }else if (n == 9) {
//            System.out.println("Вие избравте 9 - Филтрирање според највисок рејтинг");
//            pageService.getPageByHighestRating();
//        }else if (n == 10) {
//          System.out.println("Вие избравте 10 - Филтрирање според најнови внесени");
//          pageService.getNewest();
//        }else if (n == 11) {
//            System.out.println("Вие избравте 11 - Филтрирање според најстари внесени");
//            pageService.getOldest();
//        } else {
//            System.out.println("Невалиден внес, вие избравте: " + n);
//        }


    }
}

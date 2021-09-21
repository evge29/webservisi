package com.example.demo.service;
import com.example.demo.model.Users;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;


@Service
public class UserService {

   Users users = new Users();

   String username = "";

    public String getUsernameLogged(){
        return username;
    }

    public void login(String loginData) throws IOException {

        boolean check;
        String[] arrOfStr = loginData.split(" ");
        String random = "";
        users.setUserName(arrOfStr[0]);
        users.setPassword1(hashPassword(arrOfStr[1]));
        check = checkUser(users);

        if(check) {
            random = generateSecureRandom();
            username = users.getUserName();
            System.out.println("Успешно најавен корисник " + users.getUserName() + " " + users.getPassword1() + ", Сесија: " + random );
        }
        else
            System.out.println("Внесовте погрешно корисничко име или лозинка");
    }

    public String generateSecureRandom(){
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(20);
        for( int i = 0; i < 20; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public void saveUserIntoFile(Users users) throws IOException {
        //String fileName = "C:\\Users\\HP\\Desktop\\Github\\WebPageService\\src\\main\\java\\com\\example\\demo\\files\\users";

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String pages = s + "\\src\\main\\java\\com\\example\\demo\\files\\users";
        String fileName = pages;

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String st;
        String content = "";
        while ((st = br.readLine()) != null){
            content += st + "\n";
        }

        System.out.println("content: " + content);

        if(content.length() > 0){
            content = content + "{ Username: " + users.getUserName() + ", Password: " + users.getPassword1() + " },\n";
        } else {
            content = "{ Username: " + users.getUserName() + ", Password: " + users.getPassword1() + " }, \n";
        }

        FileWriter fw = new FileWriter(fileName);

        fw.write(content);
        fw.flush();
        fw.close();

    }

    public String hashPassword(String pass){
        String hashPassword = Hashing.sha256()
                .hashString(pass, StandardCharsets.UTF_8)
                .toString();
        return hashPassword;
    }

    public Boolean checkUser(Users users) throws IOException {

        Boolean flag = false;

        ArrayList<Users> lista = getAllUsers();

        String UserName = users.getUserName();

        for(int i = 0; i < lista.size(); i++)
        {
            if(UserName.equals(lista.get(i).getUserName())){
                if(users.getPassword1().equals(lista.get(i).getPassword1())){
                    flag = true;
               }
            }
        }
        return flag;
    }

    public String registration(String registrationData) throws IOException {
        String[] arrOfStr = registrationData.split(" ");

        users.setUserName(arrOfStr[0]);
        System.out.println("hashPassword(arrOfStr[1])" + hashPassword(arrOfStr[1]));
        System.out.println("hashPassword(arrOfStr[2])" + hashPassword(arrOfStr[2]));

        users.setPassword1(hashPassword(arrOfStr[1]));
        users.setPassword2(hashPassword(arrOfStr[2]));

        if(users.getPassword1().equals(users.getPassword2())){
            saveUserIntoFile(users);
            System.out.println("Корисникот е успешно регистриран");
        } else {
            System.out.println("Лозинката не се совпаѓаа");
        }

        return "Test";
    }

    public ArrayList<Users> getAllUsers() throws IOException {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String pages = s + "\\src\\main\\java\\com\\example\\demo\\files\\users";
        String fileName = pages;
        //String fileName = "C:\\Users\\HP\\Desktop\\Github\\WebPageService\\src\\main\\java\\com\\example\\demo\\files\\users";

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String st;
        ArrayList<Users> lista = new ArrayList<>();
        String UserName = users.getUserName();
        while ((st = br.readLine()) != null){
            Users users1 = new Users();

            String[] fragments = st.split(",");

            String[] userName = fragments[0].split(" ");
            String[] password = fragments[1].split(" ");

            users1.setUserName(userName[2]);
            users1.setPassword1(password[2]);
            users1.setPassword2(password[2]);

            lista.add(users1);
        }
        return lista;
    }

    public boolean deleteUser(String unRegisterData) throws IOException {

        boolean flag = false;
        String[] arrOfStr = unRegisterData.split(" ");

        ArrayList<Users> lista = getAllUsers();

        if(hashPassword(arrOfStr[1]).equals(hashPassword(arrOfStr[2]))){
            for(int i = 0; i < lista.size();i++) {
                if (arrOfStr[0].equals(lista.get(i).getUserName())){
                    clearUsersFromFile(arrOfStr[0]);
                }
            }
        }
        return flag;
    }

    public boolean clearUsersFromFile(String username) throws IOException {

       // String fileName = "C:\\Users\\HP\\Desktop\\Github\\WebPageService\\src\\main\\java\\com\\example\\demo\\files\\users";

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String pages = s + "\\src\\main\\java\\com\\example\\demo\\files\\users";
        String fileName = pages;

        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String st;
        String content = "";
        while ((st = br.readLine()) != null){

            int deleteUserIndexStart = st.indexOf("{ Username: " +username);

            if(deleteUserIndexStart >= 0){

            } else {
                content += st + "\n";
            }
        }



        FileWriter fw = new FileWriter(fileName);

        fw.write(content);
        fw.flush();
        fw.close();
        System.out.println("Корисникот е успешно избришан");
        return true;
    }

}

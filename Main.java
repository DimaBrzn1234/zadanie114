package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService=new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Kirill","Savin", (byte) 33);
        userService.saveUser("Alex","Ivanov",(byte)24);
        userService.saveUser("Vlad","Ponamarev",(byte)19);
        userService.saveUser("Gera","Procopian",(byte)40);
        /*System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();*/
    }
}

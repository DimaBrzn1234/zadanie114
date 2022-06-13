package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {


    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "admin";
    private Connection connection;
    private static SessionFactory sessionFactory;



    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            try{
                Properties propert=new Properties();
                propert.setProperty("hibernate.connection.url",url);
                propert.setProperty("dialect", "org.hibernate.dialect.PostgresSQL");
                propert.setProperty("hibernate.connection.username", user);
                propert.setProperty("hibernate.connection.password", password);
                propert.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
                propert.setProperty("show_sql", String.valueOf(true));
                //Проперти для настройки подключения hibernate к postgresql
                sessionFactory= new Configuration().addProperties(propert).addAnnotatedClass(User.class).buildSessionFactory();


            } catch (Exception e) {
                System.out.println("Ошибка в классе Util");
            }
        }
        return sessionFactory;
    }


    public Util() {

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к БД");
        }
        //System.out.println("Соединение установлено");
    }

   public Connection getConnection() {
        return connection;
    }

}
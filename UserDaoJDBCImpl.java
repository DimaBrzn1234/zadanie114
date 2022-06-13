package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS UserTable ( id BIGSERIAL PRIMARY KEY," +
                " name VARCHAR(15),last_name VARCHAR(15)," +
                "age SMALLINT)";

        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute(createTable);

        } catch (SQLException e) {
            System.out.println("Таблица не создана !!!");
        }
        System.out.println("Таблица созданна успешно");
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS UserTable";
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute(dropTable);

        } catch (SQLException exception) {
            System.out.println("Таблица не удалена!!!");
        }
        System.out.println("Таблица успешно удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO UserTable (name,last_name,age) VALUES(?,?,?)";
        try (PreparedStatement prepareStatement = new Util().getConnection().prepareStatement(saveUser)) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Пользователь не добавлен!");
        }
        System.out.println("Пользователь c именем "+ name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {

        String deleteUser = "DELETE FROM UserTable WHERE id =?";
        try (PreparedStatement prepareStatement = new Util().getConnection().prepareStatement(deleteUser)) {
            prepareStatement.setLong(1, id);
            prepareStatement.execute();
        } catch (SQLException e) {
            System.out.println("Такого пользователя не существует!");
        }
        System.out.println("Пользователь успешно удален");
    }

    public List<User> getAllUsers() {
        String getUsers = "SELECT * FROM UserTable ";
        ArrayList listUser=new ArrayList();
        try (PreparedStatement prepareStatement = new Util().getConnection().prepareStatement(getUsers)) {
            ResultSet resultSet = prepareStatement.executeQuery();
            User user = null;

            while (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")
                );
                listUser.add(user);
            }
            return listUser;

        } catch (SQLException e) {
            System.out.println("Ошибка вывода всей информации из таблицы");
            return listUser;
        }

    }

    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE UserTable";
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute(cleanTable);

        } catch (SQLException exception) {
            System.out.println("Cтроки НЕ удалены");
        }
        System.out.println("Строки удалены");
    }

}

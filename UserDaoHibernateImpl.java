package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
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

    @Override
    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS UserTable";
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute(dropTable);

        } catch (SQLException exception) {
            System.out.println("Таблица не удалена!!!");
        }
        System.out.println("Таблица успешно удалена");
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {


            Session session = Util.getSessionFactory().openSession();

            Transaction transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("Пользователь добавлен " + name);
        } catch (HibernateException e) {
            System.out.println("Пользователь не добавлен!!!");
        } finally {


            Util.getSessionFactory().close();
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}

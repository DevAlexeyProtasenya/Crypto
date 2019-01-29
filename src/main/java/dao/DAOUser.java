package dao;

import db.ConnectionPool;
import entity.User;
import exceptions.DAONullException;
import exceptions.ResultSetException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUser implements DAOAbstract<User> {
    public static final Logger LOGGER = LogManager.getLogger(DAOUser.class);

    private static final String DATA = "Some of data";

    private static final String CLASS = "Users";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";

    private static final String SQL_SELECT_ALL = "SELECT login, password, email, role FROM users ORDER BY login";
    private static final String SQL_FIND_BY_LOGIN = "SELECT login, password, email, role FROM users WHERE login=?";
    private static final String SQL_UPDATE = "UPDATE users SET password=?, email=?, role=? WHERE login=?";
    private static final String SQL_DELETE = "DELETE FROM users WHERE login=?";

    private static final String SQL_ADD_USER = "INSERT INTO users(login, password, email, role) VALUES(?,?,?,?)";
    private static final String SQL_NEW_LOGIN = "UPDATE users SET login=? WHERE login=?";
    private static final String SQL_NEW_PASSWORD = "UPDATE users SET password=? WHERE login=?";
    private static final String SQL_NEW_EMAIL = "UPDATE users SET email=? WHERE login=?";
    private static final String SQL_SELECT_ALL_CLIENTS = "SELECT login, password, email, role FROM users WHERE role='client' ORDER BY login";

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
            LOGGER.log(Level.INFO, "Received all users from database");
        } catch (SQLException e) {
            LOGGER.error("SQLException select all users", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return users;
    }

    @Override
    public User get(String login) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new DAONullException(CLASS);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
            }
            LOGGER.log(Level.INFO, "Received user from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take user", e);
        } catch (DAONullException e) {
            e.getMessage();
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (user == null) {
                throw new DAONullException(CLASS);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getRole());
            statement.setString(4, user.getLogin());
            if (statement.executeUpdate() != 0) {
                isUpdated = true;
            }
            LOGGER.log(Level.INFO, "Updated user in database");
        } catch (SQLException e) {
            LOGGER.error("SQLException update user", e);
        } catch (DAONullException e) {
            e.getMessage();
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(User user) {
        boolean isDeleted = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (user == null) {
                throw new DAONullException(CLASS);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setString(1, user.getLogin());
            if (statement.executeUpdate() != 0) {
                isDeleted = true;
            }
            LOGGER.log(Level.INFO, "Deleted user in the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to delete user", e);
        } catch (DAONullException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isDeleted;
    }

    private User createUser(ResultSet resultSet){
        User user = null;
        try {
            if (resultSet == null) {
                throw new ResultSetException();
            }
            String login = resultSet.getString(LOGIN);
            String password = resultSet.getString(PASSWORD);
            String email = resultSet.getString(EMAIL);
            String role = resultSet.getString(ROLE);
            user = new User(login, password, email, role);
        } catch (ResultSetException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQL exception!", e);
        }
        return user;
    }

    public String getPassword(String login) {
        String pass = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            if(login == null || login.isEmpty()){
                throw new DAONullException(LOGIN);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_LOGIN);
            statement.setString(1,login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                pass = resultSet.getString(PASSWORD);
            }
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException find password", e);
        } finally {
            if(connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return pass;
    }

    public boolean addUser(String login, String pass, String email, String role) {
        boolean isAdded = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty() || pass == null || pass.isEmpty() || email == null || email.isEmpty()) {
                throw new DAONullException(DATA);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_USER);
            statement.setString(1, login);
            statement.setString(2, pass);
            statement.setString(3, email);
            statement.setString(4, role);
            if (statement.executeUpdate() != 0) {
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "User was added");
        } catch (SQLException e) {
            LOGGER.error("SQLException add user", e);
        } catch (DAONullException e) {
            e.getMessage();
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isAdded;
    }

    public boolean newLogin(String login, String newLogin){
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            if(login == null || login.isEmpty()){
                throw new DAONullException("Login ");
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_NEW_LOGIN);
            statement.setString(1,newLogin);
            statement.setString(2,login);
            if(statement.executeUpdate() != 0){
                isUpdated = true;
            }
            LOGGER.log(Level.INFO, "Login was updated");
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException update login", e);
        } finally {
            if (connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isUpdated;
    }

    public boolean newPassword(String login, String newPassword){
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            if(login == null || login.isEmpty()){
                throw new DAONullException("Login ");
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_NEW_PASSWORD);
            statement.setString(1,newPassword);
            statement.setString(2,login);
            if(statement.executeUpdate() != 0){
                isUpdated = true;
            }
            LOGGER.log(Level.INFO, "Password was updated");
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException update password", e);
        } finally {
            if (connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isUpdated;
    }

    public boolean newEmail(String login, String newEmail){
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            if(login == null || login.isEmpty()){
                throw new DAONullException("Email ");
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_NEW_EMAIL);
            statement.setString(1,newEmail);
            statement.setString(2,login);
            if(statement.executeUpdate() != 0){
                isUpdated = true;
            }
            LOGGER.log(Level.INFO, "Email was updated");
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException update email", e);
        } finally {
            if (connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isUpdated;
    }
}

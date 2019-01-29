package dao;

import control.Configuration;
import db.ConnectionPool;
import entity.Order;
import exceptions.DAONullException;
import exceptions.ResultSetException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOOrder implements DAOAbstract<Order> {

    public static final Logger LOGGER = LogManager.getLogger(DAOOrder.class);

    private static final String CLASS = "DAOOrder";
    private static final String ID = "idOrder";
    private static final String LOGIN = "login";
    private static final String CREDIT_CARD = "creditCard";
    private static final String NAME_ON_CARD = "nameOnCard";
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String CVV = "cvv";

    private static final String SELECT_ALL = "SELECT idOrder, creditCard, login, nameOnCard, year, month, cvv FROM order";
    private static final String SELECT_BY_ID = "SELECT idOrder, creditCard, login, nameOnCard, year, month, cvv FROM order WHERE idOrder=?";
    private static final String SQL_UPDATE = "UPDATE order SET idOrder=?, creditCard=?, login=?, nameOnCard=?, year=?, month=?, cvv=? WHERE idOrder=?";
    private static final String SQL_DELETE = "DELETE FROM `crypto`.order WHERE idOrder=?";
    private static final String SQL_ADD_ORDER = "INSERT INTO `crypto`.order(creditCard, login, nameOnCard, year, month, cvv ) VALUES(?,?,?,?,?,?)";
    private static final String SQL_ADD_ORDER_LIST = "INSERT INTO `crypto`.order_list(idOrder, idCrypto, amount ) VALUES(?,?,?)";

    @Override
    public List<Order> selectAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        connection=ConnectionPool.getInstance().takeConnection();
        try {
            statement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Order order = createOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException DAOOrder SELECT_ALL");
        }
        return orders;
    }

    @Override
    public Order get(String id) {
        Order order = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (id == null && id.isEmpty()) {
                throw new DAONullException(CLASS);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            int idOrder = Integer.parseInt(id);
            statement.setInt(1, idOrder);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = createOrder(resultSet);
            }
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException update orders", e);
        } finally {
            if(connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return order;
    }

    @Override
    public boolean update(Order object) {
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if(object == null){
                throw new DAONullException(CLASS);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, object.getIdOrder());
            statement.setString(2, object.getCreditCard());
            statement.setString(3, object.getLogin());
            statement.setString(4, object.getNameOnCard());
            statement.setString(5, object.getYear());
            statement.setString(6, object.getMonth());
            statement.setString(7, object.getCvv());
            statement.setInt(8, object.getIdOrder());
            if(statement.executeUpdate() != 0){
                isUpdated = true;
            }
            LOGGER.log(Level.INFO, "Data order updated");
        }
        catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException update order", e);
        } finally {
            if(connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Order object) {
        Boolean isDeleted = false;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            if (object == null) {
                throw new DAONullException(CLASS);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, object.getIdOrder());
            if(statement.executeUpdate() != 0){
                isDeleted = true;
            }
            LOGGER.log(Level.INFO, "Order was deleted");
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException delete order", e);
        }
        return isDeleted;
    }

    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = null;
        try {
            if (resultSet == null) {
                throw new ResultSetException();
            }
            int id = resultSet.getInt(ID);
            String login = resultSet.getString(LOGIN);
            String cardNumber = resultSet.getString(CREDIT_CARD);
            String nameOnCard = resultSet.getString(NAME_ON_CARD);
            String year = resultSet.getString(YEAR);
            String month = resultSet.getString(MONTH);
            String cvv = resultSet.getString(CVV);
            order = new Order(id, cardNumber, login, nameOnCard, year, month, cvv);
        } catch (ResultSetException e) {
            e.getMessage();
        }
        return order;
    }

    public Order addOrder(String login, String nameOnCard, String creditCard, String monthOnCard, String yearOnCard, String cvv){
        Order order = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            if(login == null || login.isEmpty() || nameOnCard == null || nameOnCard.isEmpty() || creditCard == null ||
                    creditCard.isEmpty() || monthOnCard == null || monthOnCard.isEmpty() || yearOnCard == null ||
                    yearOnCard.isEmpty() || cvv == null || cvv.isEmpty()){
                throw new DAONullException("Data ");
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nameOnCard);
            statement.setString(2, login);
            statement.setString(3, nameOnCard);
            statement.setString(4, yearOnCard);
            statement.setString(5, monthOnCard);
            statement.setString(6, cvv);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            order = new Order(resultSet.getInt(1), nameOnCard, login, nameOnCard, yearOnCard, monthOnCard, cvv);
            LOGGER.log(Level.INFO, "Order was added!");
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException Order Add!", e);
        } finally {
            if(connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return order;
    }

    public void addCrypto(int idOrder, int idCrypto, String amount){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            if(idOrder<1 || idCrypto<1 || Integer.parseInt(amount)<1){
                throw new DAONullException("Data ");
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_ORDER_LIST);
            statement.setInt(1, idOrder);
            statement.setInt(2, idCrypto);
            statement.setString(3, amount);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Order was added in OrderList");

        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException Order Add in OrderList!", e);
        } finally {
            if(connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }
}

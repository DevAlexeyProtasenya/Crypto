package dao;

import db.ConnectionPool;
import entity.Crypto;
import entity.CryptoList;
import exceptions.DAONullException;
import exceptions.GetException;
import exceptions.ResultSetException;
import org.apache.commons.codec.digest.Crypt;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DAOCrypto implements DAOAbstract<Crypto>{
    public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(DAOCrypto.class);

    private static final String CLASS = "Crypto";

    private static final String ID_CRYPTO = "idCrypto";
    private static final String NAME_CRYPTO = "name";
    private static final String ABBREVIATION_CRYPTO = "abbreviation";
    private static final String IMAGE_CRYPTO = "image";
    private static final String INFO_CRYPTO = "info";

    private static final String SQL_SELECT_ALL = "SELECT idCrypto, name, abbreviation, image, info FROM crypto;";
    private static final String SQL_FIND_BY_ID = "SELECT idCrypto, name, abbreviation, image, info FROM crypto WHERE idCrypto=?";
    private static final String SQL_FIND_BY_NAME = "SELECT idCrypto, name, abbreviation, image, info FROM crypto WHERE name=?";
    private static final String SQL_FIND_BY_ABBREVIATION = "SELECT idCrypto, name, abbreviation, image, info FROM crypto WHERE abbreviation=?";
    private static final String SQL_UPDATE_DATA = "UPDATE crypto SET name=?, abbreviation=?, image=?, info=? WHERE idCrypto=?";
    private static final String SQL_DELETE_DATA = "DELETE FROM crypto WHERE idCrypto=?";
    private static final String SQL_ADD_CRYPTO = "INSERT INTO crypto(name, abbreviation, image, info) VALUE (?,?,?,?)";
    private static final String SQL_ADD_TO_LIST = "SELECT `crypto`.idCrypto, `crypto`.name, abbreviation, image, info, amount, `order`.idOrder FROM order_list\n" +
            "JOIN crypto\n" +
            "ON order_list.idCrypto = crypto.idCrypto\n" +
            "JOIN `order`\n" +
            "ON order_list.idOrder = order.idOrder\n"+
            "WHERE `order`.login = ?\n";
    private static final String SQL_SELL_ORDER = "DELETE FROM order_list WHERE idCrypto=? AND idOrder=?";

    @Override
    public List<Crypto> selectAll() {
        List<Crypto> cryptos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        connection = ConnectionPool.getInstance().takeConnection();
        try {
            statement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Crypto crypto = createCrypto(resultSet);
                cryptos.add(crypto);
            }
            LOGGER.log(Level.INFO, "Received all cryptos from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException cryptos Select All", e);
        } finally {
            if (connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return cryptos;
    }

    @Override
    public Crypto get(String idCrypto) {
        Crypto crypto = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if(idCrypto == null || idCrypto.isEmpty()){
                throw new GetException(CLASS);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            int id = Integer.parseInt(idCrypto);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                crypto = createCrypto(resultSet);
            }
            LOGGER.log(Level.INFO, "Received crypto from database!");
        } catch (GetException e) {
            e.getMessage();
        } catch (NumberFormatException e){
            LOGGER.error("Cannot convert to int!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException cryptos Select by id", e);
        } finally {
            if(connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return crypto;
    }

    @Override
    public boolean update(Crypto crypto) {
        boolean updated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            if (crypto == null){
                throw new DAONullException(CLASS);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_DATA);
            statement.setString(1,crypto.getNameCrypto());
            statement.setString(2,crypto.getAbbreviationCrypto());
            statement.setString(3,crypto.getIconCrypto());
            statement.setString(3,crypto.getInfoCrypto());
            statement.setInt(5, crypto.getIdCrypto());
            LOGGER.log(Level.INFO, "Data updated!");
            if(statement.executeUpdate() != 0){
                updated = true;
            }
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException cryptos update", e);
        } finally {
            if(connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return updated;
    }

    @Override
    public boolean delete(Crypto crypto) {
        boolean deleted = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (crypto == null) {
                throw new DAONullException(CLASS);
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_DATA);
            statement.setInt(1, crypto.getIdCrypto());
            if (statement.executeUpdate() != 0) {
                deleted = true;
            }
            LOGGER.log(Level.INFO, "Crypto was deleted!");
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException crypto delete!", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return deleted;
    }

    private Crypto createCrypto(ResultSet resultSet){
        Crypto crypto = null;
        try{
            if(resultSet == null){
                throw new ResultSetException();
            }
            int id = resultSet.getInt(ID_CRYPTO);
            String nameCrypto = resultSet.getString(NAME_CRYPTO);
            String abbreviationCrypto = resultSet.getString(ABBREVIATION_CRYPTO);
            String imageCrypto = resultSet.getString(IMAGE_CRYPTO);
            String infoCrypto = resultSet.getString(INFO_CRYPTO);
            crypto = new Crypto(id, nameCrypto, abbreviationCrypto, imageCrypto, infoCrypto);
        } catch (ResultSetException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQL exception!", e);
        }
        return crypto;
    }

    private CryptoList createCryptoList(ResultSet resultSet){
        CryptoList crypto = null;
        try{
            if(resultSet == null){
                throw new ResultSetException();
            }
            int id = resultSet.getInt(ID_CRYPTO);
            int idOrder = resultSet.getInt("idOrder");
            String nameCrypto = resultSet.getString(NAME_CRYPTO);
            String abbreviationCrypto = resultSet.getString(ABBREVIATION_CRYPTO);
            String imageCrypto = resultSet.getString(IMAGE_CRYPTO);
            String infoCrypto = resultSet.getString(INFO_CRYPTO);
            String amount = resultSet.getString("amount");
            crypto = new CryptoList(id, idOrder, nameCrypto, abbreviationCrypto, imageCrypto, infoCrypto, amount);
        } catch (ResultSetException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQL exception!", e);
        }
        return crypto;
    }

    public Crypto addCrypto(String nameCrypto, String abbreviationCrypto, String imageCrypto, String infoCrypto){
        Crypto crypto = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            if(nameCrypto == null || abbreviationCrypto == null || imageCrypto == null ||
                    nameCrypto.isEmpty() || abbreviationCrypto.isEmpty()){
                throw new DAONullException(CLASS);
            }
        crypto = getAndCheck(selectAll(), nameCrypto, abbreviationCrypto, imageCrypto, infoCrypto);
        if(crypto == null){
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_CRYPTO);
            statement.setString(1,nameCrypto);
            statement.setString(2,abbreviationCrypto);
            statement.setString(3,imageCrypto);
            statement.setString(4,infoCrypto);
            statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            crypto = new Crypto(resultSet.getInt(1), nameCrypto, abbreviationCrypto, imageCrypto, infoCrypto);
        }
        LOGGER.log(Level.INFO, "Crypto was added");
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException crypto add!", e);
        }
        return crypto;
    }

    private Crypto getAndCheck(List<Crypto> cryptos, String nameCrypto, String abbreviationCrypto, String imageCrypto, String infoCrypto){
        for(int i = 0; i<cryptos.size(); i++){
            String name = cryptos.get(i).getNameCrypto();
            String abbreviation = cryptos.get(i).getAbbreviationCrypto();
            String image = cryptos.get(i).getIconCrypto();
            String info = cryptos.get(i).getInfoCrypto();
            if(name.equals(nameCrypto) && abbreviation.equals(abbreviationCrypto) && image.equals(imageCrypto) && info.equals(infoCrypto)){
                return cryptos.get(i);
            }
        }
        return null;
    }

    public Crypto getByName(String nameCrypto) {
        Crypto crypto = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if(nameCrypto == null || nameCrypto.isEmpty()){
                throw new GetException("Name of crypto ");
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_NAME);
            String name = nameCrypto.toString();
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                crypto = createCrypto(resultSet);
            }
            LOGGER.log(Level.INFO, "Received crypto from database!");
        } catch (GetException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException cryptos Select by name", e);
        } finally {
            if(connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return crypto;
    }

    public Crypto getByAbbreviation(String abbreviation) {
        Crypto crypto = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if(abbreviation == null || abbreviation.isEmpty()){
                throw new GetException("Abbreviation ");
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_ABBREVIATION);
            String abbr = abbreviation.toString();
            statement.setString(1, abbr);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                crypto = createCrypto(resultSet);
            }
            LOGGER.log(Level.INFO, "Received crypto from database!");
        } catch (GetException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException cryptos Select by name", e);
        } finally {
            if(connection != null){
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return crypto;
    }

    public List<CryptoList> findOrderList(String login){
        List<CryptoList> cryptos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            if (login == null || login.isEmpty()){
                throw new DAONullException("Login ");
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_TO_LIST);
            statement.setString(1,login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CryptoList crypto = createCryptoList(resultSet);
                cryptos.add(crypto);
            }
        } catch (DAONullException e) {
            e.getMessage();
        } catch (SQLException e) {
            LOGGER.error("SQLException Find OrderList!", e);
        }
        return cryptos;
    }

    public boolean sellOrder(String idOrder, String idCrypto){
        boolean deleted = false;
        Connection connection = null;
        PreparedStatement statement = null;
        connection = ConnectionPool.getInstance().takeConnection();
        try {
            statement = connection.prepareStatement(SQL_SELL_ORDER);
            statement.setInt(1,Integer.parseInt(idCrypto));
            statement.setInt(2,Integer.parseInt(idOrder));
            if(statement.executeUpdate() != 0){
                deleted = true;
            }
            LOGGER.log(Level.INFO, "Order was deleted!");
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"SQLException sell Order!");
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return deleted;
    }
}

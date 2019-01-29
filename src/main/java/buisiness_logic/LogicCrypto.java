package buisiness_logic;

import dao.DAOCrypto;
import entity.Crypto;
import entity.CryptoList;
import exceptions.LogicNullException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LogicCrypto {
    private static final Logger LOGGER = LogManager.getLogger(LogicCrypto.class);


    public List<Crypto> selectAll() {
        DAOCrypto daoCrypto = new DAOCrypto();
        return daoCrypto.selectAll();
    }

    public Crypto getCrypto(String idCrypto){
        Crypto crypto = null;
        try{
            if(idCrypto == null || idCrypto.isEmpty()){
                throw new LogicNullException("idCrypto ");
            }
            DAOCrypto daoCrypto = new DAOCrypto();
            crypto = daoCrypto.get(idCrypto);
            LOGGER.log(Level.INFO, "Retrieved crypto");
        } catch (LogicNullException e) {
            e.printStackTrace();
        }
        return crypto;
    }

    public Crypto getCryptoByname(String cryptoName){
        Crypto crypto = null;
        try{
            if(cryptoName == null || cryptoName.isEmpty()){
                throw new LogicNullException("Name ");
            }
            DAOCrypto daoCrypto = new DAOCrypto();
            crypto = daoCrypto.getByName(cryptoName);
            LOGGER.log(Level.INFO, "Retrieved crypto");
        } catch (LogicNullException e) {
            e.printStackTrace();
        }
        return crypto;
    }

    public Crypto getCryptoByAbbreviation(String abbreviation){
        Crypto crypto = null;
        try{
            if(abbreviation == null || abbreviation.isEmpty()){
                throw new LogicNullException("Abbreviation ");
            }
            DAOCrypto daoCrypto = new DAOCrypto();
            crypto = daoCrypto.getByAbbreviation(abbreviation);
            LOGGER.log(Level.INFO, "Retrieved crypto");
        } catch (LogicNullException e) {
            e.printStackTrace();
        }
        return crypto;
    }

    public List<CryptoList> getCryptoByLogin(String login){
        List<CryptoList> cryptos  = null;
        try{
            if(login == null || login.isEmpty()){
                throw new LogicNullException("Login ");
            }
            DAOCrypto daoCrypto = new DAOCrypto();
            cryptos = daoCrypto.findOrderList(login);
            LOGGER.log(Level.INFO, "OrderList was taken");
        } catch (LogicNullException e) {
            e.getMessage();
        }
        return cryptos;
    }

    public boolean sellCrypto(String idOrder, String idCrypto){
        boolean sell = false;
        try{
            if(idCrypto == null || idCrypto.isEmpty() || idOrder == null || idOrder.isEmpty()){
                throw new LogicNullException("Order or Crypto ");
            }
            DAOCrypto daoCrypto = new DAOCrypto();
            sell = daoCrypto.sellOrder(idOrder, idCrypto);
        } catch (LogicNullException e) {
            e.getMessage();
        }
        return sell;
    }
}

package buisiness_logic;

import dao.DAOOrder;
import entity.Crypto;
import entity.Order;
import exceptions.LogicNullException;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogicOrder {

    private static final Logger LOGGER = LogManager.getLogger(LogicOrder.class);

    /*public boolean isOrder(String login, int idCrypto){
        try{
            if(login == null || login.isEmpty()){
                throw new LogicNullException("Login ");
            }
            DAOOrder daoOrder = new DAOOrder();
            List<Integer> idCryptos = daoOrder.getIdCryptoOfUser(login);
            for (int i = 0; i < idCryptos.size(); i++){
                if(idCryptos.get(i) == idCrypto){
                    LOGGER.log(Level.INFO, "Crypto is already ordered");
                    return true;
                }
            }
            LOGGER.log(Level.INFO, "Crypto isn't ordered");
        } catch (LogicNullException e) {
            e.getMessage();
        }
        return false;
    }*/

    public boolean addOrder(String login, String nameOnCard, String creditCard, String monthOnCard, String yearOnCard, String cvvOnCard, Set orderL, Set amountL){
        try{
            if(login == null || login.isEmpty() || nameOnCard == null || nameOnCard.isEmpty() || creditCard == null ||
                    creditCard.isEmpty() || monthOnCard == null || monthOnCard.isEmpty() || yearOnCard == null ||
                    yearOnCard.isEmpty() || cvvOnCard == null || cvvOnCard.isEmpty()){
                throw new LogicNullException("Data ");
            }
            DAOOrder daoOrder = new DAOOrder();
            Order order = daoOrder.addOrder(login, nameOnCard, creditCard, monthOnCard, yearOnCard, cvvOnCard);
            int orderId = order.getIdOrder();
            Iterator orderIterator = orderL.iterator();
            Iterator amountIterator = amountL.iterator();
            while (orderIterator.hasNext()) {
                Crypto crypto = (Crypto) orderIterator.next();
                String amount = (String) amountIterator.next();
                int idCrypto = crypto.getIdCrypto();
                daoOrder.addCrypto(orderId, idCrypto, amount);
            }
            LOGGER.log(Level.INFO, "Crypto are ordered");
        } catch (LogicNullException e) {
            e.getMessage();
            return false;
        }
        return true;
    }
}

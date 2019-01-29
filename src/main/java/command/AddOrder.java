package command;

import buisiness_logic.LogicOrder;
import control.Configuration;
import control.TextLanguage;
import entity.Crypto;
import entity.User;
import servlet.SessionRequestContent;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AddOrder implements ActionCommand {

    private static final String MAIN_ACTION = "/web?command=main";
    private static final String BASKET = "path.basket";
    private static final String WRONG_ADD = "config.error.addWrongList";
    private static final String LOGIN = "path.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        if(isSignIn.equals("true")){
            User user = (User) requestContent.getSessionAttributeValue("user");
            String login = user.getLogin();
            String nameOnCard = requestContent.getRequestParameterValue("nameOnCard");
            String creditCard = requestContent.getRequestParameterValue("creditCard");
            String monthOnCard = requestContent.getRequestParameterValue("monthOnCard");
            String yearOnCard = requestContent.getRequestParameterValue("yearOnCard");
            String cvvOnCard = requestContent.getRequestParameterValue("cvvOnCard");
            Set orderList = (HashSet) requestContent.getSessionAttributeValue("order_list");
            Set amountList = (HashSet) requestContent.getSessionAttributeValue("amount_list");
            Iterator orderIterator = orderList.iterator();
            while (orderIterator.hasNext()) {
                Crypto crypto = (Crypto) orderIterator.next();
                String abbrev = crypto.getAbbreviationCrypto();
                amountList.add(requestContent.getRequestParameterValue("amount"+abbrev));
            }
            requestContent.setSessionAttributeValue("amount_list", amountList);
            LogicOrder logicOrder = new LogicOrder();
            if(logicOrder.addOrder(login, nameOnCard,creditCard, monthOnCard, yearOnCard, cvvOnCard, orderList,amountList)){
                orderList.clear();
                amountList.clear();
                requestContent.setSessionAttributeValue("order-list", orderList);
                requestContent.setSessionAttributeValue("amount_list", amountList);
                page = MAIN_ACTION;
            }else{
                String pageMessage = TextLanguage.getText(WRONG_ADD,
                        (String) requestContent.getSessionAttributeValue("localization"));
                requestContent.setRequestAttributeValue("wrong", pageMessage);
                page = Configuration.getProperties(BASKET);
            }
        } else {
            page = Configuration.getProperties(LOGIN);
        }
        return page;
    }
}

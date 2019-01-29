package command;

import buisiness_logic.LogicCrypto;
import control.Configuration;
import entity.Crypto;
import servlet.SessionRequestContent;

import java.util.HashSet;
import java.util.Set;

public class DeleteCryptoOrder implements ActionCommand {
    private static final String BASKET = "path.basket";
    private static final String LOGIN_PATH = "path.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        String idCrypto = (String) requestContent.getRequestParameterValue("cryptoid");
        LogicCrypto logicCrypto = new LogicCrypto();
        if(isSignIn.equals("true")){
           Set orderList = (HashSet) requestContent.getSessionAttributeValue("order_list");
           Crypto crypto = logicCrypto.getCrypto(idCrypto);
           orderList.remove(crypto);
            requestContent.setSessionAttributeValue("order_list", orderList);
            page = Configuration.getProperties(BASKET);
        } else {
            page = Configuration.getProperties(LOGIN_PATH);
        }
        return page;
    }
}

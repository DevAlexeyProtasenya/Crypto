package command;

import buisiness_logic.LogicCrypto;
import control.Configuration;
import entity.Crypto;
import servlet.SessionRequestContent;

import java.util.HashSet;
import java.util.Set;

public class AddCryptoOrder implements ActionCommand{

    private static final String MAIN_ACTION = "/web?command=main";
    private static final String LOGIN_PATH = "path.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String)requestContent.getSessionAttributeValue("isSignIn");
        String idCrypto = (String) requestContent.getRequestParameterValue("cryptoid");
        LogicCrypto logicCrypto = new LogicCrypto();
        if(isSignIn.equals("true")){
            Crypto crypto = logicCrypto.getCrypto(idCrypto);
            Set orderList = (HashSet) requestContent.getSessionAttributeValue("order_list");
            orderList.add(crypto);
            requestContent.setSessionAttributeValue("order_list", orderList);
            page = MAIN_ACTION;
        } else {
            page = Configuration.getProperties(LOGIN_PATH);
        }
        return page;
    }
}

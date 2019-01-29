package command;

import buisiness_logic.LogicCrypto;
import control.Configuration;
import entity.CryptoList;
import entity.User;
import servlet.SessionRequestContent;

import java.util.List;

public class ViewMyCrypto implements ActionCommand{

    private static final String PAGE_TRANZ = "path.tranz";
    private static final String LOGIN = "path.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        if(isSignIn.equals("true")){
            User user = (User) requestContent.getSessionAttributeValue("user");
            String login = user.getLogin();
            List<CryptoList> cryptos;
            LogicCrypto logicCrypto = new LogicCrypto();
            cryptos = logicCrypto.getCryptoByLogin(login);
            requestContent.setRequestAttributeValue("tranzaction",cryptos);
            page = Configuration.getProperties(PAGE_TRANZ);
        } else {
            page = Configuration.getProperties(LOGIN);
        }
        return page;
    }
}

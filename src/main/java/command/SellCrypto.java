package command;

import buisiness_logic.LogicCrypto;
import control.Configuration;
import servlet.SessionRequestContent;

public class SellCrypto implements ActionCommand {

    private static final String PAGE_TRANZ = "/web?command=view_my_crypto";
    private static final String LOGIN = "path.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        LogicCrypto logicCrypto = new LogicCrypto();
        if(isSignIn.equals("true")){
            String idOrder = (String) requestContent.getRequestParameterValue("idOrder");
            String idCrypto = (String) requestContent.getRequestParameterValue("idCrypto");
            if(logicCrypto.sellCrypto(idOrder, idCrypto)){
                page = PAGE_TRANZ;
            } else {
                page = PAGE_TRANZ;
            }
        } else {
            page = Configuration.getProperties(LOGIN);

        }
        return page;
    }
}

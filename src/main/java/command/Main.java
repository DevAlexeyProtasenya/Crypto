package command;

import buisiness_logic.LogicCrypto;
import entity.Crypto;
import servlet.SessionRequestContent;
import control.Configuration;

import java.util.List;

public class Main implements ActionCommand {
    private static final String MAIN = "path.main";
    private static final String SIGN_IN = "path.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        if ("true".equals(isSignIn)) {
            List<Crypto> cryptos;
            LogicCrypto logicCrypto = new LogicCrypto();
            cryptos = logicCrypto.selectAll();
            requestContent.setRequestAttributeValue("crypto", cryptos);
            page = Configuration.getProperties(MAIN);
        } else {
            page = Configuration.getProperties(SIGN_IN);
        }
        return page;
    }
}

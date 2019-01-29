package command;

import control.Configuration;
import servlet.SessionRequestContent;

public class Logout implements ActionCommand {
    private static final String SIGN_IN_PAGE = "path.login";
    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        if(isSignIn.equals("true")){
            requestContent.setSessionAttributeValue("isSignIn", null);
            requestContent.setSessionAttributeValue("user", null);
            requestContent.setSessionAttributeValue("crypto", null);
            requestContent.setSessionAttributeValue("cryptos", null);
        }
        page = Configuration.getProperties(SIGN_IN_PAGE);
        return page;
    }
}

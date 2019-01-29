package command;

import control.Configuration;
import servlet.SessionRequestContent;

public class Language implements ActionCommand{
    private static final String LANGUAGE = "lang";
    private static final String MAIN = "/web?command=main";
    private static final String SIGNIN = "path.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String language = requestContent.getRequestParameterValue(LANGUAGE);
        if(language != null){
            requestContent.setSessionAttributeValue("localization", language);
        }
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        if(isSignIn==null){
            page = Configuration.getProperties(SIGNIN);
        } else {
            page = MAIN;
        }
        return page;
    }
}

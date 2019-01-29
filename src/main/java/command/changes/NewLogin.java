package command.changes;

import buisiness_logic.LogicUser;
import command.ActionCommand;
import control.Configuration;
import control.TextLanguage;
import entity.User;
import servlet.SessionRequestContent;

public class NewLogin implements ActionCommand {
    private static final String ACCOUNT = "path.account";
    private static final String SIGNIN = "path.login";
    private static final String LOG = "config.account.successNewLogin";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        if(isSignIn.equals("true")){
            String newLogin = requestContent.getRequestParameterValue("nickname");
            User user = (User)requestContent.getSessionAttributeValue("user");
            LogicUser logicUser = new LogicUser();
            String message = logicUser.newLogin(user.getLogin(), newLogin);
            if(message.isEmpty()){
                user.setLogin(newLogin);
                requestContent.setSessionAttributeValue("user", user);
                String log = TextLanguage.getText(LOG, (String) requestContent.getSessionAttributeValue("localization"));
                requestContent.setRequestAttributeValue("newLogin", log);
            } else {
                String error = TextLanguage.getText(message, (String) requestContent.getSessionAttributeValue("localization"));
                requestContent.setRequestAttributeValue("newLogin", error);
            }
            page = Configuration.getProperties(ACCOUNT);
        } else {
            page = Configuration.getProperties(SIGNIN);
        }
        return page;
    }
}

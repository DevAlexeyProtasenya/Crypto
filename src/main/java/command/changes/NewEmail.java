package command.changes;

import buisiness_logic.LogicUser;
import command.ActionCommand;
import control.Configuration;
import control.TextLanguage;
import entity.User;
import servlet.SessionRequestContent;

public class NewEmail implements ActionCommand {
    private static final String EMAIL = "config.account.successNewEmail";
    private static final String ACCOUNT = "path.account";
    private static final String SIGNIN = "path.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        if(isSignIn.equals("true")){
            String newEmail= requestContent.getRequestParameterValue("email");
            User user = (User)requestContent.getSessionAttributeValue("user");
            LogicUser logicUser = new LogicUser();
            String message = logicUser.newEmail(user.getLogin(), newEmail);
            if(message.isEmpty()){
                user.setEmail(newEmail);
                requestContent.setSessionAttributeValue("user", user);
                String log = TextLanguage.getText(EMAIL, (String) requestContent.getSessionAttributeValue("localization"));
                requestContent.setRequestAttributeValue("newEmail", log);
            } else {
                String error = TextLanguage.getText(message, (String) requestContent.getSessionAttributeValue("localization"));
                requestContent.setRequestAttributeValue("newEmail", error);
            }
            page = Configuration.getProperties(ACCOUNT);
        } else {
            page = Configuration.getProperties(SIGNIN);
        }
        return page;
    }
}

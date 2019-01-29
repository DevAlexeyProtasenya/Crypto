package command.changes;

import buisiness_logic.LogicUser;
import command.ActionCommand;
import control.Configuration;
import control.TextLanguage;
import entity.User;
import servlet.SessionRequestContent;

public class NewPassword implements ActionCommand {
    private static final String PASS = "config.account.successNewPass";
    private static final String ACCOUNT = "path.account";
    private static final String SIGNIN = "path.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue("isSignIn");
        if(isSignIn.equals("true")){
            String newPassword= requestContent.getRequestParameterValue("password");
            User user = (User)requestContent.getSessionAttributeValue("user");
            LogicUser logicUser = new LogicUser();
            String message = logicUser.newPassword(user.getLogin(), newPassword);
            if(message.isEmpty()){
                user.setPassword(newPassword);
                requestContent.setSessionAttributeValue("user", user);
                String log = TextLanguage.getText(PASS, (String) requestContent.getSessionAttributeValue("localization"));
                requestContent.setRequestAttributeValue("newPassword", log);
            } else {
                String error = TextLanguage.getText(message, (String) requestContent.getSessionAttributeValue("localization"));
                requestContent.setRequestAttributeValue("newPassword", error);
            }
            page = Configuration.getProperties(ACCOUNT);
        } else {
            page = Configuration.getProperties(SIGNIN);
        }
        return page;
    }
}

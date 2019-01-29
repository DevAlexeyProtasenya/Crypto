package command;

import buisiness_logic.LogicLogin;
import buisiness_logic.LogicUser;
import control.Configuration;
import control.TextLanguage;
import entity.User;
import servlet.SessionRequestContent;

public class Login implements ActionCommand{
    private static final String LOGIN="nickname";
    private static final String PASS="password";
    private static final String WRONG_LOG_PASS="config.error.wronglogpass";
    private static final String LOGIN_ATTRIBUTE_ERROR = "loginAttributeError";
    private static final String LOGIN_PATH = "path.login";
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String MAIN_ACTION = "/web?command=main";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String nickname = requestContent.getRequestParameterValue(LOGIN);
        String pass = requestContent.getRequestParameterValue(PASS);
        LogicLogin logicLogin = new LogicLogin();
        if (logicLogin.verification(nickname,pass)){
            LogicUser logicUser = new LogicUser();
            User user = logicUser.getUser(nickname);
            requestContent.setSessionAttributeValue(SIGN_IN_ATTRIBUTE, "true");
            requestContent.setSessionAttributeValue("user", user);
            page = MAIN_ACTION;
        } else {
            String pageMessage = TextLanguage.getText(WRONG_LOG_PASS,
                    (String) requestContent.getSessionAttributeValue("localization"));
            requestContent.setRequestAttributeValue(LOGIN_ATTRIBUTE_ERROR, pageMessage);
            page = Configuration.getProperties(LOGIN_PATH);
        }
        return page;
    }
}

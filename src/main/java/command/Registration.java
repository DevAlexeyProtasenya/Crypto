package command;

import buisiness_logic.LogicUser;
import control.Configuration;
import control.TextLanguage;
import servlet.SessionRequestContent;

public class Registration implements ActionCommand {
    private static final String LOGIN = "path.login";
    private static final String REGISTRATION = "path.registration";
    private static final String ERROR = "userExist";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String login = requestContent.getRequestParameterValue("nickname");
        String pass = requestContent.getRequestParameterValue("password");
        String email = requestContent.getRequestParameterValue("email");
        String role = "user";
        LogicUser logicUser = new LogicUser();
        String message = logicUser.registration(login,pass,email,role);
        if(message.isEmpty()){
            page = Configuration.getProperties(LOGIN);
        } else {
            String error = TextLanguage.getText(message,
                    (String) requestContent.getSessionAttributeValue("localization"));
            requestContent.setRequestAttributeValue(ERROR,error);
            page = Configuration.getProperties(REGISTRATION);
        }
        return page;
    }
}

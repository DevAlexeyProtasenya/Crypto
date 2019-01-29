package command;

import control.TextLanguage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import servlet.SessionRequestContent;

public class Factory {
    public static final Logger LOGGER = LogManager.getLogger(Factory.class);
    private static final String COMMAND = "command";
    private static final String WRONG_ACTION = "config.error.wrongaction";

    public static ActionCommand defineCommand(SessionRequestContent requestContent) {
        ActionCommand current = new Empty();
        String action = requestContent.getRequestParameterValue(COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            AllCommands currentType = AllCommands.valueOf(action.toUpperCase());
            current = currentType.getCommand();
        } catch (IllegalArgumentException e) {
            LOGGER.error("Command not defined", e);
            String pageMessage = TextLanguage.getText(WRONG_ACTION,
                    (String) requestContent.getSessionAttributeValue("localization"));
            requestContent.setRequestAttributeValue("wrongAction", pageMessage);
        }
        return current;
    }
}

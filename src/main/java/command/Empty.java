package command;

import control.Configuration;
import servlet.SessionRequestContent;

public class Empty implements ActionCommand {
    private static final String ERROR = "path.error";

    @Override
    public String execute(SessionRequestContent requestContent) {
        return Configuration.getProperties(ERROR);
    }
}

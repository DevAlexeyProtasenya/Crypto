package command;

import servlet.SessionRequestContent;

public interface ActionCommand {
    String execute(SessionRequestContent requestContent);
}

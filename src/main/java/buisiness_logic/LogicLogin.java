package buisiness_logic;

import dao.DAOUser;
import exceptions.LogicNullException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogicLogin {
    private static final String TEXT = "Login and password ";

    private static final Logger LOGGER = LogManager.getLogger(LogicLogin.class);


    public boolean verification(String login, String pass) {
        boolean verif = false;
        try {
            if (login == null || login.isEmpty() || pass == null || pass.isEmpty()) {
                throw new LogicNullException(TEXT);
            }
            DAOUser daoUser = new DAOUser();
            String enteredPassword = pass;
            String dbPassword = daoUser.getPassword(login);
            if(enteredPassword.equals(dbPassword)){
                verif = true;
            }
            LOGGER.log(Level.INFO, "Data was verified");
        }
        catch (LogicNullException e){
            e.getMessage();
        }
        return verif;
    }
}

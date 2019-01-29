package buisiness_logic;

import dao.DAOUser;
import entity.User;
import exceptions.LogicNullException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogicUser {

    private static final Logger LOGGER = LogManager.getLogger(LogicUser.class);

    private static final String TEXT = "Login or password ";
    private static final String REG = "Some layer is empty ";

    public User getUser(String login){
        User user = null;
        try{
            if(login == null || login.isEmpty()){
                throw new LogicNullException(TEXT);
            }
            DAOUser daoUser = new DAOUser();
            user = daoUser.get(login);
            LOGGER.log(Level.INFO,"User found");
        } catch (LogicNullException e) {
            e.getMessage();
        }
        return user;
    }

    public String registration(String login, String pass, String email, String role){
        String message = null;
        boolean isAdded = false;
        try{
            if (login == null || login.isEmpty() || pass == null || pass.isEmpty() ||
                    email == null || email.isEmpty()) {
                throw new LogicNullException(REG);
            }
            DAOUser daoUser = new DAOUser();
            isAdded = daoUser.addUser(login, pass, email, role);
            if (isAdded){
                message = "";
            } else {
                message = "User already exist!";
            }
            LOGGER.log(Level.INFO, "User was registered");
        } catch (LogicNullException e) {
            e.getMessage();
        }
        return message;
    }

    public String newLogin(String login, String newLogin){
        String message = null;
        boolean isUpdated = false;
        try{
            if(login == null || login.isEmpty() || newLogin == null || newLogin.isEmpty()){
                throw new LogicNullException("Login or new login ");
            }
            DAOUser daoUser = new DAOUser();
            isUpdated = daoUser.newLogin(login, newLogin);
            if(!isUpdated){
                message = "config.error.wrongLogin";
            } else {
                message = "";
            }
            LOGGER.log(Level.INFO, "Login was changed");
        } catch (LogicNullException e) {
            e.getMessage();
            message = "config.error.wrongLogin";
        }
        return message;
    }

    public String newPassword(String login, String newPassword){
        String message = null;
        boolean isUpdated = false;
        try{
            if(login == null || login.isEmpty() || newPassword == null || newPassword.isEmpty()){
                throw new LogicNullException("Login or new password ");
            }
            DAOUser daoUser = new DAOUser();
            isUpdated = daoUser.newPassword(login, newPassword);
            if(!isUpdated){
                message = "config.error.wrongPassword";
            } else {
                message = "";
            }
            LOGGER.log(Level.INFO, "Password was changed");
        } catch (LogicNullException e) {
            e.getMessage();
            message = "config.error.wrongPassword";
        }
        return message;
    }

    public String newEmail(String login, String newEmail){
        String message = null;
        boolean isUpdated = false;
        try{
            if(login == null || login.isEmpty() || newEmail == null || newEmail.isEmpty()){
                throw new LogicNullException("Login or new email ");
            }
            DAOUser daoUser = new DAOUser();
            isUpdated = daoUser.newEmail(login, newEmail);
            if(!isUpdated){
                message = "config.error.wrongEmail";
            } else {
                message = "";
            }
            LOGGER.log(Level.INFO, "Email was changed");
        } catch (LogicNullException e) {
            e.getMessage();
            message = "config.error.wrongEmail";
        }
        return message;
    }

}

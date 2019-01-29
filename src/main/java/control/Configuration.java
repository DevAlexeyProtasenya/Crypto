package control;

import db.ConnectionPool;

import java.util.ResourceBundle;

public class Configuration {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    private Configuration(){}
    public static String getProperties(String key){return resourceBundle.getString(key);}
}

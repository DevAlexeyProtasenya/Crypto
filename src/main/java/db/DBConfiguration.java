package db;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DBConfiguration {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final int POOL;

    public DBConfiguration() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("configDB");
            URL = resourceBundle.getString("db.url");
            USER = resourceBundle.getString("db.user");
            PASSWORD = resourceBundle.getString("db.password");
            POOL = Integer.parseInt(resourceBundle.getString("db.pool"));
        } catch (NumberFormatException | MissingResourceException e) {
            throw new RuntimeException();
        }
    }

    public String getURL() {
        return URL;
    }

    public String getUser() {
        return USER;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public int getPool() {
        return POOL;
    }
}

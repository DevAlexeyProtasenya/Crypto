package control;

import java.util.Locale;
import java.util.ResourceBundle;

public class TextLanguage {
    private TextLanguage(){}

    public static String getText(String key, String language){
        ResourceBundle resourceBundle = null;
        Locale locale = null;
        if (language != null){
            if (language == "ru_RU"){
                locale = new Locale("ru", "RU");
                resourceBundle = ResourceBundle.getBundle("localization",locale);
            }else{
                locale = new Locale("en", "EN");
                resourceBundle = ResourceBundle.getBundle("localization",locale);
            }
        } else {
            resourceBundle = ResourceBundle.getBundle("localization");
        }
        return resourceBundle.getString(key);
    }
}

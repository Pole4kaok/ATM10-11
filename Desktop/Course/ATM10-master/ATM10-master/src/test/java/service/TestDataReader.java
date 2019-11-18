package service;
import java.util.ResourceBundle;
public class TestDataReader {

    public String getTestData(String key){
        return ResourceBundle.getBundle(System.getProperty("environment")).getString(key);
    }
}

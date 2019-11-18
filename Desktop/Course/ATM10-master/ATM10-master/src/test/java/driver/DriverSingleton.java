package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSingleton {
    public static WebDriver driver;

        public DriverSingleton(){ }

        public static WebDriver getDriver(){
            if(driver == null){
                System.setProperty("browser","chrome");
                WebDriverManager.chromedriver().setup();
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                driver = new ChromeDriver();
            }

            return driver;
        }

}

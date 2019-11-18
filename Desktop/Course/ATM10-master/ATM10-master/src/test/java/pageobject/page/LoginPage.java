package pageobject.page;

import model.UserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import reporting.MyLogger;
import service.TestDataReader;


public class LoginPage extends AbstractPage {
    public LoginPage () { }
    private static final By USERNAME_LOCATOR = By.name("username");
    private static final By PASSWORD_LOCATOR = By.name("password");
    private static final By LOGINBTN_LOCATOR = By.xpath("//input[@value='Login']");
    private final String PAGE_URL = "testdata.url";

        public MainPage login(UserModel user){
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(USERNAME_LOCATOR));
            driver.findElement(USERNAME_LOCATOR).sendKeys(user.getUsername());
            driver.findElement(PASSWORD_LOCATOR).sendKeys(user.getPassword());
            driver.findElement(LOGINBTN_LOCATOR).click();
            MyLogger.info("Successfully logged in");
            return new MainPage();
        }

        public LoginPage open(){
            driver.get(new TestDataReader().getTestData(PAGE_URL));
            return this;
        }
}

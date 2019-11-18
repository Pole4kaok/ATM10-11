package pageobject.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static pageobject.page.MainPage.SAVEBTN_LOCATOR;

import driver.DriverDecorator;
import driver.DriverSingleton;
import reporting.MyLogger;

public class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(){this.driver = DriverSingleton.getDriver();}

    public void waitElementClickable(By locator){
        try{
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(locator));}
        catch(TimeoutException e){
            MyLogger.error("Waited too long to click:" + locator + ", sorry");

        }
    }

    public void waitElementInvisible(By locator){
        new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitRulesEngineComplete(By locator, String text){
        new WebDriverWait(driver,20).until(ExpectedConditions.textToBePresentInElementLocated(locator,text));
    }
    public void waitElementVisible(By locator){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitAjaxFinished(){
        new WebDriverWait(driver, 2000).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }
    public WebElement findElement(By by){
        WebElement webElement = null;// = driver.findElement(by);
        try
        {
            webElement = driver.findElement(by);
        }
        catch (NoSuchElementException e)
        {
            MyLogger.error("BLA BLA");
            throw new Exception("SSSS");
        }
        finally
        {
            return webElement;
        }
       /* if(driver instanceof JavascriptExecutor){
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", webElement);
        }*/
    }

    public void saveChanges(){
        driver.findElement(SAVEBTN_LOCATOR).click();
    }


    public void customWait(final WebElement element1){
        new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {

                WebElement element = ExpectedConditions.visibilityOf(element1).apply(driver);
                try {
                    if (element != null)
                    {
                        element.isEnabled();
                    }
                    element.click();
                    return element;
                } catch (ElementClickInterceptedException e) {
                    return null;
                }
            }
            public String toString() {
                return "element to be clickable: ";
            }
        });
    }

}

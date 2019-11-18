package pageobject.page.casePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.page.AbstractPage;
import reporting.MyLogger;

import java.util.List;
import java.util.NoSuchElementException;

import static pageobject.page.MainPage.*;

public class CaseEventPage extends AbstractPage {
    private static final By CASEEVENTFIELD_LOCATOR = By.id("select2-chosen-1");
    private static final By CASEEVENTTYPEAHEAD_LOCATOR = By.id("s2id_autogen1_search");
    private static final By CASEEVENTCLASS_LOCATOR = By.id("docketEntryName");
   // protected static final By CASEEVENT_LOCATOR = By.id("menuItem-10046-1-main");
   protected static final By CASEEVENT_LOCATOR = By.id("menuItem-1000000-1-main");
    protected static final By CASEEVENTFOUND_LOCATOR = By.id("select2-result-label-771");
    public CaseEventPage(){super();}

    public CaseEventPage newCaseEvent(String caseEvent){
        try{
        findElement(CASEEVENT_LOCATOR).click();}
        catch(NoSuchElementException e){
            MyLogger.error("Can't find element:" + CASEEVENT_LOCATOR);
        }
        waitElementClickable(CASEEVENTFIELD_LOCATOR);
        findElement(CASEEVENTFIELD_LOCATOR).click();
        waitElementClickable(CASEEVENTTYPEAHEAD_LOCATOR);
        findElement(CASEEVENTTYPEAHEAD_LOCATOR).sendKeys(caseEvent);
        waitElementInvisible(INVISIBLE_LOCATOR);

        WebElement classificationDropdown = driver.findElement(CLASSDROPDOWN_LOCATOR);
        List<WebElement> options = classificationDropdown.findElements(By.tagName("li"));
        for (WebElement option: options){
            if(option.getText().contains(caseEvent)){
                new WebDriverWait(driver,20)
                        .until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                break;
            }
        }
        WebElement addBtn = findElement(SAVEBTN_LOCATOR);
        customWait(addBtn);
        MyLogger.info("Successfully added case event");
        return this;
    }

    public AddCaseEventPage addCaseEvent(){
        saveChanges();
        return new AddCaseEventPage();
    }
}

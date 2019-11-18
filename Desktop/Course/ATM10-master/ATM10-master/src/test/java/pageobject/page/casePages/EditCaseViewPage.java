package pageobject.page.casePages;

import java.util.List;

import model.CaseModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageobject.page.AbstractPage;

public class EditCaseViewPage extends AbstractPage {
    protected static final By CASEVIEW_LOCATOR = By.id("menuItem-10044-1-main");
    protected static final By CASECAPTION_FIELD_LOCATOR = By.id("caseCaption");
    protected static final By CASETITLE_FIELD_LOCATOR = By.id("caseTitle");
    protected static final By EDITLINK_LOCATOR = By.xpath("//*[@id=\"caseInformation\"]/table/tbody/tr[10]/td/a");
    public EditCaseViewPage(){super();}

    public EditCaseViewPage addCaseCaptionAndTitle(CaseModel newCase){
        waitElementVisible(CASEVIEW_LOCATOR);
        driver.findElement(CASEVIEW_LOCATOR).click();
        waitElementVisible(EDITLINK_LOCATOR);
        driver.findElement(EDITLINK_LOCATOR).click();

        addCaseCaption(newCase.getcaseCaption());
        addCaseTitle(newCase.getcaseTitle());
        saveChanges();
        return this;
    }

    public EditCaseViewPage addCaseTitle(String caseTitle){
        WebElement caseTitleField = driver.findElement(CASETITLE_FIELD_LOCATOR);
        caseTitleField.sendKeys(caseTitle);
        return this;
    }

    public EditCaseViewPage addCaseCaption(String caseCaption){
        waitElementClickable(CASECAPTION_FIELD_LOCATOR);
        WebElement caseCaptionField = driver.findElement(CASECAPTION_FIELD_LOCATOR);
        caseCaptionField.sendKeys(caseCaption);
        return this;
    }

    public EditCaseViewPage getCheckList(){
        List checklist = driver.findElements(By.cssSelector(".cc-checklistTable"));
        Assert.assertFalse(checklist.size()==0,"It is visible. Or not. Maybe wrong selector");
        return this;
    }
}

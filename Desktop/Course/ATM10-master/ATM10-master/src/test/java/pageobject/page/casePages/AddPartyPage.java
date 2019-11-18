package pageobject.page.casePages;

import model.PersonModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobject.page.AbstractPage;
import reporting.MyLogger;

public class AddPartyPage extends AbstractPage {
    protected static final By PARTY_MENU_LOCATOR = By.id("menuItem-10045-1-main");
    protected static final By ADDNEWBNT_LOCATOR = By.xpath("//*[@id=\"searchSection\"]/table[2]/tbody/tr/td[2]/input[2]");
    protected static final By PARTYTYPE_SELECT_LOCATOR = By.id("partyTypeSelect");
    protected static final By PARTYROLE_SELECT_LOCATOR = By.id("partySubTypeSelect");
    protected static final By LASTNAME_LOCATOR = By.name("person.lastName");
    protected static final By FIRSTNAME_LOCATOR = By.name("person.firstName");
    protected static final By LINE1_LOCATOR = By.name("actorInstance.addresses[0].line1");
    protected static final By COUNTRY_LOCATOR = By.name("actorInstance.addresses[0].country.countryID");
    protected static final By ADDLEGAL_LINK_LOCATOR = By.xpath("//*[@id=\"casePartyID\"]/div[2]/a");
    public AddPartyPage(){super();}

    public AddPartyPage addPartyRoleType(String type, String role){
        driver.findElement(PARTY_MENU_LOCATOR).click();
        driver.findElement(ADDNEWBNT_LOCATOR).click();
        setPartyType(type);
        setPartyRole(role);
        saveChanges();
        MyLogger.info("Successfully added party");
        return this;
    }

    public AddPartyPage setPartyType (String type){
        waitElementClickable(PARTYTYPE_SELECT_LOCATOR);
        WebElement partyTypeDropdown = driver.findElement(PARTYTYPE_SELECT_LOCATOR);
        customWait(partyTypeDropdown);
        partyTypeDropdown.sendKeys(type);
        return this;
    }
    public AddPartyPage setPartyRole(String role){
        WebElement partyRoleDropdown = driver.findElement(PARTYROLE_SELECT_LOCATOR);
        partyRoleDropdown.click();
        partyRoleDropdown.sendKeys(role);
        return this;
    }

    public AddPartyPage addPerson(PersonModel person){
        waitElementClickable(LASTNAME_LOCATOR);
        driver.findElement(LASTNAME_LOCATOR).sendKeys(person.getLastName());
        driver.findElement(FIRSTNAME_LOCATOR).sendKeys(person.getFirstName());
        driver.findElement(LINE1_LOCATOR).sendKeys(person.getLine1());
        driver.findElement(COUNTRY_LOCATOR).sendKeys(person.getCountry());
        saveChanges();
        return this;
    }

    public AddPartyPage addLegalRep(){
        waitElementClickable(ADDLEGAL_LINK_LOCATOR);
        saveChanges();
        return this;
    }

}

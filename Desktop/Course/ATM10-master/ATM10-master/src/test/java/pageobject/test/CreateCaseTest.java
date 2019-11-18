package pageobject.test;

import model.CaseModel;
import model.PersonModel;
import model.UserModel;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.page.*;
import pageobject.page.casePages.*;
import reporting.MyLogger;
import service.CaseCreator;
import service.PersonCreator;
import service.UserCreator;

import java.util.List;

public class CreateCaseTest extends CommonConditions {
    @Test(description = "Creating case")
    public void createCase(){
        //TODO: remove hardcode
        System.setProperty("environment","qa");
        UserModel testUser = UserCreator.withCredentialsFromProperty();
        MainPage mainPage = new LoginPage().open().login(testUser)
                .openCreateCase().fillInCaseClassification("@@@").chooseCaseClassification("Business - N8 - Arbitration");
    }

    @Test (description = "create and search for a case")
    public void createAndSearchCase() {
        createCase();
        String createdCaseNumber = new CaseViewPage().getCaseNumber();
        CaseSearchPage caseSearch = new CaseSearchPage().caseSearch(createdCaseNumber);
        String foundCaseNumber = new CaseViewPage().getFoundCaseNumber();
        Assert.assertEquals(createdCaseNumber,foundCaseNumber,"Failed");
    }

    @Test(description = "complete checklist")
    public void completeChecklist(){
        createCase();
        PersonModel testPerson = PersonCreator.withDataFromProperties();
        CaseModel newCase = CaseCreator.withDataFromProperties();
        AddPartyPage addPartyPage = new AddPartyPage().addPartyRoleType("Party","Defendant")
                .addPerson(testPerson).addLegalRep();
        MyLogger.info("Added party");
        AddCaseEventPage addCaseEventPage = new CaseEventPage().newCaseEvent("Filing - Bundle").addCaseEvent();
        MyLogger.info("Added case event");
        EditCaseViewPage editCaseViewPage = new EditCaseViewPage().addCaseCaptionAndTitle(newCase);
        MyLogger.info("Added case caption and title");
        List checklist = driver.findElements(By.cssSelector(".cc-checklistTable"));
        Assert.assertTrue(false,"It is visible. Or not. Maybe wrong selector");
    }
}

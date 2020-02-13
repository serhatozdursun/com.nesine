

import appSetting.AppRuning;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framwork.CommonFunctions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class Stepdefs {

    CommonFunctions commonFunctions;
    List<String> eventsName = new ArrayList<>();

    @After
    public void closeApp() {
        commonFunctions.closeApp();
    }

    @Given("^Nesine app runing on \"([^\"]*)\"$")
    public void nesineAppRuningOn(String device) throws Throwable {
        AppRuning appRuning = new AppRuning();
        commonFunctions = new CommonFunctions(appRuning.RunApplication(device));
    }

    @When("^I click \"([^\"]*)\" button\\.$")
    public void iClickButton(String mobilElement)   {
        commonFunctions.click(mobilElement);
    }

    @And("^I fill to field  user name and password, i click \"([^\"]*)\" button\\.$")
    public void iFillToFieldUserNameAndPasswordIClickButton(String selector) {
        commonFunctions.typeById("username_edit", "sozdursun");
        commonFunctions.typeById("password_edit", "NesiNe49");
        commonFunctions.click(selector);
    }

    @And("^I Click \"([^\"]*)\" button on the homepage\\.$")
    public void clickButtonOnTheHomepage(String selector)  {
        commonFunctions.click(selector);
    }

    @And("^I click on the \"([^\"]*)\" tab on the page that opens, the first \"([^\"]*)\" button displayed is selected\\.$")
    public void iClickOnTheTabOnThePageThatOpensTheFirstButtonDisplayedIsSelected(String selector1, String aselector2)  {
        commonFunctions.click(selector1);
        commonFunctions.getMobileElements(aselector2).get(0).click();
    }

    @And("^I got the event names on the \"([^\"]*)\" page, I click the \"([^\"]*)\" button\\.$")
    public void iGotTheEventNamesOnThePageIClickTheButton(String selector1, String select2)  {
        Assert.assertTrue(commonFunctions.isDisplayedXpath(selector1));
        List<MobileElement> events = commonFunctions.getMobileElementsById("tv_teams");
        for (MobileElement elm : events) {
            eventsName.add(elm.getText());
        }
        while (!commonFunctions.elementIsPresentByXpath(select2)) {

            List<MobileElement> eventsMore = commonFunctions.getMobileElementsById("tv_teams");
            MobileElement endElement = commonFunctions.getMobileElementsById("tv_teams").get(0);

            commonFunctions.swipeByElements((AndroidElement) eventsMore.get(eventsMore.size() - 2), (AndroidElement) endElement);

            for (WebElement elm : commonFunctions.getMobileElementsById("tv_teams")) {
                if (!eventsName.contains(elm.getText())) {
                    eventsName.add(elm.getText());
                }
            }
        }

        commonFunctions.click(select2);
    }


    @Then("^I check that the event names are correct, on the basket page$")
    public void iCheckThatTheEventNamesAreCorrectOnTheBasketPage() {
        List<MobileElement> teamNames = commonFunctions.getMobileElementsById("teamName");
        List teamNamesText = new ArrayList<String>();
        for (MobileElement elm : teamNames) {
            teamNamesText.add(elm.getText());
        }

        if(teamNames.size()>=2) {
            while (eventsName.size() != teamNamesText.size()) {

                List<MobileElement> teamNamesMore = commonFunctions.getMobileElementsById("teamName");
                MobileElement endElement = commonFunctions.getMobileElementsById("teamName").get(0);
                commonFunctions.swipeByElements((AndroidElement) teamNamesMore.get(teamNamesMore.size() - 2), (AndroidElement) endElement);
                int teamNamesTextSize = teamNamesText.size();
                for (WebElement elm : commonFunctions.getMobileElementsById("teamName")) {
                    if (!teamNamesText.contains(elm.getText())) {
                        teamNamesText.add(elm.getText());
                    }
                }
                if (teamNamesText.size() == teamNamesTextSize) {
                    break;
                }
            }

            for (int i = 0; i < eventsName.size(); i++) {
                Assert.assertEquals(eventsName.get(i).trim(), teamNamesText.get(i).toString().trim());
            }
        }
    }

}

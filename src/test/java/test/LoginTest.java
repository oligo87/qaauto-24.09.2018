package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;

public class LoginTest extends BaseTest{

     /**
     * PreConditions:
     * - Open FF browser.
     *
     * Scenario:
     * - Navigate to https://linkedin.com.
     * - Verify that Login page is loaded.
     * - Enter userEmail into userEmail field.
     * - Enter userPassword into userPassword field.
     * - Click on signIn button.
     * - Verify that Home page is loaded.
     *
     * PostCondition:
     * - Close FF browser.
     */

     @DataProvider
     public Object[][] validDataProvider() {
         return new Object[][]{
                 {"oleg.ilin.amc@gmail.com", "myPasswordQA2018"},
                 {"oleg.ilin.amc@gmail.com", "myPasswordQA2018"},
                 {" oleg.ilin.amc@gmail.com ", "myPasswordQA2018"}
         };
     }
    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login(userEmail, userPassword);

        Assert.assertTrue(homePage.isPageLoaded(),"page.HomePage is not displayed");
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                {"oleg.ilin.amc@gmail.com", ""},
                {"", "myPasswordQA18"},
                {"", ""}
        };
    }
    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginWithEmptyFieldsTest(String userEmail, String userPassword){

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        loginPage.login(userEmail, userPassword);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @DataProvider
    public Object[][] loginSubmitDataProvider() {
        return new Object[][]{
                {"oleg.ilin.amc@gmail.com", "wrong", "", "The password you provided must have at least 6 characters."},
                {"oleg.ilin.amc@gmail.com", "longwrong", "", "Hmm, that's not the right password. Please try again or request a new one."},
                {"a@b.c", "myPasswordQA18", "Please enter a valid email address.", ""},
                {"a@b.c", "wrong", "Please enter a valid email address.", "The password you provided must have at least 6 characters."},
        };
    }
    @Test(dataProvider = "loginSubmitDataProvider")
    public void validationMessagesOnInvalidEmailPasswordTest(String userEmail,
                                                             String userPassword,
                                                             String emailValidationMessage,
                                                             String passwordValidationMessage){

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login-Submit page is not loaded.");

        Assert.assertEquals(loginSubmitPage.getAlertMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message text is wrong.");

        Assert.assertEquals(loginSubmitPage.getEmailValidationMessage(), emailValidationMessage, "Email validation error message is wrong!");

        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessage(), passwordValidationMessage, "Password validation error message is wrong!");

    }
}

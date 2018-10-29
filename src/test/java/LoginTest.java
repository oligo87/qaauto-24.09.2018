import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://linkedin.com");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

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
                 {"pushkin.oligo+1@gmail.com", "myPasswordQA18"},
                 {"pushkin.OLIGO+1@gmail.com", "myPasswordQA18"},
                 {" pushkin.oligo+1@gmail.com ", "myPasswordQA18"}
         };
     }
    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login(userEmail, userPassword);

        Assert.assertTrue(homePage.isPageLoaded(),"HomePage is not displayed");
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                {"pushkin.oligo+1@gmail.com", ""},
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
                {"pushkin.oligo+1@gmail.com", "wrong", "", "The password you provided must have at least 6 characters."},
                {"pushkin.oligo+1@gmail.com", "longwrong", "", "Hmm, that's not the right password. Please try again or request a new one."},
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

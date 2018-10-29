import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"pushkin.oligo+1@gmail.com", "myPasswordQA18"},
                {"pushkin.OLIGO+1@gmail.com", "myPasswordQA18"},
                {" pushkin.oligo+1@gmail.com ", "myPasswordQA18"}
        };
    }
    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                {"pushkin.oligo+1@gmail.com", ""},
                {"", "myPasswordQA18"},
                {"", ""}
        };
    }
    @DataProvider
    public Object[][] loginSubmitDataProvider() {
        return new Object[][]{
                {"pushkin.oligo+1@gmail.com", "wrong", "", "The password you provided must have at least 6 characters."},
                {"a@b.c", "myPasswordQA18", "Please enter a valid email address.", ""},
        };
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
    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login(userEmail, userPassword);

        Assert.assertTrue(homePage.isPageLoaded(),"HomePage is not displayed");
    }

    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginWithEmptyPasswordTest(String userEmail, String userPassword){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        loginPage.login(userEmail, userPassword);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");
    }

    @Test(dataProvider = "loginSubmitDataProvider")
    public void negativeLoginWithWrongPasswordTest(String userEmail, String userPassword, String emailValidationMessage, String passwordValidationMessage){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login-Submit page URL is wrong.");

        WebElement emailValidationMessageField = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(emailValidationMessageField.getText(), emailValidationMessage, "Email validation error message is wrong!");

        WebElement passwordValidationMessageField = webDriver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        Assert.assertEquals(passwordValidationMessageField.getText(), passwordValidationMessage, "Password validation error message is wrong!");

    }
}

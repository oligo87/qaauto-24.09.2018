import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
    @Test
    public void successfulLoginTest() {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login("pushkin.oligo+1@gmail.com", "myPasswordQA18");

        Assert.assertTrue(homePage.isPageLoaded(),"HomePage is not displayed");
    }

    @Test
    public void negativeLoginWithEmptyPasswordTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        loginPage.emptyPasswordLogin("a@b.c", "");

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is loaded.");
    }

    @Test
    public void negativeLoginWithWrongPasswordTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        LoginSubmitPage loginSubmitPage = loginPage.wrongPasswordLogin("pushkin.oligo+1@gmail.com", "wrong");

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login-Submit page URL is wrong.");

    }
}

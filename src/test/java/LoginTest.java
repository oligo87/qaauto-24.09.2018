import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
     * Preconditions:
     * - Open FF browser
     *
     * Scenario:
     * - Navigate to https://www.linkedin.com/
     * - Verify that login page is loaded
     * - Enter userEmail into userEmail field
     * - Enter userPassword into userPassword field
     * - Click on singin button
     * - Verify that Home page is loaded
     *
     * Postconditions:
     * - Close FF browser
     */
    @Test
    public void successfullLoginTest() {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertTrue(loginPage.isPageLoaded(),"Login page is not loaded.");

        loginPage.login("pushkin.oligo+1@gmail.com","myPasswordQA18");

        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/feed/","Home page URL is wrong.");
        Assert.assertEquals(webDriver.getTitle(),"LinkedIn","Home page title is wrong.");

        HomePage homePage = new HomePage(webDriver);
        Assert.assertTrue(homePage.isPageLoaded(),"profileNavItem is not displayed");
    }
    @Test
    public void negativeEmptyPasswordTest() {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/","Login page URL is wrong.");

        loginPage.login("a@b.c","");

        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/","Login page URL is wrong.");
    }
    @Test
    public void negativeWrongPasswordTest() {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/","Login page URL is wrong.");

        loginPage.login("pushkin.oligo+1@gmail.com","Qwerty123");

        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME","LoginSubmit page URL is wrong.");
    }
}

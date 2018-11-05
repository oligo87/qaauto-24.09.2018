import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResetPasswordTest {
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
     * - Open new browser.
     * - Navigate to https://linkedin.com.
     *
     * Scenario:
     * - Verify that Login page is loaded.
     * - Click Forgot password? link.
     * - Verify that request-password-reset page is loaded.
     * - Enter registered email into email field and click Search account btn.
     * - Verify that request-password-reset-submit page is loaded.
     * - MANUAL ACTIONS     *
     * - Verify that password-reset page is loaded.     *
     * - Enter 'newPassword' into new password and confirmation fields and click Send btn.     *
     * - Verify that password-reset-submit page is loaded.
     * - Press GoToHomePage btn.
     * - Verify that Home page is loaded.
     *
     * PostCondition:
     * - Close browser.
     */
    @Test
    public void resetPasswordTest(){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        RequestPasswordResetPage requestPasswordResetPage = loginPage.forgotPassword();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(),"RequestPasswordResetPage is not displayed.");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.searchRegisteredEmail("pushkin.oligo+1@gmail.com");
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(),"RequestPasswordResetSubmitPage is not loaded.");

        PasswordResetPage passwordResetPage = requestPasswordResetSubmitPage.manualLinkInsert();
        Assert.assertTrue(passwordResetPage.isPageLoaded(), "PasswordResetPage is not loaded.");

        PasswordResetSubmitPage passwordResetSubmitPage = passwordResetPage.resetPassword("myPasswordQA2018");
        Assert.assertTrue(passwordResetSubmitPage.isPageLoaded(), "PasswordResetSubmitPage is not loaded.");

        HomePage homePage = passwordResetSubmitPage.navigateToHomePage();
        Assert.assertTrue(homePage.isPageLoaded(), "HomePage is not loaded.");
    }
}

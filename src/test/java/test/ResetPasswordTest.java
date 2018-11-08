package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class ResetPasswordTest extends BaseTest{

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
     * - MANUAL ACTIONS
     * - Verify that password-reset page is loaded.
     * - Enter 'newPassword' into new password and confirmation fields and click Send btn.
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
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(),"page.RequestPasswordResetPage is not displayed.");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.searchRegisteredEmail("oleg.ilin.amc@gmail.com");
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(),"page.RequestPasswordResetSubmitPage is not loaded.");

        PasswordResetPage passwordResetPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(passwordResetPage.isPageLoaded(), "page.PasswordResetPage is not loaded.");

        PasswordResetSubmitPage passwordResetSubmitPage = passwordResetPage.resetPassword("myPasswordQA2018");
        Assert.assertTrue(passwordResetSubmitPage.isPageLoaded(), "page.PasswordResetSubmitPage is not loaded.");

        HomePage homePage = passwordResetSubmitPage.navigateToHomePage();
        Assert.assertTrue(homePage.isPageLoaded(), "page.HomePage is not loaded.");
    }
}

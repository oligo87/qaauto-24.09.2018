package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class PasswordResetPage extends BasePage{

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordInput;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    public PasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return webDriver.getCurrentUrl().contains("/rp/password-reset")
                && webDriver.getTitle().equals("Reset Your Password | LinkedIn")
                && isNewPasswordInputDisplayed()
                && isConfirmPasswordInputDisplayed();
    }

    private boolean isNewPasswordInputDisplayed() {
        return newPasswordInput.isDisplayed();
    }

    private boolean isConfirmPasswordInputDisplayed() {
        return confirmPasswordInput.isDisplayed();
    }

    public PasswordResetSubmitPage resetPassword(String newPassword) {
        newPasswordInput.sendKeys(newPassword);
        confirmPasswordInput.sendKeys(newPassword);
        resetPasswordSubmitButton.click();
        return new PasswordResetSubmitPage(webDriver);
    }
}

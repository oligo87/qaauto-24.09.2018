package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        waitUntilElementIsClickable(newPasswordInput);
        waitUntilElementIsClickable(confirmPasswordInput);
        return webDriver.getCurrentUrl().contains("/rp/password-reset")
                && webDriver.getTitle().equals("Reset Your Password | LinkedIn");
    }

    public PasswordResetSubmitPage resetPassword(String newPassword) {
        newPasswordInput.sendKeys(newPassword);
        confirmPasswordInput.sendKeys(newPassword);
        resetPasswordSubmitButton.click();
        return new PasswordResetSubmitPage(webDriver);
    }
}

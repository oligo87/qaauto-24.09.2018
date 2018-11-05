package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import static java.lang.Thread.sleep;

public class RequestPasswordResetPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//input[contains(@validation-message,'Please enter your email or phone')]")
    private WebElement userEmailInput;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    public RequestPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return webDriver.getCurrentUrl().contains("request-password-reset")
                && webDriver.getTitle().equals("Reset Password | LinkedIn")
                && isUserEmailInputDisplayed();
    }

    private boolean isUserEmailInputDisplayed() {
        return userEmailInput.isDisplayed();
    }

    public RequestPasswordResetSubmitPage searchRegisteredEmail(String registeredEmail) {
        GMailService gMailService = new GMailService();
        gMailService.connect();

        userEmailInput.sendKeys(registeredEmail);
        resetPasswordSubmitButton.click();

        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "pushkin.oligo+1@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);

        return new RequestPasswordResetSubmitPage(webDriver);
    }
}
package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class RequestPasswordResetPage extends BasePage{

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
        /*GMailService gMailService = new GMailService();*/
        gMailService.connect();

        userEmailInput.sendKeys(registeredEmail);
        resetPasswordSubmitButton.click();

        /*String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "oleg.ilin.amc@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        String rawResetPasswordUrl = message.substring(message.indexOf("https://www.linkedin.com/e/"), message.indexOf("_sig=") + 19);
        System.out.println("raw URL: " + rawResetPasswordUrl);

        String resetPasswordUrl = rawResetPasswordUrl.replace("amp;", "");
        System.out.println("URL: " + resetPasswordUrl);

        webDriver.get(resetPasswordUrl);*/

        return new RequestPasswordResetSubmitPage(webDriver);
    }
}
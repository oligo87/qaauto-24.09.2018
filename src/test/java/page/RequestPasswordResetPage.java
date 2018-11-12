package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class RequestPasswordResetPage extends BasePage{

    @FindBy(xpath = "//input[contains(@validation-message,'Please enter your email or phone')]")
    private WebElement userEmailInput;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordSubmitButton;

    /**
     * Constructor for RequestPasswordResetPage
     * @param webDriver driver instance from tests
     */
    public RequestPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method to verify that page is loaded with few conditions
     */
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(userEmailInput);
        return webDriver.getCurrentUrl().contains("request-password-reset")
                && webDriver.getTitle().equals("Reset Password | LinkedIn");
    }

    /**
     * Method inputs registeredEmail into userEmail field and clicks submit.
     * @param registeredEmail string registeredEmail
     * @return PasswordResetPage
     */
    public PasswordResetPage searchRegisteredEmail(String registeredEmail) {
        gMailService = new GMailService();
        gMailService.connect();

        userEmailInput.sendKeys(registeredEmail);
        resetPasswordSubmitButton.click();

        String messageSubject = "данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "oleg.ilin.amc@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);

        String resetPasswordUrl = message.substring(message.indexOf("https://www.linkedin.com/e/"), message.indexOf("_sig=") + 19).replace("amp;", "");
        System.out.println("URL: " + resetPasswordUrl);

        webDriver.get(resetPasswordUrl);

        return new PasswordResetPage(webDriver);
    }
}
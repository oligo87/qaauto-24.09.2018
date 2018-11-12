package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPasswordResetSubmitPage extends BasePage{

    @FindBy (xpath = "//button[@id='resend-url']")
    private WebElement resendButton;

    public RequestPasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        waitUntilElementIsClickable(resendButton);
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/request-password-reset-submit")
                && webDriver.getTitle().equals("Please check your mail for reset password link. | LinkedIn");
    }

    public PasswordResetPage navigateToLinkFromEmail() {

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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class RequestPasswordResetSubmitPage {
    private WebDriver webDriver;

    @FindBy (xpath = "//button[@id='resend-url']")
    private WebElement resendButton;

    public RequestPasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/request-password-reset-submit")
                && webDriver.getTitle().equals("Please check your mail for reset password link. | LinkedIn")
                && isResendButtonDisplayed();
    }

    private boolean isResendButtonDisplayed() {
        return resendButton.isDisplayed();
    }

    public PasswordResetPage manualLinkInsert() {
        try {
            sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new PasswordResetPage(webDriver);
    }
}

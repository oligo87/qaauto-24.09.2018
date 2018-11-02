import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPasswordResetSubmitPage {
    private WebDriver webDriver;

    @FindBy (xpath = "//button[@id='resend-url']")
    private WebElement resendButton;

    public RequestPasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("request-password-reset-submit")
                && webDriver.getTitle().equals("Please check your mail for reset password link.  | LinkedIn")
                && isResendButtonDisplayed();
    }

    private boolean isResendButtonDisplayed() {
        return resendButton.isDisplayed();
    }
}

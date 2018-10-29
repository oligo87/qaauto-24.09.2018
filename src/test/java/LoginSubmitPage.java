import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertBox;

    @FindBy (xpath = "//span[@id='session_key-login-error']")
    private WebElement loginError;

    @FindBy (xpath = "//span[@id='session_password-login-error']")
    private WebElement passwordError;

    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().contains("LinkedIn")
                && alertBox.isDisplayed();
    }

    public String getAlertMessageText() {
        return alertBox.getText();
    }

    public String getEmailValidationMessage() {
        return loginError.getText();
    }

    public String getPasswordValidationMessage() {
        return passwordError.getText();
    }
}

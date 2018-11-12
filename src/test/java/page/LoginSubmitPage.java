package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage extends BasePage{

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertBox;

    @FindBy (xpath = "//span[@id='session_key-login-error']")
    private WebElement loginError;

    @FindBy (xpath = "//span[@id='session_password-login-error']")
    private WebElement passwordError;

    /**
     * Constructor for LoginSubmitPage
     * @param webDriver driver instance from tests
     */
    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method to verify that page is loaded with few conditions
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().contains("LinkedIn")
                && alertBox.isDisplayed();
    }

    /**
     * @return text from alertBox element
     */
    public String getAlertMessageText() {
        return alertBox.getText();
    }

    /**
     * @return text from loginError element
     */
    public String getEmailValidationMessage() {
        return loginError.getText();
    }

    /**
     * @return text from passwordError element
     */
    public String getPasswordValidationMessage() {
        return passwordError.getText();
    }
}

package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Constructor for LoginPage object
     * @param webDriver driver instance from tests
     */
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

    }

    /**
     * Method to verify that page is loaded with few conditions
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && isSignInButtonDisplayed();
    }

    /**
     * Method checks visibility of signInButton
     */
    public boolean isSignInButtonDisplayed() {
        return signInButton.isDisplayed();
    }

    /**
     * Method for logging in by credentials
     * @param userEmail - string with userEmail
     * @param userPassword - string with userPassword
     * @param <T> - generic type of returned page object
     * @return variable pages depending on credentials combination
     */
    public <T> T login(String userEmail, String userPassword){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();

        if (isUrlContains("/feed", 5)) {
            return (T) new HomePage(webDriver);
        }
        if (isUrlContains("/uas/login-submit", 5)) {
            return (T) new LoginSubmitPage(webDriver);
        }
        else {
            return (T) new LoginPage(webDriver);
        }
    }

    /**
     * Method clicks on Forgot password link
     * @return RequestPasswordResetPage
     */
    public RequestPasswordResetPage forgotPassword() {
        waitUntilElementIsClickable(forgotPasswordLink);
        forgotPasswordLink.click();
        return new RequestPasswordResetPage(webDriver);
    }
}

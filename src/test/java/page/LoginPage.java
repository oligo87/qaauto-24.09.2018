package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && isSignInButtonDisplayed();
    }

    public boolean isSignInButtonDisplayed() {
        return signInButton.isDisplayed();
    }

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

    public RequestPasswordResetPage forgotPassword() {
        waitUntilElementIsClickable(forgotPasswordLink);
        forgotPasswordLink.click();
        return new RequestPasswordResetPage(webDriver);
    }
}

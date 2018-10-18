import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver webDriver;

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signinButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && isSignInButtonDisplayed();
    }

    public boolean isSignInButtonDisplayed() {
        return signinButton.isDisplayed();
    }

    private void initElements(){
        emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        signinButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));
    }

    public void login(String userEmail, String userPassword){
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPassword);
        signinButton.click();
    }
}

package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetSubmitPage extends BasePage{

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement navigateToHomePage;

    public PasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        waitUntilElementIsClickable(navigateToHomePage);
        return webDriver.getCurrentUrl().contains("/rp/password-reset-submit")
                && webDriver.getTitle().equals("You've successfully reset your password. | LinkedIn");
    }

    public HomePage navigateToHomePage() {
        navigateToHomePage.click();
        return new HomePage(webDriver);
    }
}

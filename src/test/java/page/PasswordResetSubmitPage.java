package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetSubmitPage extends BasePage{

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement navigateToHomePage;

    /**
     * Constructor for HomePage
     * @param webDriver driver instance from tests
     */
    public PasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method to verify that page is loaded with few conditions
     */
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(navigateToHomePage);
        return webDriver.getCurrentUrl().contains("/rp/password-reset-submit")
                && webDriver.getTitle().equals("You've successfully reset your password. | LinkedIn");
    }

    /**
     * Clicks on button navigateToHomePage
     */
    public HomePage navigateToHomePage() {
        navigateToHomePage.click();
        return new HomePage(webDriver);
    }
}

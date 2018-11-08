package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class PasswordResetSubmitPage extends BasePage{

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement navigateToHomePage;

    public PasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return webDriver.getCurrentUrl().contains("/rp/password-reset-submit")
                && webDriver.getTitle().equals("You've successfully reset your password. | LinkedIn")
                && isNavigateToHomePageDisplayed();
    }

    private boolean isNavigateToHomePageDisplayed() {
        return navigateToHomePage.isDisplayed();
    }

    public HomePage navigateToHomePage() {
        navigateToHomePage.click();
        return new HomePage(webDriver);
    }
}

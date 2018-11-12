package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//input[contains(@aria-owns,'results')]")
    private WebElement searchField;

    /**
     * Constructor for HomePage object
     * @param webDriver driver instance from tests
     */
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method to verify that page is loaded with few conditions
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && profileNavItem.isDisplayed();
    }

    /**
     * Method inputs searchTerm into searchField and presses RETURN
     */
    public SearchResultsPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.RETURN);
        return new SearchResultsPage(webDriver);
    }
}

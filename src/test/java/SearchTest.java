import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class SearchTest {
    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://linkedin.com");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    /**
     * PreConditions:
     * - Open new browser.
     * - Navigate to https://linkedin.com.
     *
     * Scenario:
     * - Verify that Login page is loaded.
     * - Login with valid credentials.
     * - Verify that Home page is loaded.
     * - Enter 'searchTerm' into search field and press RETURN key.
     * - Verify that Search page is loaded.
     * - Verify 10 searchResults displayed
     * - Verify each result item contains 'searchTerm'
     *
     * PostCondition:
     * - Close browser.
     */
    @Test
    public void basicSearchTest(){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login("pushkin.oligo+1@gmail.com", "myPasswordQA18");

        Assert.assertTrue(homePage.isPageLoaded(),"HomePage is not displayed.");

        SearchResultsPage searchResultsPage = homePage.search("HR");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(searchResultsPage.isPageLoaded(), "Search results page is not loaded.");
        Assert.assertEquals(searchResultsPage.searchResults.size(), 5);

        for (WebElement searchResult : searchResultsPage.searchResults){
            String searchResultText = searchResult.getText();
            Assert.assertTrue(searchResultText.toLowerCase().contains("hr"));
        }
    }
}

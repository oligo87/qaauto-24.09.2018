package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchResultsPage;

import java.util.List;

public class SearchTest extends BaseTest{

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
        String searchTerm = "HR";

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login("oleg.ilin.amc@gmail.com", "myPasswordQA18");
        Assert.assertTrue(homePage.isPageLoaded(),"page.HomePage is not displayed.");

        SearchResultsPage searchResultsPage = homePage.search(searchTerm);
        Assert.assertTrue(searchResultsPage.isPageLoaded(), "Search results page is not loaded.");
        Assert.assertEquals(searchResultsPage.getSearchResultsCount(), 10, "SearchResultsCount is wrong.");

        List<String> searchResultsList = searchResultsPage.getSearchResults();
        for (String searchResult : searchResultsList){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()), "SearchTerm "+searchTerm+ "is not found in "+searchResult);
        }
    }
}

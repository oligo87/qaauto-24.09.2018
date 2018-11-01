import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class SearchResultsPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//li[@class='search-result search-result__occluded-item ember-view']")
    public List<WebElement> searchResults;

    @FindBy(xpath = "//div[@class='search-results-page core-rail']")
    private WebElement searchResultsList;

    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/search/results/all/")
                && webDriver.getTitle().contains("LinkedIn")
                && searchResultsList.isDisplayed();
    }

    /*public boolean isSearchResultsRelevant() {
         for (WebElement searchResult : searchResults){
            Assert.assertTrue(searchResult.getText().toLowerCase().contains("hr"));
         }
         return
    }*/
}

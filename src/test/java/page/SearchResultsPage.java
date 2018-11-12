package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage{

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResultsList;

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchBar;

    /**
     * Constructor for SearchResultsPage
     * @param webDriver driver instance from tests
     */
    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method to verify that page is loaded with few conditions
     */
    public boolean isPageLoaded() {
        waitUntilElementIsClickable(searchBar);
        return webDriver.getCurrentUrl().contains("/search/results/all/")
                && webDriver.getTitle().contains("| Поиск | LinkedIn");
    }

    /**
     * @return count of searchResultsList entries
     */
    public int getSearchResultsCount() {
        return searchResultsList.size();
    }

    /**
     * @return consequently text of each entry of searchResultsList
     */
    public List<String> getSearchResults() {
        List<String> searchResultsStringList = new ArrayList<String>();
        for (WebElement searchResult : searchResultsList){
            ((JavascriptExecutor) webDriver)
                    .executeScript("arguments[0].scrollIntoView(true);", searchResult);
            String searchResultText = searchResult.getText();
            searchResultsStringList.add(searchResultText);
        }
        return searchResultsStringList;
    }
}

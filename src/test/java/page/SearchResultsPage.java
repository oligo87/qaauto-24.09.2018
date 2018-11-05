package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResultsList;

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchBar;

    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("/search/results/all/")
                && webDriver.getTitle().contains("| Поиск | LinkedIn")
                && searchBar.isDisplayed();
    }

    public int getSearchResultsCount() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return searchResultsList.size();
    }

    public List<String> getSearchResults() {
        List<String> searchResultsStringList = new ArrayList<String>();
        for (WebElement searchResult : searchResultsList){
            String searchResultText = searchResult.getText();
            searchResultsStringList.add(searchResultText);
        }
        return searchResultsStringList;
    }
}

package page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

public abstract class BasePage {

    WebDriver webDriver;
    static GMailService gMailService;

    /**
     * Method for waiting until webElement is clickable
     * @param webElement
     */
    public void waitUntilElementIsClickable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Method to verify that current url contains partialUrl
     * @param partialUrl
     * @param timeOutInSec
     * @return
     */
    public boolean isUrlContains(String partialUrl, int timeOutInSec){
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSec);
        try {
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public abstract boolean isPageLoaded();
}

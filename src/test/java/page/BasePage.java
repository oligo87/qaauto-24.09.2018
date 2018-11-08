package page;

import org.openqa.selenium.WebDriver;
import util.GMailService;

public abstract class BasePage {

    WebDriver webDriver;
    public static GMailService gMailService = new GMailService();

    public abstract boolean isPageLoaded();
}

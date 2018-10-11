import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    /**
     * Preconditions:
     * - Open FF browser
     *
     * Scenario:
     * - Navigate to https://www.linkedin.com/
     * - Verify that login page is loaded
     * - Enter userEmail into userEmail field
     * - Enter userPassword into userPassword field
     * - Click on singin button
     * - Verify that Home page is loaded
     *
     * Postconditions:
     * - Close FF browser
     */
    @Test
    public void successfullLoginTest() {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://linkedin.com");
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/","Home page URL is wrong.");


        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/feed/","Home page URL is wrong.");
        webDriver.quit();
    }
}

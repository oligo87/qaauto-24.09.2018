import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        emailField.sendKeys("pushkin.oligo+1@gmail.com");

        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        passwordField.sendKeys("myPasswordQA18");

        WebElement singinButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));
        singinButton.click();

        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/feed/","Home page URL is wrong.");

        webDriver.quit();
    }
}

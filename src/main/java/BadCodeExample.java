import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static java.lang.Thread.sleep;

public class BadCodeExample {

    public static void main(String args[]) throws InterruptedException {
        String searchTerm = "Selenium";

        System.out.println("Hello world!!");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.google.com/");

        WebElement searchField = webDriver.findElement(By.xpath("//*[@id='lst-ib']"));
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        sleep(3000);
        List<WebElement> searchResults = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println("Search results count: "+searchResults.size());

       // for(int i =0;i<searchResults.size();i++) {
         for (WebElement searchResult : searchResults) {
            String elementText = searchResult.getText();
            System.out.println(elementText);

            if(elementText.toLowerCase().contains(searchTerm.toLowerCase()))
            {
                System.out.println("searchTerm "+searchTerm+" was found");
            }
            else
            {
                System.out.println("searchTerm "+searchTerm+" was NOT found");
            }
        }

    }

}

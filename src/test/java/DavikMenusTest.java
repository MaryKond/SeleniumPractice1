import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DavikMenusTest {
    private static final String DAVIK_URL="https://daviktapes.com";
    private static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        driver = SharedDriver.getWebDriver();
        driver.get(DAVIK_URL);
    }
    @AfterAll
    public static void closeBrowser() {
        driver.quit();
    }

   @Test
    public void actionTest() {
//        pause();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Company']")));
       WebElement company = driver.findElement(By.xpath("//a[text()='Company']"));
       Actions actions = new Actions(driver);
       actions.moveToElement(company).build().perform();

       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Products']")));
       WebElement products = driver.findElement(By.xpath("//a[text()='Products']"));
       actions.moveToElement(products).build().perform();

       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Industries']")));
       WebElement industries = driver.findElement(By.xpath("//a[text()='Industries']"));
       actions.moveToElement(industries).build().perform();

       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Knowledge Center']")));
       WebElement knowledge = driver.findElement(By.xpath("//a[text()='Knowledge Center']"));
       actions.moveToElement(knowledge).build().perform();

       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='CONTACT']")));
       WebElement contact = driver.findElement(By.xpath("//a[text()='CONTACT']"));
       actions.moveToElement(contact).build().perform();

       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='social-icon']")));
       WebElement linkedIn = driver.findElement(By.xpath("//div[@class='social-icon']"));
       actions.moveToElement(linkedIn).click().build().perform();
       String expectedURL = "https://www.linkedin.com/company/davik";

       for(String str : driver.getWindowHandles()){
           driver.switchTo().window(str);
       }
       String actualURL = driver.getCurrentUrl();
       assertEquals(expectedURL, actualURL);

   }
}

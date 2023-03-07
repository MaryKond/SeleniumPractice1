import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class RadioButtonsTest {
    private static final String HOME_PAGE="https://www.facebook.com";
    private static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE);
    }
    @AfterEach
    public void tearDown(){

        driver.get(HOME_PAGE);
    }
    @AfterAll
    public static void closeBrowser() {
        driver.close();
    }

    @Test
    public void radioButtonsTest(){
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']")));
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        driver.findElement(By.xpath("//*[text()='Female']//following-sibling::*[@type='radio']")).click();
        String isFemaleChecked= driver.findElement(By.xpath("//*[text()='Female']//following-sibling::*[@type='radio']")).getAttribute("checked");
        assertEquals("true",isFemaleChecked);

        driver.findElement(By.xpath("//*[text()='Male']//following-sibling::*[@type='radio']")).click();
        String isMaleChecked= driver.findElement(By.xpath("//*[text()='Male']//following-sibling::*[@type='radio']")).getAttribute("checked");
        assertEquals("true",isMaleChecked);

        driver.findElement(By.xpath("//*[text()='Custom']//following-sibling::*[@type='radio']")).click();
        String isCustomChecked= driver.findElement(By.xpath("//*[text()='Custom']//following-sibling::*[@type='radio']")).getAttribute("checked");
        assertEquals("true",isCustomChecked);
    }
}

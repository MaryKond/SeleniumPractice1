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

public class ErrorTest {
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
        driver.quit();
    }

    @Test
    public void errorMessageTest() {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']"))).click();

        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).click();
        WebElement errorName = driver.findElement(By.xpath("//*[contains(text(),'your name?')]"));
        assertNotNull(errorName);
        driver.findElement(By.xpath("//input[@name='lastname']")).click();
        WebElement errorLastName = driver.findElement(By.xpath("//*[contains(text(),'your name?')]"));
        assertNotNull(errorLastName);
        driver.findElement(By.xpath("//input[@name ='reg_email__']")).click();
        WebElement errorEmail = driver.findElement(By.xpath("//*[contains(text(),'you ever need to reset')]"));
        assertNotNull(errorEmail);
    }

    @Test
    public void errorPasswordTest() {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name ='reg_email__']")));
        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("kkmorgano@gmail.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']"))).click();
        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).click();
        WebElement errorReEnterEmail = driver.findElement(By.xpath("//*[contains(text(),'Please re-enter')]"));
        assertNotNull(errorReEnterEmail);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='password_step_input']"))).click();
        WebElement errorPassword = driver.findElement(By.xpath("//*[contains(text(),'Enter a combination of at least')]"));
        assertNotNull(errorPassword);
    }
    @Test
    public void errorGenderTest() {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Kolina");
        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys("Mortina");
        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("kkmorgana@gmail.com");
        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("kkmorgana@gmail.com");
        driver.findElement(By.id("password_step_input")).sendKeys("Sterteo!1234!");
        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Jan");
        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("25");
        driver.findElement(By.xpath("//select[@name ='birthday_year']")).sendKeys("2000");
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        pause();
        WebElement errorGender = driver.findElement(By.xpath("//*[contains(text(),'Please choose a gender')]"));
        assertNotNull(errorGender);

    }



// Yuri, do you use this method in your code?
    public void pause(){
        try{
            Thread.sleep(5);
        }catch(Exception err){
            System.out.println("Wrong");
        }
    }
}
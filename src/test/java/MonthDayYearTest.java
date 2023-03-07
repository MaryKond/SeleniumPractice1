
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MonthDayYearTest {
    private static final String HOME_PAGE = "https://www.facebook.com/";
    private static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeAll
    public static void setUp() {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE);
    }

    @AfterEach
    public void tearDown() {
        driver.get(HOME_PAGE);
    }

    @AfterAll
    public static void closeBrowser() {
        driver.close();
    }


    @ParameterizedTest
    @ValueSource(strings = {"Mar", "Jul", "Nov"})
    public void monthDropMenuTest(String month) {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']")));


        driver.findElement(By.xpath("//*[@title='Month']")).click();
        driver.findElement(By.xpath("//*[text()='" + month + "']")).click();
        Select select = new Select(driver.findElement(By.xpath("//*[@title='Month']")));
        select.selectByVisibleText(month);
        WebElement monthValue = select.getFirstSelectedOption();
        String selectedoption = monthValue.getText();
//           String monthValue=driver.findElement(By.xpath("//*[@title='Month']")).getFirstSelectedOption();
        assertEquals(month, selectedoption);

    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "18", "29"})
    public void DayDropMenuTest(String day) {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']")));

        driver.findElement(By.xpath("//*[@title='Day']")).click();
        driver.findElement(By.xpath("//*[text()='" + day + "']")).click();
        String dayValue = driver.findElement(By.xpath("//*[@title='Day']")).getAttribute("value");
        assertEquals(day, dayValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2020"})
    public void yearParametrizedTest(String yearInput) {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text()='" + yearInput + "']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");
        assertEquals(yearInput, yearValue);
    }

    @Test
    public void tooYoungNegativeTest() {
        // Not allowed to register,younger than 13
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']")));

        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Olianna");
        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys("Kandriko");
        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("oliana.kendren@gmail.com");
        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("oliana.kendren@gmail.com");
        driver.findElement(By.id("password_step_input")).sendKeys("Sterteo!1234!");
        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Jul");
        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("26");
        driver.findElement(By.xpath("//label[text()='Female']")).click();

        String currentYear = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");
        int limit = Integer.valueOf(currentYear) - 12;
        String stringLimit = Integer.toString(limit);
        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text()='" + limit + "']")).click();
        String year = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");
        assertEquals(stringLimit, year);
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Create new account']")));
        String actualURL = driver.getCurrentUrl();
        //String expectedURL="https://www.facebook.com/?ty=tytrue";
        assertEquals(HOME_PAGE, actualURL);
//     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Create new account']"))).click();
//     WebElement errorRegistration = driver.findElement(By.xpath("//*[contains(text(),'We Couldn't Create Your Account')]"));
//     assertNotNull(errorRegistration);
    }


    //CHECKING February 30
    @Test
    public void wrongDateNegativeTest() {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']")));
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Olianna");
        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys("Kandriko");
        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("oliana.kendren@gmail.com");
        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("oliana.kendren@gmail.com");
        driver.findElement(By.id("password_step_input")).sendKeys("Sterteo!1234!");
        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Feb");
        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("30");
        driver.findElement(By.xpath("//select[@name ='birthday_year']")).sendKeys("2002");
        driver.findElement(By.xpath("//label[text()='Female']")).click();
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'The selected date is not valid.')]")));
        WebElement errorDate = driver.findElement(By.xpath("//*[contains(text(),'The selected date is not valid.')]"));
        assertNotNull(errorDate);
    }

}

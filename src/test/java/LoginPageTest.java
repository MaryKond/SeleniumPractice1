import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;


public class LoginPageTest {
    private static final String HOME_PAGE_FACEBOOK = "https://www.facebook.com/";
    WebDriver driver;
    SharedDriver sd = new SharedDriver();


//    @BeforeAll
//    public static void classSetup() throws InterruptedException {
//        driver= SharedDriver.getWebDriver();
//        driver.get(HOME_PAGE_FACEBOOK);
//        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
//        Thread.sleep(1000);
//    }
//    @AfterAll
//    public static void closeBrowser() {
//        SharedDriver.closeDriver();
//    }

    @BeforeEach
    public void testStart() throws InterruptedException {

        driver= sd.getWebDriver();
        driver.get(HOME_PAGE_FACEBOOK);
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        Thread.sleep(1000);
   }
    @AfterEach
    public void testTearDown() throws InterruptedException {
        sd.closeDriver();

    }


    @Test
    public void locateFields() {
        WebElement firstName = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstName);
        WebElement lastName= driver.findElement(By.xpath("//input[@name ='lastname']"));
        assertNotNull(lastName);
        WebElement phoneEmail = driver.findElement(By.xpath("//input[@name ='reg_email__']"));
        assertNotNull(phoneEmail);
        WebElement newPassElement = driver.findElement(By.xpath("//input[@type ='password']"));
        assertNotNull(newPassElement);
        WebElement birthMonth = driver.findElement(By.xpath("//select[@name ='birthday_month']"));
        assertNotNull(birthMonth);
        WebElement birthDay = driver.findElement(By.xpath("//select[@name ='birthday_day']"));
        assertNotNull(birthDay);
        WebElement birthYear = driver.findElement(By.xpath("//select[@name ='birthday_year']"));
        assertNotNull(birthYear);
        WebElement genderFemale = driver.findElement(By.xpath("//label[text()='Female']"));
        assertNotNull(genderFemale);
        WebElement genderMale = driver.findElement(By.xpath("//label[text()='Male']"));
        assertNotNull(genderMale);
        WebElement genderCustom = driver.findElement(By.xpath("//label[text()='Custom']"));
        assertNotNull(genderCustom);
        genderCustom.click();
        WebElement selectPronoun = driver.findElement(By.xpath("//select[@aria-label='Select your pronoun']"));
        assertNotNull(selectPronoun);
        WebElement Gender = driver.findElement(By.xpath("//input[@aria-label ='Gender (optional)']"));
        assertNotNull(Gender);
        WebElement signUpButton = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(signUpButton);


    }

    @Test
    public void allValidDataTest ()throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Koli");

        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys("Mortino");

        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("kkmorgano@gmail.com");

        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("kkmorgano@gmail.com");

        driver.findElement(By.id("password_step_input")).sendKeys("Sterteo!1234!");

        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Jan");

        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("25");

        driver.findElement(By.xpath("//select[@name ='birthday_year']")).sendKeys("2000");

        driver.findElement(By.xpath("//label[text()='Female']")).click();

        driver.findElement(By.name("websubmit")).click();
        Thread.sleep(8000);
        String actualResult=driver.getCurrentUrl();//??
        String expectedResult="https://www.facebook.com/checkpoint";
        assertTrue(actualResult.contains(expectedResult));// string contains, assertTrue,

    }
    @ParameterizedTest
    @ValueSource(strings = {"", "1234567","!@#Okjh"})
    public void invalidNameTest (String a) throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(a);

        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys("Smith");

        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("johnsmith@gmail.com");

        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("johnsmith@gmail.com");

        driver.findElement(By.id("password_step_input")).sendKeys("Teast1234!");

        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Jan");

        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("25");

        driver.findElement(By.xpath("//select[@name ='birthday_year']")).sendKeys("2002");

        driver.findElement(By.xpath("//label[text()='Female']")).click();

        driver.findElement(By.name("websubmit")).click();
        Thread.sleep(10000);

        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_FACEBOOK, actualURL, "url do not match");

    }
    @ParameterizedTest
    @ValueSource(strings = {"", "12jh67","!@#Okjh"})
    public void invalidLastNameTest (String b) throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Konstantin");

        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys(b);

        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("johnsmith@gmail.com");

        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("johnsmith@gmail.com");

        driver.findElement(By.id("password_step_input")).sendKeys("Teast1234!");

        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Jan");

        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("25");

        driver.findElement(By.xpath("//select[@name ='birthday_year']")).sendKeys("2002");

        driver.findElement(By.xpath("//label[text()='Female']")).click();

        driver.findElement(By.name("websubmit")).click();
        Thread.sleep(7000);

        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_FACEBOOK, actualURL, "url do not match");

    }
    @ParameterizedTest
    @ValueSource(strings = {" ","!@#Okjh@bb", "Joe Smith <john.smith@gmail.com>"})
    public void invalidEmailTest (String c) throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Konstantin");

        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys("Prokhorovich");

        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys(c);

        //driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys(c);

        driver.findElement(By.id("password_step_input")).sendKeys("Teast1234!");

        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Jan");

        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("25");

        driver.findElement(By.xpath("//select[@name ='birthday_year']")).sendKeys("2002");

        driver.findElement(By.xpath("//label[text()='Female']")).click();

        driver.findElement(By.name("websubmit")).click();
        Thread.sleep(1000);

        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_FACEBOOK, actualURL, "url do not match");

    }
    @ParameterizedTest
    @ValueSource(strings = {"","@#%11", "aaaaaa"})
    public void invalidPasswordTest (String d) throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Konstantin");

        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys("Popkorenko");

        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("2johnsmith@gmail.com");

        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("2johnsmith@gmail.com");

        driver.findElement(By.id("password_step_input")).sendKeys(d);

        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Jan");

        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("25");

        driver.findElement(By.xpath("//select[@name ='birthday_year']")).sendKeys("2002");

        driver.findElement(By.xpath("//label[text()='Female']")).click();

        driver.findElement(By.name("websubmit")).click();
        Thread.sleep(10000);

        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_FACEBOOK, actualURL, "urls do not match");
    }
    @Test
    public void genderNotChosenTest () throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Konstantin");

        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys("Popkovich");

        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("1johnsmith@gmail.com");

        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("1johnsmith@gmail.com");

        driver.findElement(By.id("password_step_input")).sendKeys("Asd1234%$#");

        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Jan");

        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("25");

        driver.findElement(By.xpath("//select[@name ='birthday_year']")).sendKeys("2002");

        driver.findElement(By.name("websubmit")).click();
        Thread.sleep(1000);

        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_FACEBOOK, actualURL, "urls do not match");
    }







//    @Test
//    public void emptyPronounTest () throws InterruptedException {
//        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Konstantin");
//
//        driver.findElement(By.xpath("//input[@name ='lastname']")).sendKeys("Popko");
//
//        driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("johnsmith@gmail.com");
//
//        driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("johnsmith@gmail.com");
//
//        driver.findElement(By.id("password_step_input")).sendKeys("Asd1234%$#");
//
//        driver.findElement(By.xpath("//select[@name ='birthday_month']")).sendKeys("Jan");
//
//        driver.findElement(By.xpath("//select[@name ='birthday_day']")).sendKeys("25");
//
//        driver.findElement(By.xpath("//select[@name ='birthday_year']")).sendKeys("2002");
//
//        driver.findElement(By.xpath("//label[text()='Custom']")).click();
//
//       driver.findElement(By.xpath("//select[@aria-label='Select your pronoun']")).click();
//       driver.findElement(By.xpath("//label[text()='Select your pronoun']"));
//        driver.findElement(By.xpath("//option[text()='She:Wish her a happy birthday!']"));
//
//
//
//        driver.findElement(By.name("websubmit")).click();
//
//
//        Thread.sleep(1000);
//
//        String actualURL = driver.getCurrentUrl();
//        assertEquals(HOME_PAGE_FACEBOOK, actualURL, "urls do not match");
//    }

}

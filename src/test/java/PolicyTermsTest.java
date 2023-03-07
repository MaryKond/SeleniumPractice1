import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PolicyTermsTest {

        private static final String HOME_PAGE = "https://www.facebook.com";
        private static WebDriver driver;

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
        public static void endTest() {
            driver.close();
        }

        @Test
        public void PolicyTest() {

            driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
            String expectedURL = "https://www.facebook.com/privacy/policy/?entry_point=facebook_page_footer";
            String actualURL = driver.getCurrentUrl();
            assertEquals(expectedURL, actualURL);
        }

        @Test
        public void TermsTest() {
            driver.findElement(By.xpath("//a[text()='Terms']")).click();
            String expectedURL1 = "https://www.facebook.com/policies_center/";
            String actualURL1 = driver.getCurrentUrl();
            assertEquals(expectedURL1, actualURL1);
        }
    }





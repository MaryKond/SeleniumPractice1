import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SharedDriver {

    private static WebDriver webDriver;
    public static WebDriver getWebDriver(){
        if(webDriver==null){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }
        return webDriver;
    }
    public static void closeDriver(){
        if(webDriver!=null){
            webDriver.close();
        }

    }
}
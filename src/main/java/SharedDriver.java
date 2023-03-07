import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;

public class SharedDriver {

    private static WebDriver webDriver;
 
    public static WebDriver getWebDriver(){
        if(webDriver==null){
            WebDriverManager.chromedriver().setup();
            // incognito mode
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--incognito");
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//            options.merge(capabilities);
//            webDriver = new ChromeDriver(options);
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();



        }
        return webDriver;
    }
    public static void closeDriver(){
        if(webDriver!=null){
            webDriver.close();
        }


    }
    public static void quitDriver(){
        if(webDriver!=null){
            webDriver.quit();
        }


    }
}
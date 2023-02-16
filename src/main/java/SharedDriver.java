import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;

public class SharedDriver {

    private WebDriver webDriver;
 
    public  WebDriver getWebDriver(){
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


        }
        return webDriver;
    }
    public void closeDriver(){
        if(webDriver!=null){
            webDriver.close();
        }


    }
}
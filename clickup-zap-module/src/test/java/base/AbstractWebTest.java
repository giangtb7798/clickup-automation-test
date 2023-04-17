package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.zaproxy.clientapi.core.ClientApi;

import java.io.IOException;


public class AbstractWebTest {
    public WebDriver driver;
    public WebElement element;
    public ClientApi api;
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws IOException {

        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8080");
        proxy.setSslProxy("localhost:8080");

        //Setup the web browser
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments(new String[]{"headless"});
        chromeOptions.setProxy(proxy);
        driver = new ChromeDriver(chromeOptions);

    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws IOException {
        driver.close();
    }


}
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import static java.lang.Thread.sleep;

public class ZapTest {
    @Test
    public void zap() throws ClientApiException, InterruptedException {

        ClientApi api;
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8080");
        proxy.setSslProxy("localhost:8080");

        //Setup the web browser
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments(new String[]{"headless"});
        chromeOptions.setProxy(proxy);
        WebDriver driver = new ChromeDriver(chromeOptions);

        System.out.println("start here");
        api = new ClientApi("localhost", 8080);
        api.automation.endDelayJob();
        System.out.println("work here");
        //Open the URL
        driver.get("https://www.facebook.com/");
/*        //Login
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.name("submit")).click();*/
        sleep(5000);
        if(api != null) {
            String title = "ZAP Scanning Report";
            String template = "traditional-html";
            String description = "this this zap test report";
            String reportFileName = "2023-01-28-ZAP-Report-.html";
            String targetFolder = System.getProperty("user.dir");
            api.reports.generate(title, template, null, description, null, null, null, null,
                    null, reportFileName, null, targetFolder, null);
        }

        //Quit the browser
        driver.quit();
    }
}

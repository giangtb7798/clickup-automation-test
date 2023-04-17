import base.AbstractWebTest;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class ZapTest extends AbstractWebTest {

    @Test(description = "before login", groups = {"before_login"})
    public void before_login() throws ClientApiException, InterruptedException, IOException {
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

        api = new ClientApi("localhost", 8080);
        api.automation.endDelayJob();
        //Open the URL
        driver.get("https://app.clickup.com/");

        sleep(5000);

        Path path = Paths.get("security-reports");
        Files.createDirectories(path);
        FileWriter fw = new FileWriter(new File("security-reports/" + "sth" + "_report.xml"));
        fw.write(new String(api.core.xmlreport()));
        fw = new FileWriter(new File("security-reports/" + "index.html"));
        fw.write(new String(api.core.htmlreport()));
        fw.close();

        //Quit the browser
        driver.quit();

    }
    @Test(description = "after login", groups = {"before_login"})
    public void after_login() throws ClientApiException, InterruptedException, IOException {
        String mail = "ntruonggiangtb98@gmail.com";
        String password = "07071998Gg";

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

        api = new ClientApi("localhost", 8080);
        api.automation.endDelayJob();

        //Open the URL
        driver.get("https://app.clickup.com/");

        //login
        driver.findElement(By.id("login-email-input")).sendKeys(mail);
        driver.findElement(By.id("login-password-input")).sendKeys(password);
        driver.findElement(By.xpath("//button[@data-test=\"login-submit\"]")).click();
        WebElement firstResult = new WebDriverWait(driver, 60l)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Home')]")));

        Path path = Paths.get("security-reports");
        Files.createDirectories(path);
        FileWriter fw = new FileWriter(new File("security-reports/" + "sth" + "_report.xml"));
        fw.write(new String(api.core.xmlreport()));
        fw = new FileWriter(new File("security-reports/" + "index.html"));
        fw.write(new String(api.core.htmlreport()));
        fw.close();

        //Quit the browser
        driver.quit();

    }
}

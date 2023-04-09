package base;

import org.example.config.WebAppDriverManager;
import org.example.httprequest.HttpRequests;
import org.testng.annotations.*;

import java.io.IOException;

import static org.example.config.WebAppDriverManager.closeBrowser;
import static org.example.config.WebAppDriverManager.openMultiBrowser;

public class AbstractWebTest {
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws IOException {
        HttpRequests.initReq();
        HttpRequests.setAuthorization("pk_55578828_L0EICZVTIO92ZOPYY9MA7UBI6WZ20JU7");
        HttpRequests.setAllUrl("https://api.clickup.com/api/v2");
        openMultiBrowser(System.getProperty("webBrowser.browser"));
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws IOException {
        closeBrowser(WebAppDriverManager.getDriver());
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters("browser")
    public void beforeSuite(@Optional("hchrome") String browser) {
        System.setProperty("webBrowser.browser", browser);
    }

}
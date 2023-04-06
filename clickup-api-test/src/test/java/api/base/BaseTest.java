package api.base;

import io.restassured.RestAssured;
import org.example.httprequest.HttpRequests;
import org.example.microservices.steps.FolderSteps;
import org.example.microservices.steps.ListSteps;
import org.example.microservices.steps.SpaceSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseTest {
    protected SpaceSteps spaceSteps = new SpaceSteps();
    protected FolderSteps folderSteps = new FolderSteps();

    protected ListSteps listSteps = new ListSteps();

    @BeforeMethod
    public void setUp() throws Exception {
        HttpRequests.initReq();
        HttpRequests.setAuthorization("pk_55578828_L0EICZVTIO92ZOPYY9MA7UBI6WZ20JU7");
        HttpRequests.setAllUrl("https://api.clickup.com/api/v2");
    }
    @BeforeSuite
    public void setupSuite() throws Exception {
        HttpRequests.initReq();
    }
    @AfterSuite
    public void tearDownSuite() throws Exception {
        RestAssured.reset();
    }
    @AfterMethod
    public void tearDown() throws Exception {
        RestAssured.reset();
    }
}

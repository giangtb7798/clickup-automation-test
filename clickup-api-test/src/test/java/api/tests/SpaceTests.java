package api.tests;

import api.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.microservices.models.spacemodels.SpaceInput;
import org.example.microservices.models.spacemodels.SpaceResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

@Epic("Click up")
@Feature("api-space")
public class SpaceTests extends BaseTest {

    @Test(description = "verify that create space successfully")
    public void create_space_successfully() throws IOException {
        String name = "company";
        SpaceInput spaceInput = new SpaceInput(name);

        //Nhập tất cả các trường bắt buộc
        SpaceResponse spaceResponse = (SpaceResponse) spaceSteps
                .when_createSpace(spaceInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(SpaceResponse.class);

        //Bỏ trống trường name
        String emptyName = "";
        spaceInput = new SpaceInput(emptyName);
        spaceSteps.when_createSpace(spaceInput)
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST);

        //name = 1
        int nameNumber = 1;
        spaceInput = new SpaceInput(nameNumber);
        spaceSteps.when_createSpace(spaceInput)
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST);

        //clean data
        spaceSteps
                .when_deleteSpace(spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
    @Test(description = "verify that get space successfully")
    public void get_space_successfully() throws IOException {
        String name = "company";
        SpaceInput spaceInput = new SpaceInput(name);

        //Nhập tất cả các trường bắt buộc
        SpaceResponse spaceResponse = (SpaceResponse) spaceSteps
                .when_createSpace(spaceInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(SpaceResponse.class);

        spaceResponse = (SpaceResponse) spaceSteps.when_getSpaceById(spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(SpaceResponse.class);

        //verify get space is correct
        spaceSteps.then_verifyNameSpaceIsCorrespondingWithNameCreated(name, spaceResponse.getName());

        //clean data
        spaceSteps
                .when_deleteSpace(spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
    @Test(description = "verify that update space successfully")
    public void update_space_successfully() throws IOException {
        String name = "company";
        SpaceInput spaceInput = new SpaceInput(name);

        //Nhập tất cả các trường bắt buộc
        SpaceResponse spaceResponse = (SpaceResponse) spaceSteps
                .when_createSpace(spaceInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(SpaceResponse.class);

        //Bỏ trống trường name
        String emptyName = "";
        spaceInput = new SpaceInput(emptyName);
        spaceSteps.when_editSpace(spaceInput, spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);

        //name = 1
        int nameNumber = 1;
        spaceInput = new SpaceInput(nameNumber);
        spaceSteps.when_editSpace(spaceInput, spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST);

        //clean data
        spaceSteps
                .when_deleteSpace(spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
    @Test(description = "verify that delete space successfully")
    public void delete_space_successfully() throws IOException {
        String name = "company";
        SpaceInput spaceInput = new SpaceInput(name);

        //Nhập tất cả các trường bắt buộc
        SpaceResponse spaceResponse = (SpaceResponse) spaceSteps
                .when_createSpace(spaceInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(SpaceResponse.class);

        //xoá space thành công
        spaceSteps
                .when_deleteSpace(spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
}
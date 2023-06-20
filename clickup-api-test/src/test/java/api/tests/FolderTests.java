package api.tests;

import api.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.microservices.models.foldermodels.FolderInput;
import org.example.microservices.models.foldermodels.FolderResponse;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

@Epic("Click up")
@Feature("api-folder")
public class FolderTests extends BaseTest {
    @Test(description = "verify that delete folder successfully", groups={"folder"})
    public void delete_folder_successfully() throws Exception {
        String name = "study";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) folderSteps
                .when_createFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        //delete data
        folderSteps
                .when_deleteFolder(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);

    }
    @Test(description = "verify that delete folder successfully", groups={"folder"})
    public void delete_folder_successfully_2() throws Exception {
        String name = "study";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) folderSteps
                .when_createFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        //delete data
        folderSteps
                .when_deleteFolder(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);

    }
    @Test(description = "get folder successfully")
    public void get_folder_successfully() throws Exception {
        String name = "study";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) folderSteps
                .when_createFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        folderResponse = (FolderResponse) folderSteps
                .when_getFolderById(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        folderSteps.then_verifyNameSpaceIsCorrespondingWithNameCreated(name, folderResponse.getName());

        //clean data
        folderSteps
                .when_deleteFolder(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
}

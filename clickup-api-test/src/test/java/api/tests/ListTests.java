package api.tests;

import api.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.microservices.models.listmodels.FolderInput;
import org.example.microservices.models.listmodels.FolderResponse;
import org.example.microservices.models.listmodels.FolderResponseList;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

@Epic("Click up")
@Feature("api-list")
public class ListTests extends BaseTest {

    @Test(description = "verify that create list successfully")
    public void create_list_successfully() throws Exception {
        String name = "nameList";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) listSteps
                .when_createListInFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        // name = "";
        name = "";
        folderInput = new FolderInput(name);
        listSteps
                .when_createListInFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST)
                .saveResponseObject(FolderResponse.class);

        //name = 1
        int nameNumber = 1;
        folderInput = new FolderInput(nameNumber);
        listSteps
                .when_createListInFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST)
                .saveResponseObject(FolderResponse.class);

        //name = "q!"
        name = "q!";
        folderInput = new FolderInput(nameNumber);
        listSteps
                .when_createListInFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST)
                .saveResponseObject(FolderResponse.class);

        //name = "nameList"
        name = "nameList";
        folderInput = new FolderInput(nameNumber);
        listSteps
                .when_createListInFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_BAD_REQUEST)
                .saveResponseObject(FolderResponse.class);

        //clean data
        listSteps.when_deleteList(folderResponse.getId());
    }
    @Test(description = "verify that get list successfully")
    public void get_list_successfully() throws IOException {
        String name = "nameList";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) listSteps
                .when_createListInFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        folderResponse = (FolderResponse) listSteps
                .when_getList(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        listSteps.then_verifyNameSpaceIsCorrespondingWithNameCreated(name, folderResponse.getName());

        //clean data
        listSteps.when_deleteList(folderResponse.getId());
    }
    @Test(description = "verify that update list successfully")
    public void update_list_successfully() throws IOException {
        String name = "nameList";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) listSteps
                .when_createListInFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        name = "nameListUpdated";
        folderInput = new FolderInput(name);
        listSteps
                .when_updateList(folderInput, folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        name = "";
        folderInput = new FolderInput(name);
        listSteps
                .when_updateList(folderInput, folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        name = "q!";
        folderInput = new FolderInput(name);
        listSteps
                .when_updateList(folderInput, folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        //clean data
        listSteps.when_deleteList(folderResponse.getId());

    }
    @Test(description = "verify that delete list successfully")
    public void delete_list_successfully() throws IOException {
        String name = "nameList";
        FolderInput folderInput = new FolderInput(name);
        FolderResponse folderResponse = (FolderResponse) listSteps
                .when_createListInFolder(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        //delete list
        listSteps
                .when_deleteList(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);

        FolderResponseList folderResponseLists = (FolderResponseList) listSteps
                .when_getLists()
                .saveResponseObject(FolderResponseList.class);

        listSteps.then_verifyListIsDeleted(folderResponseLists, folderResponse.getId());
    }
}

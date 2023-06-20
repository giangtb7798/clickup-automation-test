package tests;

import base.AbstractWebTest;
import org.example.microservices.models.foldermodels.FolderInput;
import org.example.microservices.models.listmodels.FolderResponse;
import org.example.microservices.models.listmodels.FolderResponseList;
import org.example.microservices.models.spacemodels.SpaceInput;
import org.example.microservices.models.spacemodels.SpaceResponse;
import org.example.microservices.steps.FolderSteps;
import org.example.microservices.steps.ListSteps;
import org.example.microservices.steps.SpaceSteps;
import org.example.pagesobject.LoginPage;
import org.example.pagesobject.NavigationPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import static org.example.constants.ClickUpMessage.*;


public class FolderTests extends AbstractWebTest {
    SpaceResponse spaceResponse;

    FolderResponse folderResponse;
    SpaceSteps spaceSteps = new SpaceSteps();
    ListSteps listSteps = new ListSteps();
    FolderSteps folderSteps = new FolderSteps();
    String name = "bigCompany";

    String folderName = "bigFolder";

    @BeforeMethod(alwaysRun = true)
    public void login() throws IOException {
        String email = "ntruonggiangtb98@gmail.com";
        String password = "07071998Gg";
        SpaceInput spaceInput = new SpaceInput(name);
        NavigationPage navigationPage = new NavigationPage();

        spaceResponse = (SpaceResponse) spaceSteps.when_createSpace(spaceInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(SpaceResponse.class);

        FolderInput folderInput = new FolderInput(folderName);
        folderResponse = (FolderResponse) folderSteps
                .when_createFolder(folderInput, spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);

        new LoginPage()
                .goToLoginPage()
                .enterEmailAddress(email)
                .enterPassword(password)
                .clickToSubmitBtn();

    }

    @Test(description = "verify create folder function works")
    public void verify_create_folder_function_works() throws IOException, InterruptedException {
        NavigationPage navigationPage = new NavigationPage();
        String folderName = "hand over";
        navigationPage
                .clickToSpaceSettingBtn(name)
                .clickToOptionInSpaceSetting(MENU_SPACE_SETTING[0])
                .verifyContentInMenuCreateNewIsCorrect()
                .clickToOptionInSpaceSetting(MENU_CREATE_NEW[3])
                .verifyCreateFolderDialogDisplayed()
                .clickToCreateFolderBtn()
                .verifyWarningMessageRequiredFolderName()
                .enterFolderName(folderName)
                .clickToCreateFolderBtn()
                //verify folder created successfully
                .verifyFolderNameDisplayedAndLinkedToSpace(folderName);
    }
    @Test(description = "verify create list in folder works")
    public void verify_create_list_in_folder_successfully() throws IOException, InterruptedException {
        String listName = "staffList";
        NavigationPage navigationPage = new NavigationPage();

        navigationPage
                .clickToSpace(name)
                .clickToFolder(folderName)
                .clickToFolderSetting(folderName)
                .clickToOptionFolderSetting(FOLDER_SETTING[0])
                .clickToOptionFolderSetting(MENU_CREATE_NEW[0])
                .verifyCreateListDialogDisplayed()
                .enterListName(listName)
                .clickToCreateListBtn()
                //.clickToSpace(name)
                .verifyListDisplayed(listName);

    }

    @Test(enabled = false, description = "verify create docs in folder works")
    public void verify_create_docs_in_folder_successfully() throws IOException, InterruptedException {
        name = "do not edit";
        String folderName = "folder do not edit";
        String listName = "staffList";
        NavigationPage navigationPage = new NavigationPage();

        navigationPage
                .clickToSpace(name)
                .clickToFolder(folderName)
                .clickToFolderSetting(folderName)
                .clickToOptionFolderSetting(FOLDER_SETTING[0])
                .clickToOptionFolderSetting(MENU_CREATE_NEW[1])
                //.verifyListDisplayed(listName)
                ;

        FolderResponseList folderResponseLists = (FolderResponseList) listSteps
                .when_getLists()
                .saveResponseObject(FolderResponseList.class);
        List<FolderResponse> folderResponse = folderResponseLists.getLists();
        listSteps
                .when_deleteList(folderResponse.get(folderResponse.size() - 1).getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
    @AfterMethod(alwaysRun = true)
    public void cleanData() throws IOException {

        //clean data
        spaceSteps
                .when_deleteSpace(spaceResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);

    }
}

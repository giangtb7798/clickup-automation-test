package tests;

import base.AbstractWebTest;
import org.example.microservices.models.foldermodels.FolderResponse;
import org.example.microservices.models.listmodels.FolderInput;
import org.example.microservices.steps.ListSteps;
import org.example.models.TaskModel;
import org.example.pagesobject.LoginPage;
import org.example.pagesobject.NavigationPage;
import org.example.pagesobject.TaskPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class TaskTests extends AbstractWebTest {
    ListSteps listSteps = new ListSteps();
    FolderResponse folderResponse;
    @BeforeMethod(alwaysRun = true)
    public void logIn() throws Exception {
        String email = "ntruonggiangtb98@gmail.com";
        String password = "07071998Gg";
        String listName = "myNameList";

        new LoginPage()
                .goToLoginPage()
                .enterEmailAddress(email)
                .enterPassword(password)
                .clickToSubmitBtn();

        FolderInput folderInput = new FolderInput(listName);
        folderResponse = (FolderResponse) listSteps
                .when_createListInSpace(folderInput)
                .validateResponse(HttpURLConnection.HTTP_OK)
                .saveResponseObject(FolderResponse.class);
    }

    @Test(description = "create task")
    public void create_task() throws IOException {
        String taskName = "taskName";
        String spaceName = "do not edit";

        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();
        navigationPage.refreshCurrentPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToFieldTaskName()
                .enterTaskName(taskName)
                .clickToSaveTask()
                .verifyTaskNameCreatedSuccessfully(taskName);

        //delete list
        listSteps
                .when_deleteList(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }

    @Test(description = "Sort by task name")
    public void sort_by_task_name() throws IOException, InterruptedException {
        String spaceName = "do not edit";

        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToSortDropDown()
                .clickToSortDropDownByTaskName();

        List<TaskModel> taskList = taskPage.getAllTaskName();

        //verify that list is sorted in nature
        taskPage.verifyTaskNameIsSortedInNature(taskList);
    }
    @Test(description = "Verify search task works well")
    public void search_task_works_well() throws IOException, InterruptedException {
        String keyword = "da";
        String spaceName = "do not edit";

        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToBoard()
                .enterKeywordToSearch(keyword);

        List<TaskModel> taskList = taskPage.getAllTaskName();

        taskPage.verifySearchResultIncludeKeyword(taskList, keyword);
    }
    @Test(description = "verify create subtask function")
    public void verifyCreateSubtaskFunction() throws IOException, InterruptedException {
        String taskName = "taskName";
        String subtaskName = "subtaskName";
        String spaceName = "do not edit";
        NavigationPage navigationPage = new NavigationPage();
        TaskPage taskPage = new TaskPage();
        navigationPage.refreshCurrentPage();

        navigationPage
                .clickToSpace(spaceName);

        taskPage
                .clickToFieldTaskName()
                .enterTaskName(taskName)
                .clickToSaveTask()
                .clickToCreateSubtask()
                .enterSubtaskName(subtaskName)
                .clickToSaveTask();

    }
    @AfterMethod(alwaysRun = true)
    public void cleanData() throws IOException {
        //delete list
        listSteps
                .when_deleteList(folderResponse.getId())
                .validateResponse(HttpURLConnection.HTTP_OK);
    }
}

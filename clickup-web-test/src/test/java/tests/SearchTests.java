package tests;

import base.AbstractWebTest;

import org.example.models.SearchResults;
import org.example.pagesobject.LoginPage;
import org.example.pagesobject.NavigationPage;
import org.example.pagesobject.SearchPage;
import org.example.utils.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.example.constants.ClickUpMessage.*;


public class SearchTests extends AbstractWebTest {
    @BeforeMethod(alwaysRun = true)
    public void login() throws IOException {
        String email = "ntruonggiangtb98@gmail.com";
        String password = "07071998Gg";
        NavigationPage navigationPage = new NavigationPage();
        new LoginPage()
                .goToLoginPage()
                .enterEmailAddress(email)
                .enterPassword(password)
                .clickToSubmitBtn();
    }

    @Test(description = "verify that search feature works well")
    public void verify_search_feature_works_well() throws InterruptedException {
        NavigationPage navigationPage = new NavigationPage();
        SearchPage searchPage = new SearchPage();
        String randomStr = RandomStringUtils.randomAlphabetic(5);

        navigationPage
                .clickToSearchField();

        //find space name
        List<SearchResults> searchResults = searchPage
                .enterSearchValue(SPACE_NAME)
                .getAllResults();
        searchPage.verifyThatResultsIncludeKeyword(searchResults, SPACE_NAME, SUBTITLE[0]);

        //find folder
        searchResults = searchPage
                .enterSearchValue(FOLDER_NAME)
                .getAllResults();
        searchPage.verifyThatResultsIncludeKeyword(searchResults, FOLDER_NAME, SUBTITLE[1]);

        //find task
        searchResults = searchPage
                .enterSearchValue(TASK_NAME)
                .getAllResults();
        searchPage.verifyThatResultsIncludeKeyword(searchResults, TASK_NAME, SUBTITLE[2]);

        //check case not found in search
        searchResults = searchPage
                .enterSearchValue(randomStr)
                .getAllResults();
        searchPage.verifyMessageNotFoundDisplayed();

    }
}

package org.example.microservices.steps;

import io.qameta.allure.Step;
import org.example.core.BaseApi;
import org.example.microservices.models.listmodels.FolderInput;
import org.example.microservices.models.listmodels.FolderResponse;
import org.example.microservices.models.listmodels.FolderResponseList;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.constaints.ClickUpConstants.FOLDER_ID;
import static org.example.constaints.ClickUpConstants.SPACE_ID;
import static org.example.constaints.EndPoints.*;

public class ListSteps extends BaseApi {
    @Step("create list in folder")
    public ListSteps when_createListInFolder(FolderInput folderInput) throws IOException {
        sendPost(CREATE_LIST_IN_FOLDER_URI, folderInput, "folder_id", FOLDER_ID);
        return this;
    }
    @Step("create list in space")
    public ListSteps when_createListInSpace(FolderInput folderInput) throws IOException {
        sendPost(CREATE_LIST_IN_SPACE_URI, folderInput, "space_id", SPACE_ID);
        return this;
    }
    @Step("update list id by list id")
    public ListSteps when_updateList(FolderInput folderInput, String listId) throws IOException {
        sendPut(UPDATE_LIST_URI, folderInput, "list_id", listId);
        return this;
    }
    @Step("delete list by list id")
    public ListSteps when_deleteList(String listId) throws IOException {
        sendDelete(DELETE_LIST_URI, "list_id", listId);
        return this;
    }
    @Step("get list by list id")
    public ListSteps when_getList(String listId) throws IOException {
        sendGet(GET_LIST_URI, "list_id", listId);
        return this;
    }
    @Step("get lists")
    public ListSteps when_getLists() throws IOException {
        sendGet(GET_LISTS_URI, "folder_id", FOLDER_ID);
        return this;
    }

    @Step("verify name folder just created is correct")
    public ListSteps then_verifyNameSpaceIsCorrespondingWithNameCreated(String actual, String expected) {
        assertThat(actual)
                .as("name is not correct")
                .isEqualTo(expected);
        return this;
    }

    @Step("verify list is deleted")
    public ListSteps then_verifyListIsDeleted(FolderResponseList folderResponseLists, String listId) {
        List<FolderResponse> list = folderResponseLists.getLists();
        assertThat(list)
                .as("this list still on the lists")
                .filteredOn(l -> l.getId() == listId)
                .hasSize(0);
        return this;
    }

    @Step("verify value of fields is null or not ")
    public ListSteps then_verifyValueOfFieldsNull(String ...fieldName) {
        for(String f : fieldName) {
            assertThat(f)
                    .as("this field is not null")
                    .isNull();
        }
        return this;
    }
}

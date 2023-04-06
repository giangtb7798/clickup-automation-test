package org.example.microservices.steps;

import io.qameta.allure.Step;
import org.example.core.BaseApi;
import org.example.microservices.models.spacemodels.SpaceInput;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.constaints.ClickUpConstants.TEAM_ID;
import static org.example.constaints.EndPoints.*;

public class SpaceSteps extends BaseApi {
    @Step("Create space")
    public SpaceSteps when_createSpace(SpaceInput spaceInput) throws IOException {
        sendPost(CREATE_SPACE_URI, spaceInput, "team_id", TEAM_ID);
        return this;
    }
    @Step("Edit space by id")
    public SpaceSteps when_editSpace(SpaceInput spaceInput, String spaceId) throws IOException {
        sendPut(UPDATE_SPACE_URI, spaceInput, "space_id", spaceId);
        return this;
    }
    @Step("Get space by space id")
    public SpaceSteps when_getSpaceById(String spaceId) throws IOException {
        sendGet(GET_SPACE_URI, "space_id", spaceId);
        return this;
    }
    @Step("Get all space")
    public SpaceSteps when_getAllSpace() throws IOException {
        sendGet(GET_SPACES_URI, "team_id", TEAM_ID);
        return this;
    }
    @Step("Delete space by space id")
    public SpaceSteps when_deleteSpace(String spaceId) throws IOException {
        sendDelete(DELETE_SPACE_URI, "space_id", spaceId);
        return this;
    }
    @Step("verify that name is corresponding with name created")
    public SpaceSteps then_verifyNameSpaceIsCorrespondingWithNameCreated(String actual, String expected) {
        assertThat(actual)
                .as("name is not correct")
                .isEqualTo(expected);
        return this;
    }
}
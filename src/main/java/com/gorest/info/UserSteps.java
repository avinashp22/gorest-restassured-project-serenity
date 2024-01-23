package com.gorest.info;

import com.gorest.constants.EndPoints;
import com.gorest.constants.Path;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import java.util.HashMap;

public class UserSteps {

    static String bearerToken = "Bearer 9317fbf25b647c14ca6b590d6b15424038693a09192c4e774f2c9274d7f6a5f1";

    @Step("Create new user with name : {0}, email : {1}, gender : {2}, status : {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given()
                .body(userPojo)
                .header("Content-Type", "application/json")
                .header("Authorization", bearerToken)
                .when()
                .post(Path.USER)
                .then().log().all();
    }

    @Step("Read new user with ID : {0}")
    public HashMap<String, Object> readUser(int userId) {

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", bearerToken)
                .pathParam("userID", userId)
                .when()
                .get(Path.USER + EndPoints.GET_SINGLE_USER_BY_ID)
                .then().statusCode(200).extract().path("");
    }

    @Step("Update user with Id : {0},name : {1}, email : {2}, gender {3}, status : {4}")
    public ValidatableResponse updateUser(int userId, String name, String email, String gender, String status) {

        UserPojo usersPojo = new UserPojo();
        usersPojo.setName(name);
        usersPojo.setEmail(email);
        usersPojo.setGender(gender);
        usersPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", bearerToken)
                .pathParam("userID", userId)
                .body(usersPojo)
                .when()
                .put(Path.USER + EndPoints.UPDATE_USER_BY_ID)
                .then().log().all();
    }

    @Step("Delete user with Id : {0}")
    public ValidatableResponse deleteUser(int userId) {
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", bearerToken)
                .pathParam("userID", userId)
                .when()
                .delete(Path.USER + EndPoints.DELETE_USER_BY_ID)
                .then().log().all();
    }

    @Step("Read user information with Id : {0}")
    public ValidatableResponse getUserById(int userId) {
        return SerenityRest.given()
                .pathParam("userID", userId)
                .when()
                .get(Path.USER + EndPoints.GET_SINGLE_USER_BY_ID)
                .then().log().all();
    }
}
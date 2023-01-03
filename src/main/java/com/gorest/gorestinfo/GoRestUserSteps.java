package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.GoRestUserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.TestsRequirement;
import net.thucydides.core.annotations.Title;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GoRestUserSteps {
    @Step("Creating user with name: {0}, email: {1}, gender: {2} and status: {3}")

    public ValidatableResponse creatUserSuccessfully(String name, String email, String gender, String status) {
        GoRestUserPojo goRestUserPojo = new GoRestUserPojo();
        goRestUserPojo.setName(name);
        goRestUserPojo.setEmail(email);
        goRestUserPojo.setGender(gender);
        goRestUserPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 3fd5c5d69049fe0983c50eccc57a83d284bd9dbcff3d769acd739294f73b86bb")
                .body(goRestUserPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the user information with user email : {0}")
    public HashMap<String, Object> getUserByEmail(String email) {

        return SerenityRest.given().log().all()

                .when()
                .header("Authorization", "Bearer 3fd5c5d69049fe0983c50eccc57a83d284bd9dbcff3d769acd739294f73b86bb")
                .get()
                .then().log().all()
                .statusCode(200)
                .extract()
                .path("findAll{it.email == '" + email + "'}.get(0)");

    }

    @Step("Updating user with  user_id :{0},name: {1}, email: {2}, gender: {3} and status: {4} ")
    public ValidatableResponse updateUsers(int userId, String name, String email, String gender, String status) {
        GoRestUserPojo goRestUserPojo = new GoRestUserPojo();
        goRestUserPojo.setName(name);
        goRestUserPojo.setEmail(email);
        goRestUserPojo.setGender(gender);
        goRestUserPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 3fd5c5d69049fe0983c50eccc57a83d284bd9dbcff3d769acd739294f73b86bb")
                .header("Content-Type", "application/json")

                .pathParam("user_id", userId)
                .body(goRestUserPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();



    }
   @Step("Delete user and verify")
    public ValidatableResponse deleteUser(int userId){
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 3fd5c5d69049fe0983c50eccc57a83d284bd9dbcff3d769acd739294f73b86bb")
                .pathParam("user_id", userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
   }
   @Step("verify")
    public ValidatableResponse getUserById(int userId){
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 3fd5c5d69049fe0983c50eccc57a83d284bd9dbcff3d769acd739294f73b86bb")
                .pathParam("user_id", userId)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
   }


    }


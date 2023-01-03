package com.gorest.testsuits;

import com.gorest.gorestinfo.GoRestUserSteps;
import com.gorest.testbase.TerstBase;
import com.gorest.testbase.TerstBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class CRUDTest extends TerstBase {
    static int userId;
    static String name = "siddhi" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "sidtha@gmail.com";
    static String gender = "Female";
    static String status = "active";


    @Steps
    GoRestUserSteps userSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.creatUserSuccessfully(name, email, gender, status);
        response.log().all().statusCode(201);


    }

    @Title("verify user added")
    @Test
    public void test002() {
        HashMap<String, Object> UserMap = userSteps.getUserByEmail(email);
        Assert.assertThat(UserMap, hasValue(email));
        userId = (int) UserMap.get("id");
    }

    @Title("Update user and verify")
    @Test
    public void test003() {

        name = name +"_bruno";
        userSteps.updateUsers(userId,name, email, gender, status)
                .log().all().statusCode(200);
        HashMap<String, Object> userMap = userSteps.getUserByEmail(email);
        Assert.assertThat(userMap, hasValue(email));

    }
    @Title("Delete  user and verify")
    @Test
    public  void test004(){
        userSteps.deleteUser(userId).statusCode(204);
        userSteps.getUserById(userId).statusCode(404);

    }
}


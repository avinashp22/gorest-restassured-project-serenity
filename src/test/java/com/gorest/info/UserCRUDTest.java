package com.gorest.info;

import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {

    static int usersId;
    static String name = TestUtils.getRandomValue() + "PrimeUser";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String gender = "male";
    static String status = "active";

    @Steps
    UserSteps steps;

    @Title("This will create the user")
    @Test
    public void T1() {

        ValidatableResponse response = steps.createUser(name, email, gender, status);
        response.log().all().statusCode(201);
        usersId = response.log().all().extract().path("id");
        System.out.println(usersId);

    }

    @Title("This will read the user")
    @Test
    public void T2() {
        HashMap<String, Object> userMap = steps.readUser(usersId);
        Assert.assertThat(userMap, hasValue(name));
    }

    @Title("This will update the user")
    @Test
    public void T3() {
        name = "Avi" + TestUtils.getRandomValue();
        email = "avi@gmail.com" + TestUtils.getRandomValue();
        steps.updateUser(usersId, name, email, gender, status).statusCode(200);

        HashMap<String, Object> userMap = steps.readUser(usersId);
        Assert.assertThat(userMap, hasValue(usersId));

    }

    @Title("This will delete the user")
    @Test
    public void T4() {
        steps.deleteUser(usersId).statusCode(204);
        steps.getUserById(usersId).statusCode(404);
    }
}

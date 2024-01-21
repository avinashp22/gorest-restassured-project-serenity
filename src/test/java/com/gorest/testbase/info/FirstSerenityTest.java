package com.gorest.testbase.info;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Jay
 */
@RunWith(SerenityRunner.class)
public class FirstSerenityTest extends TestBase {

    @Test
    public void getAllStudents(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Title("This test will get the information of all students")
    @Test
    public void test001(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

}

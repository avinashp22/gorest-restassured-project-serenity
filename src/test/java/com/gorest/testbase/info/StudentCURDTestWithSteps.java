package com.gorest.testbase.info;

import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Jay
 */
@RunWith(SerenityRunner.class)
public class StudentCURDTestWithSteps extends TestBase {

    static String firstName = TestUtils.getRandomValue() + "PrimeUser";
    static String lastName = TestUtils.getRandomValue() + "PrimeUser";
    static String programme = "Api Testing";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static int studentId;

    @Steps
    StudentSteps steps;


    @Title("This will crate a new student")
    @Test
    public void test001() {
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Rest Assured");

        ValidatableResponse response = steps.createStudent(firstName, lastName, email, programme, courseList);
        response.statusCode(201);
    }

    @Title("Verify if the student was added to the application")
    @Test
    public void test002() {

        HashMap<String, Object> studentMap = steps.getStudentInfoByFirstName(firstName);

        Assert.assertThat(studentMap, hasValue(firstName));

        studentId = (int) studentMap.get("id");

    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        firstName = firstName + "_updated";

        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Rest Assured");

        steps.updateStudent(studentId, firstName, lastName, email, programme, courseList).statusCode(200);

        HashMap<String, Object> studentMap = steps.getStudentInfoByFirstName(firstName);
        Assert.assertThat(studentMap, hasValue(firstName));
    }

    @Title("Delete the student and verify if the student is deleted")
    @Test
    public void test004() {
        steps.deleteStudent(studentId).statusCode(204);
        steps.getStudentInfoById(studentId).statusCode(404);
    }

}

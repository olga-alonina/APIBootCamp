package com.cydeo.tests.P03_authorizations;

import com.cydeo.utility.SpartanAuthTestbase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
@Tag("spartan")
public class P01_SpartanAuthTest extends SpartanAuthTestbase {

    @Test
    public void testPublicUser() {
        given().accept(ContentType.JSON)
                .get("/spartans").prettyPeek().
                then().log().ifValidationFails()
                .statusCode(401);
    }

    @Test
    public void testAuthenticatedUser() {
        given().accept(ContentType.JSON)
                .auth().basic("user","user")
                .get("/spartans").prettyPeek()
                .then().log().ifValidationFails()
                .statusCode(200);
    }


    @Test
    public void testEditorDELETE() {
        given().accept(ContentType.JSON)
                .pathParam("id",1412)
                .auth().basic("editor","editor")
                .delete("/spartans/{id}").prettyPeek()
                .then().log().ifValidationFails()
                .statusCode(403);
    }


    @ParameterizedTest
    @ValueSource(strings = {"admin","editor","user"})
    void testAllUsersGET(String role ) {

        given().accept(ContentType.JSON)
                .auth().basic(role,role)
                .get("/spartans").prettyPeek()
                .then().log().ifValidationFails()
                .statusCode(200);

    }

    @ParameterizedTest
    @CsvFileSource(resources ="/GETSpartans.csv" ,numLinesToSkip = 1)
    public void testAllUsersCSVFile(String username,String password) {

      given().accept(ContentType.JSON)
              .auth().basic(username,password)
              .get("/spartans")
              .then().log().ifValidationFails()
              .statusCode(200);

    }

    @ParameterizedTest
    @CsvFileSource(resources ="/DELETESpartans.csv" ,numLinesToSkip = 1)
    public void DELETESpartanTest(String username,String password,int id, int statusCode) {

        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .auth().basic(username,password)
                .delete("/spartans/{id}")
                .then().log().all()
                .statusCode(statusCode);

    }
}

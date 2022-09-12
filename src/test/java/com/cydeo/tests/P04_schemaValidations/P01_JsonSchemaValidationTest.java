package com.cydeo.tests.P04_schemaValidations;

import com.cydeo.utility.SpartanTestbase;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P01_JsonSchemaValidationTest extends SpartanTestbase {
    // Send request to GET /spartans
    // and verify the response json match the schema document
    // under resources folder
    // with the name of AllSpartansSchema.json


    @Test
    public void allSpartanSchemaValidaton() {


        given().log().uri().
        when()
                .get("/spartans").
        then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("AllSpartanSchema.json"));
    }





}


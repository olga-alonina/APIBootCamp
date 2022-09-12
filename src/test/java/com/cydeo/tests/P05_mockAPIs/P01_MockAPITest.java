package com.cydeo.tests.P05_mockAPIs;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P01_MockAPITest {

    @BeforeAll
    public static void init(){
        baseURI="https://5c7a36d8-2988-4240-8974-e037659cd88d.mock.pstmn.io";
    }

    @AfterAll
    public static void destroy(){
        reset();
    }

    @Test
    public void mockSpartans() {

        given().log().uri().
        when()
                .get("/spartans").prettyPeek().
        then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }
}

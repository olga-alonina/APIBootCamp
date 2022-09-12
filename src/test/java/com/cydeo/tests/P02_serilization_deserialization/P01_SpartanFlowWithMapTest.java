package com.cydeo.tests.P02_serilization_deserialization;

import com.cydeo.utility.SpartanTestbase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class P01_SpartanFlowWithMapTest extends SpartanTestbase {

        /*

    Create a Spartan Flow to run below testCases dynamicly

    - POST     /spartans
            Create a spartan Map
                name = "BootCamp POST "
                gender="Male"
                phone=1231231231l

            - verfiy status code 201
            - success is "A Spartan is Born!"
            - take spartanID from response and save as a global variable

    - PUT  Spartan with spartanID    /spartans/{id}

             Create a spartan Map
                name = "BootCamp PUT "
                gender="Female"
                phone=3213213213l

             - verify status code 204

    - PATCH  Spartan with spartanID    /spartans/{id}

             Create a spartan Map
                name = "BootCamp PATCH "
             - verify status code 204

    - GET  Spartan with spartanID     /spartans/{id}


             - verify status code 200
             - verfiy name is BootCamp PUT

    - DELETE  Spartan with spartanID   /spartans/{id}


             - verify status code 204

     - GET  Spartan with spartanID   /spartans/{id}


             - verify status code 404
             - verfiy name is BootCamp PUT


     */

    static int spartanID;
    @Order(1)
    @Test
    public void postSpartan() {

        Map<String,Object> spartanMap=new HashMap<>();

        spartanMap.put("name", "BootCamp POST");
        spartanMap.put("gender", "Male");
        spartanMap.put("phone", 1231231231l);


        JsonPath jp = given().log().uri()
                .contentType(ContentType.JSON)
                .body(spartanMap).
         when()
                .post("/spartans").prettyPeek().
         then()
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();


         spartanID = jp.getInt("data.id");


    }

    @Order(2)
    @Test
    public void putSpartan() {

        Map<String,Object> spartanMap=new HashMap<>();

        spartanMap.put("name", "BootCamp PUT");
        spartanMap.put("gender", "Female");
        spartanMap.put("phone", 3213213213l);

        given().log().uri()
                .pathParam("id", spartanID)
                 .contentType(ContentType.JSON)
                  .body(spartanMap).
        when()
                .put("/spartans/{id}").prettyPeek().
        then()
                .statusCode(204);

    }
    @Order(3)
    @Test
    public void patchSpartan() {
        Map<String,Object> spartanMap=new HashMap<>();

        spartanMap.put("name", "BootCamp PATCH");


        given().log().uri()
                .pathParam("id", spartanID)
                .contentType(ContentType.JSON)
                .body(spartanMap).
        when()
                .patch("/spartans/{id}").prettyPeek().
         then()
                .statusCode(204);
    }
    @Order(4)
    @Test
    public void getSpartan(){


        given().log().uri()
                .pathParam("id", spartanID).
        when()
                .get("/spartans/{id}").prettyPeek().
        then()
                .statusCode(200)
                .body("name", is("BootCamp PATCH"));


    }

    @Order(5)
    @Test
    public void deleteSpartan() {
        given().log().uri()
                .pathParam("id", spartanID).
        when()
                .delete("/spartans/{id}").
        then()
                .statusCode(204);
    }

    @Order(6)
    @Test
    public void getAfterDelete() {

        given()
                .pathParam("id", spartanID).
        when()
                .get("/spartans/{id}").prettyPeek().
        then()
                .statusCode(404);

    }
}

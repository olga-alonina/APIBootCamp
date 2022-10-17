package com.cydeo.tests.P01_verifications;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P04_HamCrestMatchersTest extends HrTestBase {

    /*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
               region ids needs to be 1,2,3,4
               ...
               ..
               .
    */

    @Test
   public  void getAllRegions() {


        given()
                .accept(ContentType.JSON)
                .log().uri().
        when()
                .get("/regions").prettyPeek().
        then()
                .statusCode(200)
                .header("Date", notNullValue())
                .body("items.region_name",hasItem("Europe"))
                .body("items.region_id",hasItem(1))
                .body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia"))
                .body("items",hasSize(25))
        ;









    }
}

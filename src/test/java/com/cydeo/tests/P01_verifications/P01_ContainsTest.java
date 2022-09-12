package com.cydeo.tests.P01_verifications;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
@Tag("contains")
public class P01_ContainsTest extends HrTestBase {

    /*
       Given
                accept type is application/json
        When
                user sends get request to /regions/2
        Then
                response status code must be 200
                content type equals to application/json
                response body contains   Americas

     */

    @Test
    public void getOneRegion() {

        Response response = given()
                .accept(ContentType.JSON)
                .log().uri()
                .pathParam("id", 2).
                when()
                .get("/regions/{id}").prettyPeek();


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());


        //verify
        Assertions.assertTrue(response.asString().contains("Americas"));




    }



    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    public void getOneRegionValueSource(int id) {

        Response response = given()
                .accept(ContentType.JSON)
                .log().uri()
                .pathParam("id", id).
                when()
                .get("/regions/{id}").prettyPeek();


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());


        //verify
        Assertions.assertEquals(id, (Integer) response.path("region_id"));
    }


    // numLineToSkip skip the header if it has a value as 2
    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv",numLinesToSkip = 1)
    public void getOneRegionCsvFileSource(int id,String regionName) {

        Response response = given()
                .accept(ContentType.JSON)
                .log().uri()
                .pathParam("id", id).
                when()
                .get("/regions/{id}").prettyPeek();


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());


        //verify
        Assertions.assertEquals(id, (Integer) response.path("region_id"));
        Assertions.assertEquals(regionName, response.path("region_name").toString());

        
    }












}

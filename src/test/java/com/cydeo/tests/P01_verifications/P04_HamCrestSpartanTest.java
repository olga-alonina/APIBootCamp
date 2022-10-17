package com.cydeo.tests.P01_verifications;

import com.cydeo.utility.SpartanTestbase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class P04_HamCrestSpartanTest extends SpartanTestbase {
    /**
     * - Send a request to GET /spartans/search
     * - Query Parameters values are
     *     - gender —> Female
     *     - nameContains —> ea
     * - Log Everything
     * - Verify followings
     *       - Status Code is 200
     *       - ContentType is application/json
     *       - Total Element 1
     *       - jsonArray size hasSize 1
     *       - All Names hasItem "Jeanelle"
     *       - Every gender is Female
     */

    @Test
    public void searchSpartans() {

        given()
                .queryParam("nameContains", "ea")
                .queryParam("gender","Female")
                .log().uri().
        when()
                .get("/spartans/search")  .prettyPeek().
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("totalElement", is(1))
                .body("content", hasSize(1))
                .body("content.name",hasItem("Jeanelle"))
                .body("content.gender",everyItem(is("Female")))
        ;










    }
}

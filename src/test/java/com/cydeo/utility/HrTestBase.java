package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class HrTestBase {

    @BeforeAll
    public static void init(){
        baseURI="http://54.167.139.116:1000";
        basePath="/ords/hr";
    }

    @AfterAll
    public static void destroy(){
        reset();
    }
}

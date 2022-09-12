package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class NewsAPITestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI=ConfigurationReader.getProperty("news.api.url");
    }
}

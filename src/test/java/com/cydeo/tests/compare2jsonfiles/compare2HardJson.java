package com.cydeo.tests.compare2jsonfiles;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import com.google.common.io.Resources;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class compare2HardJson {
    @Test

    public void Test1() throws IOException {
        URL url = Resources.getResource("FirstFile.json");
        String beforeJsonString = Resources.toString(url, StandardCharsets.UTF_8);
        URL url2 = Resources.getResource("SecondFile.json");
        String afterJsonString = Resources.toString(url2, StandardCharsets.UTF_8);


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode beforeNode = objectMapper.readTree(beforeJsonString);
        JsonNode afterNode = objectMapper.readTree(afterJsonString);
        JsonNode patch = JsonDiff.asJson(beforeNode, afterNode);

        String diffs = patch.toPrettyString();
        System.out.println("diffs = " + diffs);
    }
}

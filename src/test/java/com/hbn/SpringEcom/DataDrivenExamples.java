package com.hbn.SpringEcom;

import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DataDrivenExamples {

    @DataProvider(name = "DataPost")
    public Object[][] dataForPost(){
//        Object[][] data = new Object[2][2];
//        data[0][0] ="Albert";
//        data[0][1] = "Scientist";
//        data[1][0] ="Edison";
//        data[1][1] = "Scientist";
//        return data;

//        Same as above commented code
        return new Object[][]{
                {"Subash", "Engineer"},
                {"Arun", "DB Admin"},
                {"Sekar", "Developer"}
        };

    }

    @Test(dataProvider = "DataPost")
    public void test_02_post_dynamic(String firstname, String jobDescription) throws JSONException {
        JSONObject request = new JSONObject();
        System.out.println(request);

        request.put("name", firstname);
        request.put("Job", jobDescription);

        baseURI = "https://reqres.in/api";

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toString())
                .when()
                .post("/users/2")
                .then()
                .statusCode(201)
                .log().all();
    }

}

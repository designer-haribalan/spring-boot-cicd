package com.hbn.SpringEcom;

import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Test_02_Post {
    // POST
    @Test
    void test1_post() throws JSONException {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "Haribalan");
//        map.put("Job", "Software Engineer");

        // using JSON Simpler to post the json input
        JSONObject request = new JSONObject();
        System.out.println(request);

        request.put("name", "Haribalan");
        request.put("Job", "Software Engineer");

        given()
        .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);

    }

    @Test
    public void test_2_put() throws JSONException {
        JSONObject request = new JSONObject();
        System.out.println(request);

        request.put("name", "Haribalan");
        request.put("Job", "Software Engineer");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toString())
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void test_02_patch() throws JSONException {
        JSONObject request = new JSONObject();
        System.out.println(request);

        request.put("name", "Haribalan");
        request.put("Job", "Software Engineer");

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toString())
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void test_02_delete() throws JSONException {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)
                .log().all();
    }

    }

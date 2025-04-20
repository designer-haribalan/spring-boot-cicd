package com.hbn.SpringEcom;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;


public class Test_01_Get {

    @Test
    public void test_01(){
        Response response = get("https://reqres.in/api/users?page=2");

        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getBody());
        System.out.println(response.asString());
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        System.out.println(response.getTime());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    // BDD Approach -> Given / When / Then
    @Test
    void test_02(){
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(7));
    }

    // GET
    @Test
    void test_03(){
        given()
                .get("http://localhost:8080/api/products")
                .then()
                .statusCode(200)
//                .body()
                .log().all();
    }


}

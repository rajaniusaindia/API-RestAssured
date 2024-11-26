package com.ra.postapis;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUser {
    @Test(enabled = true, priority = 1)
    public void postCreateUser() {
        System.out.println("================ Request Section ===========");
        // 1. Same steps - define base URI
        RestAssured.baseURI = "https://gorest.co.in";
        //2. construct a request - create local
        RequestSpecification request = RestAssured.given().log().all();
        // 3 we need headers & body
        request.contentType("application/json");
        request.header("Authorization", "Bearer b2aec4781216fd9b1aa97480014e03d54bf028b39f88cc076d562dc0ff0c6282");
        // 4. construct a payload - bring everything in 1 line
        String payload = "\"name\": \"Chandu Champion\",\"email\": \"chandu@test2.com\",\"gender\":\"male\",\"status\": \"inactive\"}";// Note escape char added by IntelliJ
        // 5. passing the payload to the body of a request
        request.body(payload);

        //3. Which type of call - HTTP GET POST
        //Postman - when we send the request - we get response
        // Service url - end point from https://gorest.co.in/public/v2/users - /public/v2/users
        Response response = request.post("/public/v2/users");
        // 3.  Postman - when we send the request - we get response
        // we should see the token
        System.out.println("================ response Body ===========");
        System.out.println("Response body \n " + response.prettyPrint());

        System.out.println("================ verified status code ===========");
        int statusCode = response.statusCode();
        System.out.println("Response body \n " + statusCode);
        // Status code for POST - 201
        Assert.assertEquals(statusCode, 201);

        System.out.println("================ verified status line ===========");
        String statusLine = response.getStatusLine();
        // get this value and then assert
        System.out.println("status line: " + statusLine);
        Assert.assertTrue(statusLine.contains("200 OK"));

        // We cannot asssert on the token but can check if it is not null
        // extract the data from the response body  and assert on the required field
        JsonPath jsonPath = response.jsonPath();
        Object id = jsonPath.get("id");
        System.out.println("extracted id " + id);
        Assert.assertNotNull(id, "id not validated");
        // if there is any failure - then get executed
        System.out.println("User created successfully");


        System.out.println("================ get all headers values===========");
        Headers headers = response.getHeaders();
        System.out.println("Printing all headers");
        System.out.println(headers);
    }

}

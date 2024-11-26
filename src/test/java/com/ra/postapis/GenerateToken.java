package com.ra.postapis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class GenerateToken {

    // Here we are passing the payload as a POJO Object
    @Test (enabled=true, priority=1)
    public void postGenerateTokenPojo() throws JsonProcessingException {
        // 1. Same steps - define base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        //2. construct a request - create local
        RequestSpecification request = RestAssured.given().log().all();
        request.contentType("application/json");

        //3. construct a payload using pojo class
        // first create an Object of Credentials class and pass the values (in ctr to initialize)
        Credentials credentials = new Credentials("admin", "password123");

        //4. create an object of Mapper class to convert the object to payload
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(credentials);

        // rest becomes easy
        // 5. passing the payload to the body of a request
        request.body(payload);

        //6. Which type of call - HTTP GET POST
        //Postman - when we send the request - we get response
        // Service url
        Response response = request.post("/auth");
        // 7.  Postman - when we send the request - we get response
        // we should see the token
        System.out.println("================ response Body ===========");
        System.out.println("Response body \n "+ response.prettyPrint());

    }

    // Here we are passing the payload as a String
    @Test(enabled=false, priority=1)
    public void postGenerateToken(){
        System.out.println("================ Request Section ===========");
        // 1. Same steps - define base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        //2. construct a request - create local
        RequestSpecification request = RestAssured.given().log().all();
        // 3 we need headers & body
        request.contentType("application/json");
        // 4. construct a payload
        String payLoad = "{\"username\" : \"admin\",\"password\" : \"password123\"}";// Note escape char added by IntelliJ
        // 5. passing the payload to the body of a request
        request.body(payLoad);

        //3. Which type of call - HTTP GET POST
        //Postman - when we send the request - we get response
        // Service url
        Response response = request.post("/auth");
        // 3.  Postman - when we send the request - we get response
        // we should see the token
        System.out.println("================ response Body ===========");
        System.out.println("Response body \n "+ response.prettyPrint());

        System.out.println("================ verified status code ===========");
        int statusCode = response.statusCode();
        System.out.println("Response body \n "+ statusCode);
        Assert.assertEquals(statusCode, 200);

        System.out.println("================ verified status line ===========");
        String statusLine=response.getStatusLine();
        // get this value and then assrert
        System.out.println("status line: " + statusLine);
        Assert.assertTrue(statusLine.contains("200 OK"));

        // We cannot asssert on the token but can check if it is not null
        // extract the data from the response body  and assert on the required field
        JsonPath jsonPath = response.jsonPath();
        Object token = jsonPath.get("token");
        System.out.println("extracted token id " + token);
        Assert.assertNotNull(token, "token not validated");
        // if there is any failure - then get executed


       System.out.println("================ get all headers values===========");
        Headers headers = response.getHeaders();
        System.out.println("Printing all headers");
        System.out.println(headers);

    }
}

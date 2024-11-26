package com.ra.postapis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

public class CreateFakerUser {

    @Test(enabled=true, priority=1)
    public void postFakerUser() throws JsonProcessingException {
        System.out.println("================ CREATE FAKER USER ===========");

        Faker faker = new Faker();
        // 1. Same steps - define base URI
        RestAssured.baseURI = "https://reqres.in";
        //2. construct a request - create local
        RequestSpecification request = RestAssured.given().log().all();
        // 3 we need headers & body
        request.contentType("application/json");

        // Instead of job we will use lastname()
        Reqres reqres_data = new Reqres(faker.name().firstName(), faker.name().lastName());

        //4. create an object of Mapper class to convert the object to payload
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(reqres_data);

        // rest becomes easy
        // 5. passing the payload to the body of a request
        request.body(payload);

        //6. Which type of call - HTTP GET POST
        //Postman - when we send the request - we get response
        // Service url
        Response response = request.post("/api/users");
        // 7.  Postman - when we send the request - we get response
        // we should see the token
        System.out.println("================ response Body ===========");
        System.out.println("Response body \n "+ response.prettyPrint());
        // should give us random test data
    }
}

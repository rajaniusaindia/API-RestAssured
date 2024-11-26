package com.ra.postapis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class CreateBooking {

    @Test (enabled= true, priority=1)
    public void testCreateBookingPojo() throws JsonProcessingException {
        System.out.println("================ CREATE BOOKING USING POJO CLASS ===========");
        // 1. Same steps - define base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        //2. construct a request - create local
        RequestSpecification request = RestAssured.given().log().all();
        // 3 we need headers & body
        request.contentType("application/json");
        // 4. construct a payload - pass it from json file
        // Create object of Bookingdate first
        Bookingdates bookingdates = new Bookingdates("2018-01-01", "2019-01-01");
        Booking booking = new Booking("Jim", "Brown", 111, true, bookingdates, "Breakfast");

        // Copy rest from GenerateToken
        //4. create an object of Mapper class to convert the object to payload
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);


        // rest becomes easy
        // 5. passing the payload to the body of a request
        request.body(payload);

        //6. Which type of call - HTTP GET POST
        //Postman - when we send the request - we get response
        // Service url
        Response response = request.post("/booking");
        // 7.  Postman - when we send the request - we get response
        // we should see the token
        System.out.println("================ response Body ===========");
        System.out.println("Response body \n "+ response.prettyPrint());

    }

    @Test (enabled= false, priority=1)
    public void postCreateBooking(){
        System.out.println("================ Request Section ===========");
        // 1. Same steps - define base URI
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        //2. construct a request - create local
        RequestSpecification request = RestAssured.given().log().all();
        // 3 we need headers & body
        request.contentType("application/json");
        // 4. construct a payload - pass it from json file

        // which class in java - File class - copy path reference from root
        File payload = new File("./src/test/resources/payloads/booking.json");
        // 5. passing the payload to the body of a request
        //File payLoad = null;
        request.body(payload);

        //3. Which type of call - HTTP GET POST
        //Postman - when we send the request - we get response
        // Service url
        Response response = request.post("/booking");
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

        System.out.println("================ get all headers values===========");
        Headers headers = response.getHeaders();
        System.out.println("Printing all headers");
        System.out.println(headers);
    }
}

package com.ra.apis;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAPIs {

    @Test (enabled=false, priority=1)
    public void getUsers(){
        // Non-BDD format - Object method
        // BDD - static - use all the methods
        // import static io.restassured.RestAssured.*;
        // Cannot do any modification since Static
        // If buying a raw material - Non-BDD, can customize
        // Readymade - BDD cannot change,  cannot customize

        // 1. Same steps - define base URI
        RestAssured.baseURI = "https://gorest.co.in/";
        //2. construct a request - create local
        RequestSpecification request = RestAssured.given().log().all();
        //3. Which type of call - HTTP GET POST
        //Postman - when we send the request - we get response
        Response response = request.get("/public/v1/users");
        // 3.  Postman - when we send the request - we get response
        System.out.println("================ response Body ===========");
        //System.out.println(response.getBody()); // io.restassured.internal.RestAssuredResponseImpl@35267fd4
        // we want the values
        System.out.println("Response body \n "+ response.getBody().asString());
        //Response body
        // {"meta":{"pagination":{"total":2965,"pages":297,"page":1,"limit":10,"links":{"previous":null,"current":"https://gorest.co.in/public/v1/users?page=1",
        // "next":"https://gorest.co.in/public/v1/users?page=2"}}},"data":[{"id":7352076,"name":"Agrata Pillai","email":"agrata_pillai@smitham.test","gender":"male",
        // "status":"active"},{"id":7352074,"name":"Bankimchandra Johar","email":"johar_bankimchandra@lemke.example","gender":"female","status":"inactive"},
        // {"id":7352069,"name":"Chandraayan Pilla IV","email":"chandraayan_iv_pilla@dare.example","gender":"male","status":"inactive"},{"id":7352066,
        // "name":"Bhadraksh Mukhopadhyay","email":"bhadraksh_mukhopadhyay@legros-reilly.test","gender":"female","status":"active"},{
        // "id":7352065,"name":"Bankimchandra Asan","email":"bankimchandra_asan@hagenes-leuschke.test","gender":"female","status":"active"},
        // {"id":7352061,"name":"Chandraprabha Deshpande","email":"deshpande_chandraprabha@walker-hyatt.example","gender":"female","status":"inactive"},
        // {"id":7352060,"name":"Aadrika Rana","email":"rana_aadrika@casper.test","gender":"female","status":"inactive"},{"id":7352059,"name":"Bhagirathi Mukhopadhyay",
        // "email":"mukhopadhyay_bhagirathi@graham.test","gender":"female","status":"active"},{"id":7352058,"name":"Chetan Pandey",
        // "email":"pandey_chetan@lehner-hintz.example","gender":"female","status":"active"},{"id":7352055,"name":"Brahmdev Marar",
        // "email":"brahmdev_marar@rohan.example","gender":"female","status":"inactive"}]}
        // how to copy the entire string - keep mouse one level below - next line  and mouse over up - will copy the entire string
        // single line

        // Go to jsonlint - validate json
        // or https://jsonschema.net/app/schemas/483184

        // how to get in better format

        System.out.println("Response body \n "+ response.prettyPrint());

        System.out.println("================ status code ===========");
        int statusCode=response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);

        System.out.println("================ status line ===========");
        String statusLine=response.getStatusLine();
        // get this value and then assrert
        System.out.println("status line: " + statusLine);
        //Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

        // or
        Assert.assertTrue(statusLine.contains("200 OK"));


        System.out.println("================ get single header value===========");
        String dateHeader=response.getHeader("Date");
        System.out.println(dateHeader);
        Assert.assertTrue(dateHeader.contains("GMT"));

        System.out.println("================ get all headers values===========");
        Headers dateHeaders = response.getHeaders();
        System.out.println("Printing all headers");
        System.out.println(dateHeaders);
    }

    @Test (enabled=true, priority=1)
    public void getInactiveUsers(){
        System.out.println("================ Request Section ===========");
        // 1. Same steps - define base URI
        RestAssured.baseURI = "https://gorest.co.in/";
        //2. construct a request - create local
        RequestSpecification request = RestAssured.given().log().all();
        request.queryParam("status","inactive");
        //3. Which type of call - HTTP GET POST
        //Postman - when we send the request - we get response
        Response response = request.get("/public/v1/users");
        // 3.  Postman - when we send the request - we get response
        System.out.println("================ response Body ===========");
        //System.out.println("Response body \n "+ response.getBody().asString());
        System.out.println("Response body \n "+ response.prettyPrint());

    }
}

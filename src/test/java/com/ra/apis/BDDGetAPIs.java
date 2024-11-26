package com.ra.apis;

// important to add TestNG import and @Test  else RUn button is diasbled
import org.testng.annotations.Test;
import io.restassured.RestAssured;

// static - use all the methods
import static io.restassured.RestAssured.*;


public class BDDGetAPIs {
    @Test (enabled=false, priority=1)
    public void getAllUsers() {

        // step 1: define the base URI - https://gorest.co.in
        // must be taken from rest.io
        RestAssured.baseURI = "https://gorest.co.in";

        // follow BDD style - Given When and Then
        // Given - pre-condition
        // When - exactly what action we are going to perform
        // Then - result from the action

        // step 2: rite the steps in the bdd format starting with given when and then
        // Like a method chaining
        // USe given() => When() => then()

        // Where to see the details of where it is passing and where it is passing
        // Add log().all()

        given().log().all().contentType("application/json")
                .when().log().all().get("/public/v1/users")
                .then().log().all().statusCode(200)
                .header("Server", "cloudflare")
                .header("Content-Encoding", "gzip");

    }
    @Test (enabled=false, priority=2)
    public void getAllPosts() {

        // step 1: define the base URI - https://gorest.co.in
        // must be taken from rest.io
        RestAssured.baseURI = "https://gorest.co.in";

        // follow BDD style - Given When and Then
        // Given - pre-condition
        // When - exactly what action we are going to perform
        // Then - result from the action

        // step 2: rite the steps in the bdd format starting with given when and then
        // Like a method chaining
        // USe given() => When() => then()

        // Where to see the details of where it is passing and where it is passing
        // Add log().all()

        /*given().log().all().contentType("application/json")
                .when().log().all().get("/public/v1/posts")
                .then().log().all().statusCode(200)
                .header("Server", "cloudflare")
                .header("Content-Encoding", "gzip");*/

        // Limitation with BDD approach  - Cannot do a custom validation, has to come from the pre-defined
        // remove the content-type and see what happens
        given().log().all()
                .when().log().all().get("/public/v1/posts")
                .then().log().all().statusCode(200)
                .header("Server", "cloudflare");
    }

    @Test (enabled=false, priority=3)
    public void getAllComments(){
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .when().log().all().get("/public/v1/comments")
                .then().log().all()
                .statusCode(200)
                .header("Server", "cloudflare")
                .statusLine("HTTP/1.1 200 OK");
    }
    @Test (enabled=false, priority=4)
    public void getAllToDos(){
        // run and verify getting same output as POSTMAN

        // first get the base urlEncodingEnabled
        // After running this code we should get the exact output as in the POSTMAN

        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .when().log().all().get("/public/v1/todos")
                .then().log().all()
                .statusCode(200)
                .header("Server", "cloudflare")
                .statusLine("HTTP/1.1 200 OK");
    }

    @Test (enabled=false, priority=4)
    public void getFemaleUsers(){
        // one way - but not recommended
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .when().log().all().get("/public/v1/users?gender=female")
                .then().log().all()
                .statusCode(200)
                .header("Server", "cloudflare")
                .statusLine("HTTP/1.1 200 OK");
    }

    @Test (enabled=false, priority=4)
    public void getSingleUserByID(){
        // Second way - recommended - through query param
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .queryParam("status","inactive")
                .queryParam("gender", "male")
                .when().log().all().get("/public/v1/users")
                .then().log().all()
                .statusCode(200)
                .header("Server", "cloudflare")
                .statusLine("HTTP/1.1 200 OK");



    }

    @Test (enabled=true, priority=4)
    public void getInactiveMaleUsers(){
        // Second way - recommended - through query param
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                //.pathParam("/","7352066") - later
                .when().log().all().get("/public/v1/users/7352066")
                .then().log().all()
                .statusCode(200)
                .header("Server", "cloudflare")
                .statusLine("HTTP/1.1 200 OK");

    }
}


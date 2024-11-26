package com.ra.postapis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateRecordJSONObj {

    /***
     * {
     *     "name": "luis",
     *     "job": "qa-manager",
     *     "skills": [
     *         "core-java",
     *         "selenium",
     *         "restassured"
     *     ],
     *     "details": {
     *         "company": "IBM",
     *         "email": "luis@accenture.com"
     *     }
     * }
     */

    @Test
    public void testCreateRecordsUsingJSONObject(){
        JSONObject js = new JSONObject();
        js.put("name","Rajani");
        js.put("job","qa-engineer");

        // Create first array
        JSONArray skill = new JSONArray();
        skill.put("core-java");
        skill.put("selenium");
        skill.put("restassured");

        js.put("skills",skill);

        // Create object
        JSONObject detail = new JSONObject();
        detail.put("company","IBM");
        detail.put("email", "luis@accenture.com");

        // now we want to put this into the original main map
        js.put("details", detail);

        // Follow BDD approach

        System.out.println(js.toString());
        // Expected = Actual
        // {"skills":["core-java","selenium","restassured"],"name":"Rajani","details":{"company":"IBM","email":"luis@accenture.com"},"job":"qa-engineer"}
        // Validated in JSON-Lint - Valid Response

        //Mistake was that => js.toString()
        // We were getting the HashMap

        Response response = given().auth().none()
                .contentType(ContentType.JSON)
                .when().body(js.toString()).log().all()
                .post("https://reqres.in/api/users");

        System.out.println("=============Response===========");
        System.out.println(response.statusCode());
        System.out.println(response.prettyPrint());

    }
}

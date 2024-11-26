package com.ra.postapis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;

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
public class CreateUserMap {

    @Test
       public void  postCreateUserUsingMap(){
           // Object is super class – can store any data type – String Array, Object any thing
           //Best to have Key = String, Value=Object
           // HashMap changed to LinkedHashMap to maintain the insertion order
           //HashMap<String, Object> map = new HashMap<String, Object>();

           LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
           map.put("name","luis");
           map.put("job","qa-manager");
           // skills => Array with String values => so keep as generic String
           ArrayList<String> skill = new ArrayList<String>();
           skill.add("core-java");
           skill.add("selenium");
           skill.add("restassured");

           // Now add key - skills into the original  and put value => skills
           map.put("skills", skill);

           // details is an Object => { }
           // ArrayList or one more Map => create a new Map for => details
           //HashMap<String, Object> detail = new HashMap<String, Object>();
           LinkedHashMap<String, Object> detail = new LinkedHashMap<String, Object>();
           detail.put("company","IBM");
           detail.put("email", "luis@accenture.com");

           // now we want to put this into the original main map
           map.put("details", detail);

           // Follow BDD approach

           Response response = given().auth().none()
                   .contentType(ContentType.JSON)
                   .when().body(map).log().all()
                   .post("https://reqres.in/api/users");

           System.out.println("=============Response===========");
           System.out.println(response.statusCode());
           System.out.println(response.prettyPrint());

        /***
         * So, Note order of data sent in Request not the same as Response
         * What changes we need to do? Just change to LinkedHashMap
         * Difference between: HashMap, LinkedHashMap, TreeMap
         * Map – no dup by default,
         * HashMap – no order – unordered – does not follow order of insertion
         * LinkedHashMap – follow insertion order
         * TreeMap – follow ascending order
         * See implementation above
         */

        /*** Same insertion order - LinkedHashMap
         * =============Response===========
         * 201
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
         *     },
         *     "id": "839",
         *     "createdAt": "2024-08-29T23:47:23.372Z"
         * }
         */
    }

}


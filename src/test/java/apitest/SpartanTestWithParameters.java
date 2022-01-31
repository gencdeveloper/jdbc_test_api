package apitest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SpartanTestWithParameters {


    @BeforeTest
    public void beforeclass() {
        baseURI = "http://52.207.61.129:8000";
    }
    /*
     * Given accept type is Json
     * And id parameter value is 5
     * When user sends GET request to/api/spartans/{id}
     * Then response status code shuld be 200
     * And response content-type: application/json;charset=UTF-8
     * And "Blythe" should be in response payload
     * */

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Max"));


    }

    @Test
    public void negativeScenario() {
        /*
        Task
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to/api/spartans/{id}
        then response status code should be 404
        And response content-type: application/json;chartset=UTF-8
        aND SPARTAN nOT fOUND MESSAGE SHOULD BE IN response payload
        * */
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5000)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(), 404);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Not Found"));

    }

    @Test
    public void positiveTestWithQueryParam() {
        /*
         * Given accept type is json
         * And query parameter values are:
         * gender|female
         * nameContains|e
         * When user sends GET request to /api/spartans/search
         * Then response status code should be 200
         * And response content-type: application/json;charset=UTF-8
         * And "Female" should be in response payload
         * And "Janette" should be in response payload
         * */

       Response response= given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");


        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Jared Abshire"));

    }

    @Test
    public void positiveTestWithQueryParamWithMaps(){
        //create a map and add querry parameters
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");

        //Response verification
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Jared Abshire"));

    }
}

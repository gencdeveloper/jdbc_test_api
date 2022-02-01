package apiReview.w12_04_21;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class zipTask {

    @BeforeClass
    public void setUp(){
        baseURI= "http://api.zippopotam.us/";
    }

    @Test
    public void zipCode(){
        String zipUrl = "http://api.zippopotam.us/";
        /***
         * Given Accept application/json
         * And path zipcode is 22031 ---api.zippopotam.us/us/22031
         * When I send a GET request to /us endpoint
         * Then status code must be 200
         * And content type must be application/json
         * And Server header is cloudflare
         * And Report-To header exists
         * And body should contains following information
         *     post code is 22031
         *     country  is United States
         *     country abbreviation is US
         *     place name is Fairfax
         *     state is Virginia
         *     latitude is 38.8604
         *
         */

        Response response = given().accept(ContentType.JSON)
                .when().get("us/22031");

        //And Server header is cloudflare
        Assert.assertEquals(response.headers().getValue("Server"),"cloudflare");

        // * And Report-To header exists
        Assert.assertTrue(response.headers().hasHeaderWithName("Report-To"));


        JsonPath jsonPath = response.jsonPath();

        //post code is 22031
        String postCode = jsonPath.getString("\'post-code\'");
        //Assert.assertEquals(postCode,22031);

        //country  is United States
        Assert.assertEquals(jsonPath.getString("country"),"United States");

        //country abbreviation is US
        Assert.assertEquals(jsonPath.getString("\'country abbreviation\'"),"US");

        //palce name is Fairfax
        Assert.assertEquals(jsonPath.getString("places[0].\'place name\'"),"Fairfax");

        //state is Virginia
        Assert.assertEquals(jsonPath.getString("places[0].state"),"Virginia");

        //latittude
        Assert.assertEquals(jsonPath.getString("places[0].latitude"),"38.8604");

    }



}

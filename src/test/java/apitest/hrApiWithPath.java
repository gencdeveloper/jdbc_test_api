package apitest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class hrApiWithPath {

    @BeforeClass
    public void beforeclass() {

        baseURI = ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void getCountriesWithPath(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);


        //print limit value
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore").toString());

        //how to call items for long list
        String firstCountryId = response.path("items.country_id[0]");
        System.out.println("firstCountryId = " + firstCountryId);

        String firstCountryName = response.path("items.country_name[1]");
        System.out.println("firstCountryId = " + firstCountryName);

        //dizin icinden dizin almak istersen
        String firstHref= response.path("items.links[2].href[0]");
        System.out.println("firstCountryId = " + firstHref);

        //if you want to all country name
        List <String> AllCountryName = response.path("items.country_name");
        System.out.println("AllCountryName = " + AllCountryName);

        //assert that all regians ids are equal to 2
        List <Integer> regionIDs = response.path("items.region_id");
        for (int regionID : regionIDs){
            System.out.println(regionID);
            assertEquals(regionID,2);
        }

    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                //karismamasi icin burada filterlama yapiyoruz.yani job_id:IT_PROG diyerek
                .and().queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("IT_PROG"));

        //makes sure we have only IT_PROG as a job_id
        List <String> jobIDs = response.path("items.job_id");
        for (String jobID : jobIDs){
            System.out.println(jobID);
            assertEquals(jobID,"IT_PROG");
        }

    }
}

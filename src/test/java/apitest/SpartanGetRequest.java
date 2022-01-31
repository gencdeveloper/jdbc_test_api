package apitest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

import static io.restassured.RestAssured.*;
public class SpartanGetRequest {

    String spartanUrl = "http://52.207.61.129:8000";

    @Test
    public void test1(){
        Response response = when().get(spartanUrl+"/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();


    }

    @Test
    public void test2(){
        /*
        * When users sends a get request to /api/spartans/3//
        * Then status code should be 200//
        * And content type should be application/json;charset=UFT-8//
        * and json body should contain fidole
        *
        * */

        Response response =when().get(spartanUrl+"/api/spartans/780");

        //verify status code
        Assert.assertEquals(response.getStatusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json");

        //Verify Body Contains fidole
        Assert.assertTrue(response.body().asString().contains("fidole"));

    }

    @Test
    public void helloTest(){
        /**
         * Given no headers provided
         * When users sends GET request to /api/hello
         * Then response status code should be 200
         * And Content type header shoul be "text/plain;chartset=UTF-8"
         * And header should contain date
         * And content-length should be 17
         * And body shoul be "Hello from Sparta"
         * */

        //When users sends GET request to /api/hello
        Response response = when().get(spartanUrl+"/api/hello");

        //Then response status code should be 200
        Assert.assertEquals(response.getStatusCode(),200);

        //And Content type header shoul be "text/plain;chartset=UTF-8"
        Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");

        //Verify we have headers named date
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));

        //header should contain date
        response.header("Date");

        //* And content-length should be 17
        System.out.println(response.header("Content-Length"));
        String contentLengthVerify = response.header("Content-Length");
        Assert.assertEquals(contentLengthVerify,response.header("Content-Length"));

        // * And body shoul be "Hello from Sparta"

        Assert.assertTrue(response.body().asString().contains("Hello from Sparta"));

    }

}

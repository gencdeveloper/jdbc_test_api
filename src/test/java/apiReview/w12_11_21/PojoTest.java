package apiReview.w12_11_21;

import Day6_POJO.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PojoTest {

    String spartanUrl = "http://52.206.11.157:8000";
    String zipUrl = "http://api.zippopotam.us/";

    @Test
    public void spartanWithPojo(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",7)
                .when().get(spartanUrl+"/api/spartans/{id}");

       assertEquals(response.statusCode(),200);

       Spartan spartan7 = response.body().as(Spartan.class); //de-serialization
       // as() method to map json object to Spartan Object

        System.out.println("spartan7.getName() = " + spartan7.getName());

        assertEquals(spartan7.getName(),"Hershel");
    }

    @Test
    public void zipWithPojo(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipCode", 45414)
                .when().get(zipUrl+"US/{zipCode}");

        assertEquals(response.statusCode(),200);

        System.out.println("response.body().asString() = " + response.body().asString());

        PostalCode pc45414 = response.body().as(PostalCode.class);

        System.out.println("pc45414.getCountry() = " + pc45414.getCountry());

        System.out.println("pc45414.getPlaces().get(0).getPlaceName() = " + pc45414.getPlaces().get(0).getPlaceName());

    }






}

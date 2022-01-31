package apitest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class SpartanTestWithJsonPath {
    @BeforeClass
    public void beforeclass() {

        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",19)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //verify id and name withPath()
        int id = response.path("id");
        String name = response.path("name");

        assertEquals(id,19);
        assertEquals(name,"Max");

        //assign response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        int idJson = jsonPath.getInt("id");
        String nameJson = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


        //verifications
        assertEquals(idJson,19);
        assertEquals(nameJson,"Max");
        assertEquals(gender,"Male");
        assertEquals(phone,1234567890);
    }
}

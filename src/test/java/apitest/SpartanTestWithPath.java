package apitest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPath {

    @BeforeClass
    public void beforeclass() {

        //baseURI = "http://52.207.61.129:8000";
        baseURI = ConfigurationReader.get("spartan_api_url");
    }

    /**
     * Given accept type is json
     * And path param id is 19
     * When user sends a get request to "api/spartans/{id}"
     * Then status code is 200
     * And content-type is "application/json;charset=UTF-8"
     * And response payload values match the following
     * id is 19,
     * name is "Max",
     * gender is "Male",
     * phone is 1234567890
     */


    @Test
    public void getOneSpartan_path() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 19)
                .when().get("api/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

       //response.prettyPrint();
      // printing each key value in the json body/payload
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());

        System.out.println(response.body().path("gender").toString());
        System.out.println(response.body().path("phone").toString());

     //save json key values
       int id = response.path("id");
       String name = response.path("name");
       String gender = response.path("gender");
       Long phone = response.path("phone");


        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //assert one by one
        assertEquals(id,19);
        assertEquals(name, "Max");
        assertEquals(gender,"Male");


    }

    @Test
    public void getAllSpartanWithPath(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);

        //verify content type
        assertEquals(response.getHeader("Content-Type"),"application/json");

        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);

        //if you want to run lastID number: we are using for last number for element
        int lastId = response.path("id[-1]");
        System.out.println("firstId = " + lastId);

        //print all names of spartans
        //eger ki yukaridaki gibi spesifik bir deger almayip butun isimleri ya da id leri basacaksaniz List method kullanabilirsiniz,.
        List<String> names = response.path("name");
        System.out.println("names = " + names);

        List<Long> phones = response.path("phone");
        for (Long phone : phones) {
            System.out.println(phone);
        }

    }



}


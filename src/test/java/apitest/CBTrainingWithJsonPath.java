package apitest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CBTrainingWithJsonPath {

    @BeforeClass
    public void beforeclass() {

        baseURI = ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1(){
        //10423

        //how to get request
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10423)
                .when().get("/teacher/{id}");

        //Verify status Code
        assertEquals(response.statusCode(),200);

        //assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        //get values from jsonpath
        String firstName = jsonPath.getString("teachers.firstName[0]");
        System.out.println("firstName = " + firstName);

        //get lastName values from jsonpath
        String lastName = jsonPath.getString("teachers.lastName[0]");
        System.out.println("firstName = " + lastName);

        //call with json path
        String firstname2 = jsonPath.getString("teachers.firstName");
        System.out.println("firstname2 = " + firstname2);

        //call with response path == burada fark var hatirlar
        //String firstname3 = response.path("teachers.firtsName");
        //System.out.println("firstname3 = " + firstname3);

        /**
         * BURAYI SADECE KODLARI OKUMAK ICIN YAZIYORUM!!! STUDENTS VERISI BIZ DE YOK!
         *
         * //Get students contanct
         * String phone = jsonpath.getString("students.contact[0]");
         * Github ogrenirken burayi kontrol ettim
         *dsadsadsd
         *
         *
         * */


    }

}

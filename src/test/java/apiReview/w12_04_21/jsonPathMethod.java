package apiReview.w12_04_21;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class jsonPathMethod {

        @BeforeClass
        public void setUp(){
            //Bu sayfadaki her ornekte hr api kullanacaksam buraya tanimla heryerde kullan ugrasma
            baseURI = "http://52.206.11.157:1000/ords/hr"; //dikkat bu benim sahsi apim(52.206.11.157)

        }


        @Test
        public void jsonPathTest(){

            Response response = given().accept(ContentType.JSON) //Given accept type is Json
                    .and().queryParam("q","{\"region_id\":3}")
                    .when().get("/countries"); //yukarida restAssured dan temellenerek baseURI kullandigim icin gerek kalmadi


            Assert.assertEquals(response.statusCode(),200); // Then status code is 200
            Assert.assertEquals(response.contentType(),"application/json"); // content type application/json
            Assert.assertTrue(response.headers().hasHeaderWithName("Date")); //has header "Date"

            JsonPath jsonPath = response.jsonPath();

            //we reach some useful methods
            //And count is 6
            Assert.assertEquals(jsonPath.getInt("count"),6);


            Assert.assertEquals(jsonPath.getBoolean("hasMore"),false);


            List<String> expectedCountries = new ArrayList(Arrays.asList("Australia", "China", "India", "Japan", "Malaysia", "Singapore"));
            Collections.sort(expectedCountries);
            System.out.println("expectedCountries = " + expectedCountries);

            List<String> actualCountries = jsonPath.getList("items.country_name");
            Collections.sort(actualCountries);
            System.out.println("actualCountries = " + actualCountries);
            Assert.assertEquals(actualCountries,expectedCountries);

        }







}

package apiReview.w12_04_21;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class pathMethodTest {

    @BeforeClass
            public void setUp(){
        //Bu sayfadaki her ornekte hr api kullanacaksam buraya tanimla heryerde kullan ugrasma
        baseURI = "http://52.206.11.157:1000/ords/hr"; //dikkat bu benim sahsi apim(52.206.11.157)

    }


    //path method is to analyze to response

    /** 1- First Question!!!!
        - Given accept type is Json
        - Query param value q= region_id 3
        - When users sends request to/ countries
        - Then status code is 200
        - content type application/json
        - has header "Date"
        - And count is 6
        - And hasMore is false
     * */

    @Test
    public void pathMethodCountries1(){ //{{hrUrl}}/countries?q={"region_id": 3}
        Response response = given().accept(ContentType.JSON) //Given accept type is Json
                .and().queryParam("q","{\"region_id\" : 3}")
                .when().get("/countries"); //yukarida restAssured dan temellenerek baseURI kullandigim icin gerek kalmadi


        Assert.assertEquals(response.statusCode(),200); // Then status code is 200
        Assert.assertEquals(response.contentType(),"application/json"); // content type application/json
        Assert.assertTrue(response.headers().hasHeaderWithName("Date")); //has header "Date"

        //And count is 6
        int actualCount = response.path("count");
        Assert.assertEquals(actualCount,6);

        // And hasMore is false
        boolean hasMore = response.path("hasMore");
        Assert.assertEquals(hasMore,false);

        //"country_name" : "India", how can i reach this with Gpath syntax
        String actualCountry = response.path("items[2].country_name");
        System.out.println("actualCountry = " + actualCountry);
        System.out.println(response.path("items[2].links[0].href").toString()); //http://52.206.11.157:1000/ords/hr/countries/IN

        List<Integer> regions = response.path("items.region_id"); //*  - And all regions_id is 3
        for (int region: regions) {
            Assert.assertEquals(region,3);
        }
        System.out.println("regions = " + regions);


        List <String> expectedCountries = new ArrayList(Arrays.asList("Australia", "China", "India", "Japan", "Malaysia", "Singapore"));
        Collections.sort(expectedCountries);
        System.out.println("expectedCountries = " + expectedCountries);

        List<String> actualCountries = response.path("items.country_name");
        Collections.sort(actualCountries);
        System.out.println("actualCountries = " + actualCountries);
        Assert.assertEquals(actualCountries,expectedCountries);

    }







}

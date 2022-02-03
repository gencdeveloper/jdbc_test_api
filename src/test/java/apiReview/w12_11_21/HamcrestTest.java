package apiReview.w12_11_21;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HamcrestTest {
    /**
     * I will use hamcrest Method for this thest
     * {
     *     "id": 7,
     *     "name": "Hershel",
     *     "gender": "Male",
     *     "phone": 5278678322
     * }
     *
     * */
    String spartanUrl = "http://52.206.11.157:8000";
    String zipUrl = "http://api.zippopotam.us/"; // zipUrl + "US/{zipCode},45414

    @Test
    public void oneSpartanTest(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",7)
                .when().get(spartanUrl+ "/api/spartans/{id}")
                .then().assertThat().statusCode(200).and().contentType("application/json")

                .log().all()
                //hamcrest e basliyoruz
                .body("id",equalTo(7),"name",equalTo("Hershel")
                        ,"gender",equalTo("Male")
                        ,"phone",equalTo(5278678322L));

    }

    /**
     *
     * {
     *     "post code": "90210",
     *     "country": "United States",
     *     "country abbreviation": "US",
     *     "places": [
     *         {
     *             "place name": "Beverly Hills",
     *             "longitude": "-118.4065",
     *             "state": "California",
     *             "state abbreviation": "CA",
     *             "latitude": "34.0901"
     *         }
     *     ]
     * }
     * */
    @Test
    public void zipCodeTestWithHamcrest(){
        given().accept(ContentType.JSON)
                .and().pathParam("zipCode",90210)
                .when().get(zipUrl+ "US/{zipCode}")
                .then().assertThat().statusCode(200).and().contentType("application/json")

                .body("country", equalTo("United States"),

                "'country abbreviation'",equalTo("US"),
                        "places.state[0]",equalTo("California"));

    }









}

package apiReview.w12_11_21;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
//
public class CollectionTest {

    String spartanUrl = "http://52.206.11.157:8000";
    String zipUrl = "http://api.zippopotam.us/";

    @Test
    public void oneSpartanWithMap(){
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

            Response response = given().accept(ContentType.JSON)
                    .and().pathParam("id",7)
                    .when().get(spartanUrl+"/api/spartans/{id}");


            assertEquals(response.statusCode(),200);

            Map<String,Object> spartan7 = response.body().as(Map.class);

            System.out.println(spartan7);

            //assertEquals(spartan7.get("id"),7);
            assertEquals(spartan7.get("name"),"Hershel");
            assertEquals(spartan7.get("gender"),"Male");

    }


    /**
     "places": [
     *         {
     *             "place name": "Beverly Hills",
     *             "longitude": "-118.4065",
     *             "state": "California",
     *             "state abbreviation": "CA",
     *             "latitude": "34.0901"
     */

            @Test
            public void zipTestCollection(){

                Response response = given().accept(ContentType.JSON)
                        .and().pathParam("zipCode", 45414)
                        .when().get(zipUrl+"US/{zipCode}");

                assertEquals(response.statusCode(),200);

                //to reach my response body and for assertions
                Map<String,Object> pc45414 = response.body().as(Map.class); //deserialization

                System.out.println(pc45414);

                assertEquals(pc45414.get("country"),"United States");

                //I am getting "places" key from my Map and putting the value in a List<Map>
                // array a sahip olan places i mi adim adim test icin yaptim ARTIK PLACES icindeki tum elemenlere erisiyorum.
                List<Map<String,Object>> placesFor45414 = (List<Map<String, Object>>) pc45414.get("places");

                assertEquals(placesFor45414.get(0).get("state"),"Ohio");
                assertEquals(placesFor45414.get(0).get("state abbreviation"),"OH");

            }
}

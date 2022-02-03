package apiReview.w12_11_21;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

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


}

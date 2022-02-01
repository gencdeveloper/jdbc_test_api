package apiReview.w12_04_21;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class parametersTest {

    //Baglantilari kuruyoruz.
    String hrUrl = "http://52.206.11.157:1000/ords/hr"; //dikkat bu benim sahsi apim(52.206.11.157)
    String zipUrl = "http://api.zippopotam.us/";
    String Spartans = "http://52.206.11.157:8000";

    @Test
    public void pathParamTest(){

        Response response = given().accept(ContentType.JSON)
                        .and().pathParam("id",15)
                        .when().get(Spartans+"/api/spartans/{id}");
        assertEquals(response.statusCode(),200); // burada sonucumuzu assert ettik statustCODE ile
        assertEquals(response.contentType(),"application/json"); //burada da bekledigimiz basligi assert ettik
        assertTrue(response.body().asString().contains("Meta"));
        response.prettyPrint();

    }


    @Test // with query parameter: nameContains : "m" and gender : male
    public void queryParamTest(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender","male")
                .and().queryParam("nameContains","m")
                .when().get(Spartans+"/api/spartans/search");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }


    @Test //get me employees "department_id" : 80
    public void queryParamTest_HR(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"department_id\" : 80}")
                .when().get(hrUrl+"/employees");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
    }

}

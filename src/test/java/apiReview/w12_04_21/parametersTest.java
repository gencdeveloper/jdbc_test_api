package apiReview.w12_04_21;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class parametersTest {

    //Baglantilari kuruyoruz.
    String hrUrl = "http://52.206.11.157:1000/ords/hr"; //dikkat bu benim sahsi apim(52.206.11.157)
    String zipUrl = "http://api.zippopotam.us/";
    String Spartans = "http://52.206.11.157:8000";

    @Test
    public void pathParamTest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                        .and().pathParam("id",15)
                        .when().get(Spartans+"/api/spartans/{id}");
        Assert.assertEquals(response.statusCode(),200); // burada sonucumuzu assert ettik statustCODE ile
        Assert.assertEquals(response.contentType(),"application/json"); //burada da bekledigimiz basligi assert ettik
        Assert.assertTrue(response.body().asString().contains("Meta"));
        response.prettyPrint();

    }
}

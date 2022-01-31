package apiReview.w12_04_21;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RO1_Api_Test {

    //Baglantilari kuruyoruz.
    String hrUrl = "http://52.206.11.157:1000/ords/hr"; //dikkat bu benim sahsi apim(52.206.11.157)
    String zipUrl = "http://api.zippopotam.us/US/";
    String Spartans = "http://52.206.11.157:8000";


    //get me all employees from HR api
    @Test
    public void testOne(){
        //Response body olusturmak icin hazir kodu yaziyoruz.
        //Yukarida emin ol restassured paketlerinin yuklendigine
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrUrl+"/employees");

       response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
    }

}

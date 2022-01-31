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



    @Test //get me all employees from HR api
    public void testOne(){
        //Response body olusturmak icin hazir kodu yaziyoruz.
        //Yukarida emin ol restassured paketlerinin yuklendigine
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrUrl+"/employees");

       response.prettyPrint(); // response body deki prety kismi
        Assert.assertEquals(response.statusCode(),200); // burada sonucumuzu assert ettik statustCODE ile
        Assert.assertEquals(response.contentType(),"application/json"); //burada da bekledigimiz basligi assert ettik
    }


    @Test //get me all regions from HR api
    public void testTwo(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrUrl+"/regions");

        //response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200); // burada sonucumuzu assert ettik statustCODE ile
        Assert.assertEquals(response.contentType(),"application/json"); //burada da bekledigimiz basligi assert ettik

        //does the response have "Europe" in it?
        Assert.assertTrue(response.body().asString().contains("Europe"));


        //does my response have the header name "Date" in it
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));


        //Transfer-Encoding = chunked / header ile ana headrlerimiza gittik ve chunked degerimizi aldik.
        Assert.assertEquals(response.header("Transfer-Encoding"), "chunked");

    }



}

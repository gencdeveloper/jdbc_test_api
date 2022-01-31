package apiReview.w12_04_21;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RO1_Api_Test {

    //Baglantilari kuruyoruz.
    String hrUrl = "http://52.206.11.157:1000/ords/hr"; //dikkat bu benim sahsi apim(52.206.11.157)
    String zipUrl = "http://api.zippopotam.us/";
    String Spartans = "http://52.206.11.157:8000";



    @Test //get me all employees from HR api
    public void testOne(){
        //Response body olusturmak icin hazir kodu yaziyoruz.
        //Yukarida emin ol restassured paketlerinin yuklendigine
        Response response = given().accept(ContentType.JSON)
                .when().get(hrUrl+"/employees");

       response.prettyPrint(); // response body deki prety kismi
        Assert.assertEquals(response.statusCode(),200); // burada sonucumuzu assert ettik statustCODE ile
        Assert.assertEquals(response.contentType(),"application/json"); //burada da bekledigimiz basligi assert ettik
    }


    @Test //get me all regions from HR api
    public void testTwo(){

        Response response = given().accept(ContentType.JSON)
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


    //Do a request to zip URL and get 45414, verify it contains Dayton, veerify status code and Contenttype
    /**
     * import static io.restassured.RestAssured.*; yukarida restAssured static import ettigim icin
     * Asagida artik tekrardan  RestAssured.given() yazmama gerek kalmadan sadece given() ile baslayabilirim.
     * */
    @Test
    public void testThree(){
        Response response = given().accept(ContentType.JSON) //Static import
                .when().get(zipUrl+"US/45414");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Dayton"));
        response.prettyPrint();
    }

    @Test
    public void fourth(){
        //chaining object yontemi ile yani lazy way ile responsa assigned etmeden de yapacagiz

        given().accept(ContentType.JSON)
                .when().get(zipUrl+"US/45414")
                .then().assertThat().contentType("application/json").and().statusCode(200);
    }

}

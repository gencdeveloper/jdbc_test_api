package apiReview.w12_11_21;

import Day6_POJO.Spartan;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SpartanFlow {

    String spartanUrl = "http://52.206.11.157:8000";
    Response mockSpartan; // I assinged as a global variable

    @BeforeClass
    public void setUp(){


        //I want to send get request to my Mock API to receive a spartan object
        //burayya mackora da insa ettim ve implement ettim.
        mockSpartan = given().accept(ContentType.JSON)

                .header("X-API-Key","8ed3f8e0") // get your athorization
                .when().get("https://my.api.mockaroo.com/eu6Spartans.json"); //end point from mock api

         System.out.println(mockSpartan.body().asString());

    }

    @Test
    public void postSpartan(){

        Spartan mySpartan = new Spartan();
        mySpartan.setName(mockSpartan.path("name"));
        mySpartan.setGender(mockSpartan.path("gender"));
        Long phone = Long.valueOf(mockSpartan.path("phone").toString()); //burasi onemli string i dynamic long yapabilirsin!
        mySpartan.setPhone(phone);

        Response postresponse = given()
                .accept(ContentType.JSON)     // hey API I want to JSON response
                .and()
                .contentType(ContentType.JSON)  // hey API i am sending JSON as a value (***This is needed for Post Method)

                .and().body(mySpartan)         //serializition from JAVA to Json

                .when().post(spartanUrl+"/api/spartans");  //since I provided URI and Path as base parameter at Before Class

      int idFromPost = postresponse.path("data.id");

        System.out.println(idFromPost);

        Assert.assertEquals(postresponse.path("success"),"A Spartan is Born!"); //assertion yani post islemi tanimlandi mi
        //derdimiz onu anlamak!

        //System.out.println(response.body().asString());//bu saaate kadar yaptigimizi bize don

        //yukaridaki cikti verme orneginden farkli olarak ayrica bir de hamcrest matches ile de
        //make a get request to spartan with this created id

        given().log().all().accept(ContentType.JSON)
                .pathParam("id",idFromPost)
                .when().get(spartanUrl+"/api/spartans/{id}")
                .then().statusCode(200);


    }


}

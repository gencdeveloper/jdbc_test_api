package apiReview.w12_11_21;

import Day6_POJO.Spartan;
//import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;


public class CreateSpartan {

    @BeforeClass
    public void setUp(){
        baseURI =  "http://52.206.11.157:8000";
        basePath = "/api/spartans";

    }

    @Test
    public void postSpartan(){

        Spartan mySpartan = new Spartan();
                                            //pojo class sayesinde direkt getir setir yaparak hizlica duzenliyoruz.
        mySpartan.setName("Yunus");
        mySpartan.setGender("Male");
        mySpartan.setGender("Male");
        mySpartan.setPhone(23435432543L);

        Response response = given()
                .accept(ContentType.JSON)     // hey API I want to JSON response
                .and()
                .contentType(ContentType.JSON)  // hey API i am sending JSON as a value (***This is needed for Post Method)

                .and().body(mySpartan)         //serializition from JAVA to Json

                .when().post();  //since I provided URI and Path as base parameter at Before Class

        int idfromPost = response.path("data.id");

        System.out.println("idfromPost = " + idfromPost);

        //System.out.println(response.body().asString());//bu saaate kadar yaptigimizi bize don

        //yukaridaki cikti verme orneginden farkli olarak ayrica bir de hamcrest matches ile de
        //make a get request to spartan with this created id

        given().accept(ContentType.JSON)
                .pathParam("id",idfromPost)
                .when().get("/{id}")
                .then().statusCode(200);


    }


}

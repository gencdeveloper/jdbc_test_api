package apitest;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

    @Test
    public void OneSpartanWithHamcrest(){
      given().accept(ContentType.JSON)
              .and().pathParam("id",19).
      when().get("http://52.207.61.129:8000/api/spartans/{id}")
              .then().statusCode(200)
              .and().assertThat().contentType("application/json")
              .and().assertThat().body("id",equalTo(19),
                      "name",equalTo("Max"),
                      "gender",equalTo("Male"),
                      "phone",equalTo(1234567890));

    }

    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",10785)
                .when().log().all().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                //header diyerek header altindaki basliklari secebiliriz
                .and().header("Content-Length",equalTo("285"))
                .and().header("Connection",equalTo("Keep-Alive"))
                .and().header("Date",notNullValue())
                .and().assertThat().body("teachers.firstName[0]",equalTo("Roscoe"),
                        "teachers.lastName[0]",equalTo("Rolfson"),
                        "teachers.gender[0]",equalTo("Male"))
                .log().all();


    }

    @Test
    public void teachersWithDepartments(){
        given().accept(ContentType.JSON)
                .and().pathParam("name","Computer")
                .when().log().all().get("http://api.cybertektraining.com/teacher/department/{name}")
                .then().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8")).and()
                .body("teachers.firstName",hasItems("Alexander","Marteen"));

    }

}

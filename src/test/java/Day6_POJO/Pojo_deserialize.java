package Day6_POJO;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id",19)
                .when().get("http://52.207.61.129:8000/api/spartans/{id}");

        assertEquals(response.statusCode(),200);


        //JSON to pojo --> de serialize to java custom class
        //json to OUR spartan class object

        Spartan spartan19 = response.body().as(Spartan.class);

        System.out.println("spartan19 = " + spartan19);

        System.out.println("spartan19.getName() = " + spartan19.getName());
        System.out.println("spartan19.getPhone() = " + spartan19.getPhone());

        //assertion
        assertEquals(spartan19.getId(),19);
        assertEquals(spartan19.getName(),"Max");

    }

    @Test
    public void regionToPojo(){
        Response response = when().get("http://52.207.61.129:1000/ords/hr");

        assertEquals(response.statusCode(),200);

        //JSON to POJO(region class)
       Regions regions = response.body().as(Regions.class);
        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());

        List<Item> items = regions.getItems();

        System.out.println(items.get(1).getRegionId());

    }

    @Test
    public void gson_example(){

        Gson gson = new Gson();

        //JSON to JAva collections or Pojo --> De-serialization
        String myJsonData = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Map<String,Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println(map);

        Spartan spartan15 = gson.fromJson(myJsonData,Spartan.class);
        System.out.println(spartan15);

        //-----------SERIALIZATION---------------
        //JAVA Collection or POJO to JSON
        Spartan spartanEU = new Spartan(200,"Mike","Male",123123123);

        String jsonSpartanEU = gson.toJson(spartanEU);
        System.out.println(jsonSpartanEU);
    }


}

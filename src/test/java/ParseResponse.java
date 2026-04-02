import Utils.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import payloads.GoogleDummyAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParseResponse {

    public static void main(String[] args)
    {
        // Add Place -> update place with new Address -> get place to validate place with new address

        RestAssured.baseURI ="https://rahulshettyacademy.com";
        String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(GoogleDummyAPIs.payLoad()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
                .header("server","Apache/2.4.52 (Ubuntu)").body("scope",equalTo("APP")).extract().response().asString();
        System.out.println(response);
        JsonPath js= ReusableMethods.rawToJson(response);
        String placeid=js.getString("place_id");
        System.out.println(placeid);

        //Update Address
        String address="70 Summer walk, USA";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeid+"\",\n" +
                        "\"address\":\""+address+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //Get Place and validate whether the address is updated with new one or not
        String updatedresponse=given().log().all().queryParam("place_id",placeid).queryParam("key","qaclick123")
                .when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js2=ReusableMethods.rawToJson(updatedresponse);
        String updatedaddress=js2.getString("address");
        System.out.println(updatedaddress);
        Assert.assertEquals(updatedaddress,address);

    }
}

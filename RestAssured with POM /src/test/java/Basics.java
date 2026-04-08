import io.restassured.RestAssured;
import payloads.GoogleDummyAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Basics {

    public static void main(String[] args)
    {
        // validate if Add place API is working as expected
        // given-all input details
        // when-submit method of the API (i.e. POST, GET, PUT, DELETE) and resource
        // then-validate the response

        RestAssured.baseURI ="https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(GoogleDummyAPIs.payLoad()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
                .header("server","Apache/2.4.52 (Ubuntu)").body("scope",equalTo("APP"));

    }
}

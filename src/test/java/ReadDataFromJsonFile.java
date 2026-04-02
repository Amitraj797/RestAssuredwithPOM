import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReadDataFromJsonFile {

    @Test
    public void readBookFromJsonFile() throws IOException {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Files.readString(Paths.get("/Users/tanushri/IdeaProjects/APITesting/src/test/resources/AddPlace.json")))
                .when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
                .body("status", equalTo("OK")).body("scope", equalTo("APP"));
    }
}

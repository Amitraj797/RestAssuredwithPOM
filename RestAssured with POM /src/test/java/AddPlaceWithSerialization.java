import POJO.Serialization.AddPlace;
import POJO.Serialization.Location;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceWithSerialization {

    @Test
    public void addPlace()
    {
        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        addPlace.setAccuracy(50);
        addPlace.setAddress("Frontline house");
        addPlace.setLanguage("English");
        addPlace.setWebsite("https://google.com");
        addPlace.setName("Amit Raj");
        addPlace.setPhone_number("(+91) 983 893 3937");
        location.setLat(-38.383494);
        location.setLng(-77.234234);
        addPlace.setLocation(location);
        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(addPlace).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
                .body("status",equalTo("OK"));
    }
}

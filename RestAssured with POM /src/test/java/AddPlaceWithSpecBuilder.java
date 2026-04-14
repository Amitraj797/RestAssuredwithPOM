import POJO.Serialization.AddPlace;
import POJO.Serialization.Location;
import Utils.SpecBuild;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceWithSpecBuilder {

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

       RequestSpecification requestSpecification= given().log().all().spec(SpecBuild.requestSpecBuilder())
                .body(addPlace);

           ResponseSpecification responseSpecification= requestSpecification.when().post("/maps/api/place/add/json").then().log().all().spec(SpecBuild.responseSpecBuilder())
                .body("status",equalTo("OK"));
    }
}

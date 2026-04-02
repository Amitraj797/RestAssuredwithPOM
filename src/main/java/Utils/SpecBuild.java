package Utils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuild {

    public static RequestSpecification requestSpecBuilder() {

        RequestSpecBuilder request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
                .addQueryParam("key","qaclick123")
                .addHeader("Content-Type", "application/json");
        return request.build();
    }
    
    public static ResponseSpecification responseSpecBuilder() {
        ResponseSpecBuilder response = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON);
        return response.build();
    }
}

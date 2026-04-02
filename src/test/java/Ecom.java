import POJO.Ecom.LoginRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Ecom {

    public static void main(String[] args) {
        RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("amit1@xyz.com");
        loginRequest.setUserPassword("Amit@123");
       RequestSpecification request= given().spec(req).body(loginRequest);

    }
}

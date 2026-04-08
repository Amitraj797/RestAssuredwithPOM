import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class JiraAddIssueAndAttachment
{
    String token="dGFudXNocmlnb3JhaTc5N0BnbWFpbC5jb206QVRBVFQzeEZmR0YwZ0xvNy1PbVFMSjRfZkNHS01wdHItbmZsS1lHSllxazJTZUVtdHd4OEhVRDV5dnJpZkJNSlFGeTNqanoyZHhISWdhb3VFUjVFcllaVFV2bmp6bDF4YkU3MWEzREZ0akxhbXJaSlR6aEw0V2tyX2tpLTJIUmJmV1pHVEdiTUFvSEpjbEZ6SHE1eTM5NFJncVZxd1NIMkZZamU0ek05N2FuS290NmdHbHYtdzcwPTEzRUYwNzE4";
    String id;
    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://tanushrigorai797.atlassian.net";
    }

    @Test(priority = 1)
    public void addIssue() throws IOException {
        Response response=given().header("Accept","application/json").header("Content-Type","application/json").header("Authorization","Basic "+token)
                .body(Files.readString(Paths.get("/Users/tanushri/IdeaProjects/APITesting/src/test/resources/AddIssue.json"))).when()
                .post("/rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().response();
        JsonPath jsonPath = response.jsonPath();
        id=jsonPath.getString("id");
    }

    @Test(priority = 2)
    public void addAttachment() throws IOException
    {
        given().header("Accept","application/json").header("X-Atlassian-Token","no-check").header("Authorization","Basic "+token)
                .pathParams("key",id).when()
                .multiPart("file", new File("/Users/tanushri/IdeaProjects/APITesting/src/test/resources/images.jpeg"))
                .when().post("rest/api/3/issue/{key}/attachments'").then().log().all().assertThat().statusCode(200).body("filename", hasItem("images.jpeg"));
    }

}

import POJO.Deserialization.GetCourses;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthWithDeserialization {

    String token;
    String[] courseTitles={"Selenium Webdriver Java","Cypress","Protractor"};
    @Test
    public void getAuthorizeToken()
    {
        Response response=given().log().all().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust").when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                .then().log().all().assertThat().statusCode(200).extract().response();
        JsonPath jsonPath=response.jsonPath();
        token=jsonPath.getString("access_token");
        System.out.println(token);

        GetCourses courseDetails=given().log().all().queryParam("access_token",token).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .then().log().all().extract().as(GetCourses.class);

        System.out.println(courseDetails.getInstructor());
        System.out.println(courseDetails.getExpertise());
        System.out.println(courseDetails.getUrl());

        // Getting the price of SoapUI Webservices testing course dynamically
        int count=courseDetails.getCourses().getApi().size();

        for(int i=0;i<count;i++)
        {
            if(courseDetails.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println(courseDetails.getCourses().getApi().get(i).getPrice());
            }
        }

        //Get all courses name under WebAutomation
        ArrayList<String> actualCoures=new ArrayList<String>();
        int courseCount=courseDetails.getCourses().getWebAutomation().size();

        for(int i=0;i<courseCount;i++)
        {
            actualCoures.add(courseDetails.getCourses().getWebAutomation().get(i).getCourseTitle());
        }

        List<String> expectedCourse= Arrays.asList(courseTitles);

        Assert.assertEquals(expectedCourse, actualCoures);

    }


}

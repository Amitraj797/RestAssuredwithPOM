import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import payloads.MockResponse;

public class ParseComplexJsonMockAPI {

    public static void main(String[] args)
    {
        /*
        1. Print No of courses returned by API
        2.Print Purchase Amount
        3. Print Title of the first course
        4. Print All course titles and their respective Prices
        5. Print no of copies sold by RPA Course
        6. Verify if Sum of all Course prices matches with Purchase Amount
         */

        JsonPath js=new JsonPath(MockResponse.coursePrice());
        // 1. Print No of courses returned by API
        int totalNoOfCourses=js.getInt("courses.size()");
        System.out.println("Total number of courses: "+totalNoOfCourses);
        // 2.Print Purchase Amount
        int purchaseAmount=js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase amount: "+purchaseAmount);
        // 3. Print Title of the first course
        String firstCourseName=js.getString("courses[0].title");
        System.out.println("First course name: "+firstCourseName);
        // 4. Print All course titles and their respective Prices
        for(int i=0; i<totalNoOfCourses;i++)
        {
            String courseName=js.getString("courses["+i+"].title");
            int coursePrice=js.getInt("courses["+i+"].price");
            System.out.println("Course name: "+courseName +" & Price of course: "+coursePrice);
        }
        // 5. Print no of copies sold by RPA Course
        for (int i=0; i<totalNoOfCourses;i++)
        {
            if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA"))
            {
                System.out.println(js.getInt("courses["+i+"].copies"));
                break;
            }
        }
        // 6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum=0;
        for(int i=0; i<totalNoOfCourses;i++)
        {
            int costOfEachCourse=js.getInt("courses["+i+"].price");
            int noOfCopiesSold=js.getInt("courses["+i+"].copies");
            sum+=costOfEachCourse*noOfCopiesSold;
        }
        Assert.assertEquals(sum,purchaseAmount);
    }
}

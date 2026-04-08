import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.LibraryAPIs;

import static io.restassured.RestAssured.given;

public class DynamicJsonWithDataProvider {

    @Test(dataProvider ="BookDetails",dataProviderClass = LibraryAPIs.class)
    public void addBook(String bookName, String isbn, String aisle, String author)
    {
        RestAssured.baseURI="http://216.10.245.166";
        Response response=given().log().all().header("Content-Type","application/json")
                .body(LibraryAPIs.addBook(bookName,isbn,aisle,author))
                .when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract()
                .response();
        JsonPath jsonPath = response.jsonPath();
        String bookId= jsonPath.getString("ID");
        System.out.println("Book ID: "+bookId);
    }
}

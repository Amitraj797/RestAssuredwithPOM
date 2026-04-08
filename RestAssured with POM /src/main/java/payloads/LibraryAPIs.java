package payloads;

import org.testng.annotations.DataProvider;

public class LibraryAPIs {

    public static String addBook(String bookName, String isbn, String aisle, String author)
    {
        String payload="{\n" +
                "\n" +
                "\"name\":\""+bookName+"\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\""+author+"\"\n" +
                "}";
        return payload;
    }

    @DataProvider(name="BookDetails")
    public Object[][] getBookDetails()
    {
        return new Object[][] {{"Appium","4535","iso4004","Ankit Mathur"},
                               {"Java","4093","iso56456","Naveen Karmarkar"},
                               {"Playwright","09485","is0459","Jignesh"},
                               {"SQL","05805","iso4535","Nivesh"},
                               {"Software Testing","04589","iso3058","Amit Raj"}};
    }
}

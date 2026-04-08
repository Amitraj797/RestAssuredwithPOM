package payloads;

public class MockResponse {

    public static String coursePrice()
    {
        return """
                {
                
                "dashboard": {
                
                "purchaseAmount": 910,
                
                "website": "rahulshettyacademy.com"
                
                },
                
                "courses": [
                
                {
                
                "title": "Selenium Python",
                
                "price": 50,
                
                "copies": 6
                
                },
                
                {
                
                "title": "Cypress",
                
                "price": 40,
                
                "copies": 4
                
                },
                
                {
                
                "title": "RPA",
                
                "price": 45,
                
                "copies": 10
                
                }
                
                ]
                
                }
                
                """;
    }
}

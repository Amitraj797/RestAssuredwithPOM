package Utils;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {

    public static JsonPath rawToJson(String response)
    {
        JsonPath jsonconverson=new JsonPath(response);
        return jsonconverson;
    }
}

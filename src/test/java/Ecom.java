import POJO.Ecom.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ecom {

    public static void main(String[] args) {

        //Login to Application
        RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("amit1@xyz.com");
        loginRequest.setUserPassword("Amit@123");
       RequestSpecification loginrequest= given().spec(req).body(loginRequest);

       LoginResponse loginResponse = loginrequest.when().log().all().post("api/ecom/auth/login").then().log().all().assertThat().statusCode(200)
               .body("message", equalTo("Login Successfully")).extract().response().as(LoginResponse.class);

        System.out.println(loginResponse.getMessage());
        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getUserId());

       // Add a product
        RequestSpecification addProductRequest= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",loginResponse.getToken()).build();
        AddProductResponse addproduct= given().log().all().spec(addProductRequest)
                .param("productName","laptop").param("productAddedBy",loginResponse.getUserId())
                .param("productCategory","fashion").param("productSubCategory","shirts")
                .param("productPrice","23000").param("productDescription","Apple Mac M2 Chip")
                .param("productFor","women").multiPart("productImage", new File("src/test/resources/images.jpeg"))
                .when().post("api/ecom/product/add-product").then().log().all().assertThat().statusCode(201)
                .extract().response().as(AddProductResponse.class);

        System.out.println(addproduct.getMessage());
        System.out.println(addproduct.getProductId());

       //Create an order
        CreateOrder createOrderList = new CreateOrder();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setProductOrderedId(addproduct.getProductId());
        orderDetails.setCountry("India");

        List<OrderDetails> orderDetail= new ArrayList<OrderDetails>();
        orderDetail.add(orderDetails);
        createOrderList.setOrders(orderDetail);

        RequestSpecification createOrderBaseRequest= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",loginResponse.getToken()).setContentType(ContentType.JSON).build();

        OrderCreatedDetails createdOrderDetails = given().log().all().spec(createOrderBaseRequest).body(createOrderList).when()
                .post("api/ecom/order/create-order").then().log().all().assertThat().statusCode(201)
                .extract().response().as(OrderCreatedDetails.class);
        System.out.println(createdOrderDetails.getMessage());
        System.out.println(createdOrderDetails.getProductOrderId());
        System.out.println(createdOrderDetails.getOrders());

        //View and Verify Order Details
        String orderId = createdOrderDetails.getOrders().get(0);

        RequestSpecification viewOrder= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",loginResponse.getToken()).build();
       ViewOrderResponse viewOrderResponse= given().log().all().spec(viewOrder).queryParam("id",orderId)
               .when().get("api/ecom/order/get-orders-details").then().log().all().assertThat().statusCode(200).extract().response().as(ViewOrderResponse.class);
        OrderData orderData = viewOrderResponse.getData();
       System.out.println(orderData.getOrderBy());
        System.out.println(createdOrderDetails.getOrders());
        System.out.println("orderId: " + orderId);
        System.out.println("actualId: " + orderData.get_Id());
       Assert.assertEquals(orderId,orderData.get_Id());


        // Delete Product After order placed
        RequestSpecification deleteProductBaseRequest= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",loginResponse.getToken()).build();
        Response deleteProductResponse= given().log().all().spec(deleteProductBaseRequest).pathParam("productId",addproduct.getProductId())
                .when().delete("api/ecom/product/delete-product/{productId}").then().log().all().assertThat().statusCode(200)
                .assertThat().body("message",equalTo("Product Deleted Successfully")).extract().response();
        System.out.println(deleteProductResponse.getBody().asString());
    }

}

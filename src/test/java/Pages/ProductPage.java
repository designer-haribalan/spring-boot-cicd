package Pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.io.File;
import static io.restassured.RestAssured.given;

public class ProductPage {
    private static final String BASE_URI = "http://localhost:8080/api";
    private RequestSpecification request;
    @Getter
    private Response response;

    public ProductPage() {
        RestAssured.baseURI = BASE_URI;
    }

    // GET Request
    public void sendGetRequest(String endpoint) {
        response = given().when().get(BASE_URI + endpoint);
    }

    // Create Product (POST)
    public void createProduct(String name, String description, String brand, int price, String category) {
        request = given()
                .contentType("multipart/form-data")
                .multiPart("product",
                        "{ \"name\":\"" + name + "\", \"description\":\"" + description + "\", \"brand\":\"" + brand +
                                "\", \"price\":" + price + ", \"category\":\"" + category + "\" }", "application/json");
    }

    public void attachImage(String imagePath) {
        request.multiPart("imageFile", new File(imagePath));
    }

    public void sendPostRequest(String endpoint) {
        response = request.when().post(BASE_URI + endpoint);
    }

    // Update Product (PUT)
    public void updateProduct(int productId, String newName, String imagePath) {
        request = given()
                .contentType("multipart/form-data")
                .multiPart("product", "{ \"id\": " + productId + ", \"name\": \"" + newName + "\" }", "application/json");

        if (imagePath != null) {
            request.multiPart("imageFile", new File(imagePath));
        }
    }

    public void sendPutRequest(int productId) {
        response = request.when().put(BASE_URI + "/products/" + productId);
    }

    // DELETE Request
    public void sendDeleteRequest(int productId) {
        response = given().when().delete(BASE_URI + "/products/" + productId);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    // Search Products
    public void searchProducts(String keyword) {
        System.out.println("Searching with: " + keyword);  // Debugging line
        response = given().when().get(BASE_URI + "/products/search?keyword=" + keyword);
    }

}


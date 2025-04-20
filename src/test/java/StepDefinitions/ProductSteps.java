package StepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.io.File;

import static io.restassured.RestAssured.given;

public class ProductSteps {
    private Response response;
    private RequestSpecification request;
    private final String baseUri = "http://localhost:8080/api";
    private int productId;

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = given().when().get(baseUri + endpoint);
    }

    @When("the request is processed")
    public void the_request_is_processed() {
        Assert.assertNotNull(response);
    }

    @Then("I should receive a list of all products with status code {int}")
    public void i_should_receive_a_list_of_all_products_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("I should receive the product details with status code {int}")
    public void i_should_receive_the_product_details_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Given("I have a new product with name {string}, description {string}, brand {string}, price {int}, category {string}")
    public void i_have_a_new_product_with_name_description_brand_price_category(String name, String description, String brand, Integer price, String category) {
        request = given()
                .contentType("multipart/form-data")
                .multiPart("product", "{\"name\":\"" + name + "\", \"description\":\"" + description + "\", \"brand\":\"" + brand + "\", \"price\":" + price + ", \"category\":\"" + category + "\"}", "application/json");
    }

    @Given("I attach an image {string}")
    public void i_attach_an_image(String imagePath) {
        request.multiPart("imageFile", new File(imagePath));
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String endpoint) {
        response = request.when().post(baseUri + endpoint);
    }

    @Then("the product should be added successfully with status code {int}")
    public void the_product_should_be_added_successfully_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Given("I have an existing product with ID {int}")
    public void i_have_an_existing_product_with_id(Integer id) {
        this.productId = id;
    }

    @Given("I update its name to {string}")
    public void i_update_its_name_to(String newName) {
        RestAssured.baseURI = baseUri;
        request = given()
                .multiPart("product", "{\"id\":" + productId + ", \"name\":\"" + newName + "\"}", "application/json");
    }

    @Given("I have an image file {string}")
    public void i_have_an_image_file(String fileName) {
        File imageFile = new File("src/test/resources/Images/" + fileName);
        if (!imageFile.exists()) {
            throw new RuntimeException("Test image file not found: " + fileName);
        }
        request.multiPart("imageFile", imageFile);
    }

//    @When("I send a PUT request to {string}")
//    public void i_send_a_put_request_to(String endpoint) {
//        response = request.when().put(baseUri + "/" + productId);
//    }

    @When("I send a PUT request to {string}")
    public void i_send_a_put_request_to(String endpoint) {
        File imageFile = new File("src/test/resources/Images/test-image.jpg");

        response = RestAssured.given()
                .baseUri(baseUri)
                .contentType("multipart/form-data")  // Ensuring multipart request
                .multiPart("product", "{ \"id\": " + productId + ", \"name\": \"Updated Laptop\", \"price\": 120.00 }", "application/json") // Product as JSON
                .multiPart("imageFile", imageFile) // Attaching image
                .when()
                .put(baseUri + "/products/" + productId); // Correct URL format
    }


    @Then("the product should be updated successfully with status code {int}")
    public void the_product_should_be_updated_successfully_with_status_code(Integer statusCode) {
        response.then().log().all(); // Log response for debugging
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Given("I have a product with ID {int}")
    public void i_have_a_product_with_id(Integer id) {
        this.productId = id;
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        response = given().when().delete(baseUri + endpoint.replace("{id}", String.valueOf(productId)));
    }

    @Then("the product should be deleted successfully with status code {int}")
    public void the_product_should_be_deleted_successfully_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("I should receive a list of matching products with status code {int}")
    public void i_should_receive_a_list_of_matching_products_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }
}

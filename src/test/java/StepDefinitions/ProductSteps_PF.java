package StepDefinitions;

import Pages.ProductPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class ProductSteps_PF {
    private final ProductPage productPage = new ProductPage();
    private int productId;

//    @Given("I send a GET request to {string}")
//    public void i_send_a_get_request_to(String endpoint) {
//        productPage.sendGetRequest(endpoint);
//    }

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        if (endpoint.contains("search")) {
            productPage.searchProducts("Laptop");
        } else {
            productPage.sendGetRequest(endpoint);
        }
    }

    @When("the request is processed")
    public void the_request_is_processed() {
        Assert.assertNotNull(productPage.getResponse());
    }

    @Then("I should receive a list of all products with status code {int}")
    public void i_should_receive_a_list_of_all_products_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), productPage.getStatusCode());
    }

    @Then("I should receive the product details with status code {int}")
    public void i_should_receive_the_product_details_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), productPage.getStatusCode());
    }

    @Given("I have a new product with name {string}, description {string}, brand {string}, price {int}, category {string}")
    public void i_have_a_new_product_with_name_description_brand_price_category(String name, String description, String brand, Integer price, String category) {
        productPage.createProduct(name, description, brand, price, category);
    }

    @Given("I attach an image {string}")
    public void i_attach_an_image(String imagePath) {
        productPage.attachImage(imagePath);
    }

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String endpoint) {
        productPage.sendPostRequest(endpoint);
    }

    @Then("the product should be added successfully with status code {int}")
    public void the_product_should_be_added_successfully_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), productPage.getStatusCode());
    }

    @Given("I have an existing product with ID {int}")
    public void i_have_an_existing_product_with_id(Integer id) {
        this.productId = id;
    }

    @Given("I update its name to {string}")
    public void i_update_its_name_to(String newName) {
        productPage.updateProduct(productId, newName, null);
    }

    @Given("I have an image file {string}")
    public void i_have_an_image_file(String fileName) {
        productPage.updateProduct(productId, "Updated Product", "src/test/resources/Images/" + fileName);
    }

    @When("I send a PUT request to {string}")
    public void i_send_a_put_request_to(String endpoint) {
        productPage.sendPutRequest(productId);
    }

    @Then("the product should be updated successfully with status code {int}")
    public void the_product_should_be_updated_successfully_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), productPage.getStatusCode());
    }

    @Given("I have a product with ID {int}")
    public void i_have_a_product_with_id(Integer id) {
        this.productId = id;
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        productPage.sendDeleteRequest(productId);
    }

    @Then("the product should be deleted successfully with status code {int}")
    public void the_product_should_be_deleted_successfully_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), productPage.getStatusCode());
    }

    @Then("I should receive a list of matching products with status code {int}")
    public void i_should_receive_a_list_of_matching_products_with_status_code(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), productPage.getStatusCode());
    }

}

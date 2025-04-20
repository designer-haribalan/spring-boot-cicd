Feature: Product Management in E-Commerce

  Scenario: Retrieve all products
    Given I send a GET request to "/products"
    When the request is processed
    Then I should receive a list of all products with status code 200

  Scenario: Retrieve a product by ID
    Given I send a GET request to "/products/1"
    When the request is processed
    Then I should receive the product details with status code 200

  Scenario: Add a new product
    Given I have a new product with name "Laptop", description "Gaming Laptop", brand "Lenovo", price 1200, category "Electronics"
    And I attach an image "src/test/resources/Images/laptop.jpg"
    When I send a POST request to "/products"
    Then the product should be added successfully with status code 200

  Scenario: Update an existing product
    Given I have an existing product with ID 10
    And I update its name to "Updated Laptop"
    And I have an image file "test-image.jpg"
    When I send a PUT request to "/products/{id}"
    Then the product should be updated successfully with status code 200


  Scenario: Delete a product
    Given I have a product with ID 1
    When I send a DELETE request to "/products/1"
    Then the product should be deleted successfully with status code 200

  Scenario: Search for products by keyword
    Given I send a GET request to "/products/search?keyword=Laptop"
    When the request is processed
    Then I should receive a list of matching products with status code 200

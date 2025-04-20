package com.hbn.SpringEcom.controller;

import com.hbn.SpringEcom.model.Product;
import com.hbn.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> productGetById(@PathVariable int id){

        Product product = productService.getProductById(id);
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/{id}/image")
    public ResponseEntity<byte[]> getImageProductId(@PathVariable int id){
        Product product = productService.getProductById(id);

        if(id > 0){
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addproducts(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product saveProduct = null;
        try {
            saveProduct = productService.addorUpdateProduct(product, imageFile);
            return new ResponseEntity<>(saveProduct, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // This is for update product
    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile ){
        Product updatedProduct = null;

        try {
            updatedProduct = productService.addorUpdateProduct(product, imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        Product product = productService.getProductById(id);

        if(product != null){
            productService.deleteById(id);
            return new ResponseEntity<>("Deleted!! ", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //search feature
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("searching with :" + keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}

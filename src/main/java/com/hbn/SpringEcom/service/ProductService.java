package com.hbn.SpringEcom.service;

import com.hbn.SpringEcom.model.Product;
import com.hbn.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }

    public Product getProductById(int id){
        return productRepo.findById(id).orElse(new Product(-1));
    }

    public Product addorUpdateProduct(Product product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());


        return productRepo.save(product);
    }

    public void deleteById(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}

package com.linus.ecom_proj.service;

import com.linus.ecom_proj.model.Product;
import com.linus.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProduct(){
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public byte[] getImageById(int id) {
        return repo.findById(id).get().getImageData();
    }

    public Product updateProduct(int id, Product product, MultipartFile image) throws IOException {
        product.setImageData(image.getBytes());
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
}

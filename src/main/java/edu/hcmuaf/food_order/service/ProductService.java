package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.model.Product;
import edu.hcmuaf.food_order.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepo;


    public List<Product> finAll() {
        return productRepo.findAll();
    }


    public Product save(Product product) {
        return productRepo.save(product);
    }


    public void delete(int id) {
        productRepo.deleteById(id);
    }


    public Product findById(int id) {
        return productRepo.findById(id).get();
    }
}

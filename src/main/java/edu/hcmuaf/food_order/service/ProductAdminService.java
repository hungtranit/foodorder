package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.model.ProductAdmin;
import edu.hcmuaf.food_order.repository.ProductAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductAdminService {

    private final ProductAdminRepository productRepository;

    @Autowired
    public ProductAdminService(ProductAdminRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductAdmin> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductAdmin> findById(Long id) {
        return productRepository.findById(id);
    }

}

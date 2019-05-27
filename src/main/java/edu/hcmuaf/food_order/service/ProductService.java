package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.dao.ProductDAO;
import edu.hcmuaf.food_order.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public Product insertProduct(Product product) {
        return productDAO.insertProduct(product);
    }

}

package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.dao.ProductDAO;
import edu.hcmuaf.food_order.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements SearchService{

    @Autowired
    ProductDAO productDAO;

    public Product insertProduct(Product product) {
        return productDAO.insertProduct(product);
    }





    @Override
    public List<Product> searchProuct(String searchText, int pageNo, int resultPerPage) {
        List<Product> productList = productDAO.searchProduct(searchText,pageNo,resultPerPage);
        return productList;
    }

    @Override
    public int searchProductPageCount(String searchText, int resultPerPage) {
        long productCount = searchProductResultCount(searchText);
        int pageCount = (int) ((productCount/resultPerPage)+1);

        return pageCount;
    }

    @Override
    public int searchProductResultCount(String searchText) {
        int userCount = productDAO.searchProductTotalCount(searchText);
        return userCount;
    }
}

package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.model.Product;

import java.util.List;

public interface SearchService {

    List<Product> searchProuct(String searchText, int pageNo, int resultPerPage);

    int searchProductPageCount(String searchText, int  resultPerPage);

    int searchProductResultCount(String searchText);

}

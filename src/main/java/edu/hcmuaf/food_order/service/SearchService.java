package edu.hcmuaf.food_order.service;

import edu.hcmuaf.food_order.model.Product;

import java.util.List;

public interface SearchService {

    public List<Product> searchProuct(String searchText, int pageNo, int resultPerPage);

    public int searchProductPageCount(String searchText, int  resultPerPage);

    public int searchProductResultCount(String searchText);

}

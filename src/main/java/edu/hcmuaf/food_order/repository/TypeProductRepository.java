package edu.hcmuaf.food_order.repository;

import edu.hcmuaf.food_order.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProductRepository extends JpaRepository<TypeProduct, String> {

}

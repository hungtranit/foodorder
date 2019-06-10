package edu.hcmuaf.food_order.repository;

import edu.hcmuaf.food_order.model.ProductAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductAdminRepository extends JpaRepository<ProductAdmin, Long> {

    @Override
    Optional<ProductAdmin> findById(Long id);
}

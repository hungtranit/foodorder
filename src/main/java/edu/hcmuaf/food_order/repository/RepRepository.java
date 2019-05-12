package edu.hcmuaf.food_order.repository;

import edu.hcmuaf.food_order.model.Rep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepRepository extends JpaRepository<Rep, Integer> {

    List<Rep> findAllByCmtid(int cmtID);

}

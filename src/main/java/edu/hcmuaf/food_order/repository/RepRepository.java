package edu.hcmuaf.food_order.repository;

import edu.hcmuaf.food_order.model.Rep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepRepository extends JpaRepository<Rep, Integer> {

    @Query("SELECT coalesce(max(e.id), 0) FROM Rep e")
    int getMaxId();

}

package edu.hcmuaf.food_order.repository;

import edu.hcmuaf.food_order.model.InfoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<InfoUser, String> {

}

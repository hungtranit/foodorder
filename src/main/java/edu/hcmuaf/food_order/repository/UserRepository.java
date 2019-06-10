package edu.hcmuaf.food_order.repository;

import edu.hcmuaf.food_order.model.InfoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<InfoUser, String> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query("update InfoUser u set u.passworduser = ?1 where u.username = ?2")
    void updatePassword(String password, String username);



}

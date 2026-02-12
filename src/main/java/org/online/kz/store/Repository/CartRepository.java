package org.online.kz.store.Repository;

import org.online.kz.store.model.Cart;
import org.online.kz.store.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart getAllByType(String type);

    @Query("SELECT c FROM Cart c")
    List<Cart> getAllCarts();


    List<Cart> findByUserId(Long id);

    Cart findById(Long id);

    void deleteByUser(Users user);

    List<Cart> findByUser(Users user);
}

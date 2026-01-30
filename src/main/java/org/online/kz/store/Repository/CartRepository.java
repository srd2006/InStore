package org.online.kz.store.Repository;

import org.online.kz.store.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart getAllByType(String type);

    @Query("SELECT c FROM Cart c")
    List<Cart> getAllCarts();

    Integer getCartsById(long id);

    List<Cart> getCartsByPrice(BigDecimal price);


//
//    Cart findCartById(Integer cartId);

}

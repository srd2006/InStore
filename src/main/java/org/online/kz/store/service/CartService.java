package org.online.kz.store.service;

import org.online.kz.store.model.Cart;
import org.online.kz.store.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    Cart getCartById(int cartId);


    List<Cart> getAllCart();


    void addCart(Cart cart);

    void removeCartById(int cartId);

    void getGoodsById(long id);

    void addGoodsById(long id);

    Integer totalPrice(Cart cart);


}

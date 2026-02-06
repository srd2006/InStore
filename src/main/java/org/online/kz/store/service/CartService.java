package org.online.kz.store.service;

import org.online.kz.store.model.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CartService {

    Cart getCartById(int cartId);


    void addCart(Cart cart);

    void removeCartById(int cartId);

    void getGoodsById(long id);

    void addGoodsById(long id);

    public List<Cart> getMyCart();

    BigDecimal totalPrice();


}

package org.online.kz.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.online.kz.store.Repository.CartRepository;
import org.online.kz.store.Repository.GoodsRepository;
import org.online.kz.store.Repository.UsersRepository;
import org.online.kz.store.model.Cart;
import org.online.kz.store.model.Goods;
import org.online.kz.store.model.Users;
import org.online.kz.store.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("mainCart")
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final GoodsRepository goodsRepository;
    private final UsersRepository usersRepository;


    @Override
    public List<Cart> getAllCart() {
        return cartRepository.getAllCarts();
    }

    @Override
    public Cart getCartById(int cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }

    @Override
    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }


//

    @Override
    public void getGoodsById(long id) {
        goodsRepository.getGoodsById(id);
    }

    @Override
    public void removeCartById(int cartId) {
        cartRepository.deleteById(cartId);
    }


    @Override
    public void addGoodsById(long id) {
        Goods savedGoods = goodsRepository.getGoodsById(id);

        // Получаем текущего пользователя через Spring Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Users managedUser = usersRepository.findByFullUserName(username);

        Cart cart = new Cart();
        cart.setType(savedGoods.getType());
        cart.setTitle(savedGoods.getTitle());
        cart.setDescription(savedGoods.getDescription());
        cart.setPrice(savedGoods.getPrice());
        cart.setUser(managedUser);

        cartRepository.save(cart);
    }

    @Override
    public Integer totalPrice(Cart cart) {
        List<Cart> carts = cartRepository.getCartsByPrice(cart.getPrice());

        BigDecimal totalPrice = new BigDecimal(0);
        for (Cart cart1 : carts) {
            totalPrice = totalPrice.add(cart1.getPrice());
        }


        return totalPrice.intValue();
    }
}

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
import java.util.Objects;

@Service("mainCart")
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final GoodsRepository goodsRepository;
    private final UsersRepository usersRepository;


//    @Override
//    public List<Cart> getAllCart() {
//        return cartRepository.getAllCarts();
//    }

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
    public void addGoodsById(long goodsId) {


        Goods goods = goodsRepository.findById((int) goodsId)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = Objects.requireNonNull(auth).getName();

        Users user = usersRepository.findByFullUserName(username);
        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }


        Cart cart = new Cart();
        cart.setType(goods.getType());
        cart.setTitle(goods.getTitle());
        cart.setDescription(goods.getDescription());
        cart.setPrice(goods.getPrice());
        cart.setUser(user);


        cartRepository.save(cart);
    }


    @Override
    public List<Cart> getMyCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = Objects.requireNonNull(auth).getName();

        Users user = usersRepository.findByFullUserName(username);

        return cartRepository.findByUserId(user.getId());
    }

    @Override
    public BigDecimal totalPrice() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = Objects.requireNonNull(auth).getName();

        Users user = usersRepository.findByFullUserName(username);

        List<Cart> carts = cartRepository.findByUserId(user.getId());

        BigDecimal total = BigDecimal.ZERO;

        for (Cart cart : carts) {
            total = total.add(cart.getPrice());
        }
        return total;
    }
}

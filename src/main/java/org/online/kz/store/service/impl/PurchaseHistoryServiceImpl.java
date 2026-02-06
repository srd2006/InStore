package org.online.kz.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.online.kz.store.Repository.CartRepository;
import org.online.kz.store.Repository.PurchaseHistoryRepository;
import org.online.kz.store.Repository.UsersRepository;
import org.online.kz.store.model.Cart;
import org.online.kz.store.model.PurchaseHistory;
import org.online.kz.store.model.Users;
import org.online.kz.store.service.PurchaseHistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Service("mainPurchase")
@RequiredArgsConstructor
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {


    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final UsersRepository usersRepository;
    private final CartRepository cartRepository;


    @Transactional
    @Override
    public void historyOfOrder() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = Objects.requireNonNull(auth).getName();

        Users user = usersRepository.findByFullUserName(username);
        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }

        List<Cart> carts = cartRepository.findByUser(user);
        if (carts.isEmpty()) {
            throw new RuntimeException("Корзина пуста");
        }

        for (Cart cart : carts) {
            PurchaseHistory purchaseHistory = new PurchaseHistory();
            purchaseHistory.setType(cart.getType());
            purchaseHistory.setTitle(cart.getTitle());
            purchaseHistory.setDescription(cart.getDescription());
            purchaseHistory.setPrice(cart.getPrice());
            purchaseHistory.setDate(LocalDateTime.now());
            purchaseHistory.setUser(user);

            purchaseHistoryRepository.save(purchaseHistory);
        }

        cartRepository.deleteByUser(user);
    }

    @Override
    public List<PurchaseHistory> getMyHistory() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = Objects.requireNonNull(auth).getName();

        Users user = usersRepository.findByFullUserName(username);
        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }

        return purchaseHistoryRepository.findByUser(user);
    }

    @Transactional
    @Override
    public void deleteHistoryById(int id) {
        purchaseHistoryRepository.deleteById(id);
    }
}

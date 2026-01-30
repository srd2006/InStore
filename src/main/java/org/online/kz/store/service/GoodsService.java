package org.online.kz.store.service;

import org.jspecify.annotations.Nullable;
import org.online.kz.store.model.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsService {
    List<Goods> getAllGoods();

    void addGoods(Goods goods);

    List<Goods> findByName(String word);

    void deleteGoodsByID(int id);

    void updateGoods(Goods goods);

    @Nullable Object findById(int id);
}

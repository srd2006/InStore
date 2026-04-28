package org.online.kz.store.service;

import org.jspecify.annotations.Nullable;
import org.online.kz.store.model.Goods;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface GoodsService {
    List<Goods> getAllGoods();

    void addGoods(Goods goods);

    List<Goods> findByName(String word);

    void deleteGoodsByID(int id);

    void updateGoods(Goods goods, MultipartFile file) throws IOException;

    @Nullable Object findById(int id);
}

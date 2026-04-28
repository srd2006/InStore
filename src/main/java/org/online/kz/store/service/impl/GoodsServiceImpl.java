package org.online.kz.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.online.kz.store.Repository.GoodsRepository;
import org.online.kz.store.Repository.UsersRepository;
import org.online.kz.store.model.Goods;
import org.online.kz.store.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service(value = "mainGoodsService")
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {


    private final GoodsRepository goodsRepository;

    @Override
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public Goods findById(int id) {
        return goodsRepository.findById(id).get();
    }

    @Override
    public void addGoods(Goods goods) {
        goodsRepository.save(goods);
    }

    @Override
    public void deleteGoodsByID(int id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public List<Goods> findByName(String word) {
        return goodsRepository.findByName(word);
    }

    @Override
    public void updateGoods(Goods goods, MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {

            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            file.transferTo(new File(uploadDir + filename));

            goods.setImage(filename); // 🔥 обновляем фото
        } else {

            Goods existing = goodsRepository.findById(Math.toIntExact(goods.getId()))
                    .orElseThrow();

            goods.setImage(existing.getImage());
        }

        goodsRepository.save(goods);
    }

}

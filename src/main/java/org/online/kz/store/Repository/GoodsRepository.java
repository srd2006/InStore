package org.online.kz.store.Repository;


import org.online.kz.store.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    @Query("SELECT goods FROM Goods goods " +
            "WHERE goods.title ilike concat('%', :word, '%' ) " +
            "OR goods.type ilike concat('%', :word, '%')" +
            " OR goods.description ilike concat('%', :word, '%')")
    List<Goods> findByName(String word);



   void getGoodsById(long id);
}

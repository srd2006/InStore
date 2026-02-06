package org.online.kz.store.Repository;

import org.online.kz.store.model.PurchaseHistory;
import org.online.kz.store.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {


    List<PurchaseHistory> findByUser(Users user);

    void deleteById(int id);

    List<PurchaseHistory> id(int id);
}

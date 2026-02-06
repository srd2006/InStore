package org.online.kz.store.service;


import org.online.kz.store.model.PurchaseHistory;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface PurchaseHistoryService {


    void historyOfOrder();


    List<PurchaseHistory> getMyHistory();


    void deleteHistoryById(int id);
}

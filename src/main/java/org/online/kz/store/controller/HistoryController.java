package org.online.kz.store.controller;

import lombok.RequiredArgsConstructor;
import org.online.kz.store.service.PurchaseHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HistoryController {

    private final PurchaseHistoryService purchaseHistoryService;

    @GetMapping("/purchase-purchaseHistory")
    public String getPurchaseHistory(Model model) {
        model.addAttribute("purchaseHistory", purchaseHistoryService.getMyHistory());
        return "purchase-history";
    }

    @PostMapping("/cart/checkout")
    public String checkout() {
        purchaseHistoryService.historyOfOrder();
        return "redirect:/purchase-purchaseHistory";
    }

    @PostMapping("/purchase-purchaseHistory/delete/{id}")
    public String deleteHistoryById(@PathVariable int id) {
        purchaseHistoryService.deleteHistoryById(id);
        return "redirect:/purchase-purchaseHistory";
    }
}

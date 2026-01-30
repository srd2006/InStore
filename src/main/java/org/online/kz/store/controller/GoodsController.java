package org.online.kz.store.controller;


import lombok.RequiredArgsConstructor;
import org.online.kz.store.model.Goods;
import org.online.kz.store.service.GoodsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;


    @GetMapping("/")  // http://localhost:8088/
    @PreAuthorize("isAuthenticated()")
    public String getAllGoods(Model model) {
        model.addAttribute("store", goodsService.getAllGoods());
        return "goods";
    }

    @GetMapping("/add_goods")  // http://localhost:8088/add_goods
    public String getAddGoods(Model model) {
        model.addAttribute("store", goodsService.getAllGoods());
        return "add_goods";
    }

    @GetMapping("/search") // http://localhost:8088/search
    public String getSearchGoods(Model model, @RequestParam String word) {
        model.addAttribute("store", goodsService.findByName(word));
        return "goods";
    }

    @PostMapping("/add_goods") // http://localhost:8088/add_goods
    public String addGoods(@ModelAttribute Goods goods) {
        goodsService.addGoods(goods);
        return "add_goods";
    }


    @PostMapping("/delete-goods")
    public String deleteGood(@RequestParam int id) {
        goodsService.deleteGoodsByID(id);
        return "redirect:/";
    }

    @GetMapping("/update_goods/{id}")
    public String updateGods(@PathVariable int id, Model model) {
        model.addAttribute("stores", goodsService.findById(id));
        return "update_goods";
    }


    @PostMapping("/update_goods")
    public String updateGoodsPost(@ModelAttribute Goods goods) {
        goodsService.updateGoods(goods);
        return "redirect:/";
    }


    @GetMapping(value = "/purchase-history")
    public String getPurchaseHistory(Model model) {
        model.addAttribute("store", goodsService.getAllGoods());
        return "purchase-history";
    }


}



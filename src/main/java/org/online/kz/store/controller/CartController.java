package org.online.kz.store.controller;


import lombok.RequiredArgsConstructor;
import org.online.kz.store.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;


    @GetMapping("/cart")
    public String cartPage(Model model) {
        model.addAttribute("cartItems", cartService.getMyCart());
        model.addAttribute("totalPrice", cartService.totalPrice());
        return "cart";
    }

    @GetMapping(value = "/back")
    public String home() {
        return "redirect:/";
    }

    @GetMapping(value = "/cart/{id} ")
    public String getItemById(Model model, @PathVariable int id) {
        model.addAttribute("cartItems", cartService.getCartById(id));
        return "cart";
    }


    @PostMapping(value = "/buy/add")
    public String addItem(@RequestParam int id) {
        cartService.addGoodsById(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeItem(@RequestParam int id) {
        cartService.removeCartById(id);
        return "redirect:/cart";
    }




}

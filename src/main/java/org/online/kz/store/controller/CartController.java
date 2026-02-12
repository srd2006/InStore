package org.online.kz.store.controller;


import lombok.RequiredArgsConstructor;
import org.online.kz.store.dto.AddressDto;
import org.online.kz.store.model.Users;
import org.online.kz.store.service.CartService;
import org.online.kz.store.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;


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

    @PostMapping("/cart/change-address")
    public String changeAddress(@ModelAttribute AddressDto dto) {
        userService.setNewAddUsers(dto);
        return "redirect:/cart";
    }


}

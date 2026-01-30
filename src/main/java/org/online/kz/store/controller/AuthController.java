package org.online.kz.store.controller;


import lombok.RequiredArgsConstructor;
import org.online.kz.store.dto.UserDto;
import org.online.kz.store.model.Users;
import org.online.kz.store.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;


    @GetMapping(value = "/login")
    public String login(Model model, String email, String password) {
        model.addAttribute("store", userService.getUserByEmailPasswordAndUserPassword(email, password));
        return "login_page";
    }

    @GetMapping(value = "/register")
    public String registerPage() {
        return "register_page";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        userService.getUserByEmailPasswordAndUserPassword(email, password);
        return "redirect:/";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute Users user, Model model, UserDto userDto) {

        if (!user.getUserPassword().equals(userDto.getRePassword())) {
            model.addAttribute("error", true);
            return "register_page";
        }
        if (userService.existUserByEmail(userDto)) {
            model.addAttribute("error", true);
            return "register_page";
        }

        if (userService.existUserByNumber(userDto)) {
            model.addAttribute("error", true);
            return "register_page";
        }


        userService.addUser(user);
        return "redirect:/";
    }


}

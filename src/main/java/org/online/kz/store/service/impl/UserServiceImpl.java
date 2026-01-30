package org.online.kz.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.online.kz.store.Repository.GoodsRepository;
import org.online.kz.store.Repository.PermissionRepository;
import org.online.kz.store.Repository.UsersRepository;
import org.online.kz.store.dto.UserDto;
import org.online.kz.store.model.Permission;
import org.online.kz.store.model.Users;
import org.online.kz.store.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Objects;


@org.springframework.stereotype.Service("main")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UsersRepository usersRepository;
    private final GoodsRepository goodsRepository;
    private final PermissionRepository permissionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Users findUserById(long id) {
        return usersRepository.getUsersById(id);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String reNewPassword) {

        if (!newPassword.equals(reNewPassword)) {
            throw new IllegalArgumentException("Пароли не совпадают");
        }

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Users user = usersRepository.findByUserEmail(email);

        if (!bCryptPasswordEncoder.matches(oldPassword, user.getUserPassword())) {
            throw new IllegalArgumentException("Старый пароль неверный");
        }

        user.setUserPassword(
                bCryptPasswordEncoder.encode(newPassword)
        );

        usersRepository.save(user);
    }

    @Override
    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Users) Objects.requireNonNull(authentication).getPrincipal();
    }

    @Override
    public void addUser(Users users) {

        if (!Objects.equals(users.getPassword(), users.getRePassword())) {
            throw new RuntimeException("Вводите пароли правильно");

        }

        Permission permission = permissionRepository.findByRole("ROLE_USER");

        users.setUserPassword(passwordEncoder.encode(users.getUserPassword()));

        users.setPermissions(Collections.singletonList(permission));

        usersRepository.save(users);
    }


    @Override
    public Users getUserByEmailPasswordAndUserPassword(String email, String password) {
        return usersRepository.findByUserEmailAndUserPassword(email, password);
    }

    @Override
    public boolean existUserByEmail(UserDto userDto) {
        return usersRepository.existsByUserEmail(userDto.getUserEmail());
    }

    @Override
    public boolean existUserByNumber(UserDto userDto) {
        return usersRepository.existsByUserNumber(userDto.getUserNumber());
    }
}

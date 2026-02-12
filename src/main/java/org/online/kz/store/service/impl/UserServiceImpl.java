package org.online.kz.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.online.kz.store.Repository.PermissionRepository;
import org.online.kz.store.Repository.UsersRepository;
import org.online.kz.store.dto.AddressDto;
import org.online.kz.store.dto.UserDto;
import org.online.kz.store.model.Permission;
import org.online.kz.store.model.Users;
import org.online.kz.store.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Objects;


@org.springframework.stereotype.Service("main")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UsersRepository usersRepository;
    private final PermissionRepository permissionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Users findUserById(long id) {
        return usersRepository.getUsersById(id);
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword, String reNewPassword) {

        if (!newPassword.equals(reNewPassword)) {
            throw new IllegalArgumentException("Пароли не совпадают");
        }

        Users user = usersRepository.findByUserEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Пользователь с таким email не найден");
        }

        if (user.getUserPassword() == null) {
            throw new IllegalArgumentException("Пароль пользователя не задан");
        }

        if (!bCryptPasswordEncoder.matches(oldPassword, user.getUserPassword())) {
            throw new IllegalArgumentException("Старый пароль неверный");
        }

        user.setUserPassword(bCryptPasswordEncoder.encode(newPassword));
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

    @Override
    public void setNewAddUsers(AddressDto addressDto) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = Objects.requireNonNull(auth).getName();

        Users user = usersRepository.findByFullUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }


        Users existingUser = usersRepository
                .findByUserNumber(addressDto.getUserNumber())
                .orElse(null);

        if (existingUser != null && existingUser.getId() != user.getId()) {
            throw new RuntimeException("Этот номер уже используется");
        }


        user.setUserCity(addressDto.getUserCity());
        user.setUserAddress(addressDto.getUserAddress());
        user.setUserNumber(addressDto.getUserNumber());

        usersRepository.save(user);
    }
}

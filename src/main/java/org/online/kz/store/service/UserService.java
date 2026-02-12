package org.online.kz.store.service;


import org.online.kz.store.dto.AddressDto;
import org.online.kz.store.dto.UserDto;
import org.online.kz.store.model.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Users findUserById(long id);

    void addUser(Users users);

    Users getUserByEmailPasswordAndUserPassword(String email, String password);

    void changePassword(String email,String password, String newPassword, String reNewPassword);

    Users getCurrentUser();

    boolean existUserByEmail(UserDto userDto);

    boolean existUserByNumber(UserDto userDto);

    void setNewAddUsers(AddressDto addressDto);

}

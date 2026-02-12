package org.online.kz.store.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String userNumber;

    private String userEmail;

    private String address;

    private String city;

    private String password;

    private String rePassword;
}

package org.online.kz.store.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @Column
    private String fullUserName;

    @Column
    private String userCity;

    @Column(unique = true, nullable = false)
    private String userNumber;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column
    private String userPassword;

    @Transient
    private String rePassword;

    @Column
    private String userAddress;

    @ManyToMany
    private List<City> cities;



    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions;



    @Override
    public String getUsername() {
        return userNumber;
    }

    @Override
    public @Nullable String getPassword() {
        return userPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }
}

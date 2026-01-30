package org.online.kz.store.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permission", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private int Id;

    @Column
    private String role;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Users user;

    @Override
    public @Nullable String getAuthority() {
        return role;
    }


}

package org.online.kz.store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cart_store", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String type;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL)
    private Users user;

    @OneToMany
    private List<Goods> goods;
}

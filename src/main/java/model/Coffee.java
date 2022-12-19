package model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "supplier1"})

@Entity
@Table(name = "coffees")

@NamedQueries({ @NamedQuery(name = "hql_get_coffee_by_name",
        query = "from Coffee where cofName= :name") })

public class Coffee {

    @Id
    @Column(name = "id_prod", nullable = false)
    private Integer id;

    @Column(name = "COF_NAME", length = 32)
    private String cofName;

    @ManyToOne
    @JoinColumn(name = "SUPP_ID", nullable = false)
    private Supplier supplier1;

    @Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "SALES", nullable = false)
    private Integer sales;

    @Column(name = "TOTAL", nullable = false)
    private Integer total;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_prod", referencedColumnName = "id_prod", nullable = false)
    private Product product;

    @OneToOne (mappedBy="coffee", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private EncriptedCode encriptedCode;
}
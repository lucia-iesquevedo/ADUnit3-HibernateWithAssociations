/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "coffee"})

@Entity
@Table(name = "encriptedcodes")
public class EncriptedCode {

    @Id
    @Column(name="code")
    private int code;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_coffee")
    private Coffee coffee;

}
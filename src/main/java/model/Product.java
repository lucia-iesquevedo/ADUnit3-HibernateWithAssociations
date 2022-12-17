/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name= "products")
public class Product {

    private int id_prod;
    private String country;
    private EuropeanCode eu;

    public Product() {
    }
    
    public Product(int id_prod, String country, EuropeanCode eu) {
        this.id_prod = id_prod;
        this.country = country;
        this.eu = eu;
    }

    @Id
    @Column(name = "id_prod")
    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @ManyToOne
    @JoinColumn(name="FirstDig", referencedColumnName = "FirstDig", nullable=false)
    public EuropeanCode getEu() {
        return eu;
    }

    public void setEu(EuropeanCode eu) {
        this.eu = eu;
    }

    @Override
    public String toString() {
        return "Product{" + "id_prod=" + id_prod + ", country=" + country + ", eu=" + eu + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId_prod() == product.getId_prod() &&
                Objects.equals(getCountry(), product.getCountry()) &&
                Objects.equals(getEu(), product.getEu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_prod(), getCountry(), getEu());
    }
}
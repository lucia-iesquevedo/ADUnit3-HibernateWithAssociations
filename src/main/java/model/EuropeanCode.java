/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import jakarta.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "eucodes")
public class EuropeanCode {

    private int firstDig;
    private int secondDig;
    private int thirdDig;
    private Collection<Product> products;



    public EuropeanCode() {
    }

    public EuropeanCode(int firstDig, int secondDig, int thirdDig, Set<Product> products) {
        this.firstDig = firstDig;
        this.secondDig = secondDig;
        this.thirdDig = thirdDig;
        this.products = products;
    }

    @Id
    @Column(name = "FirstDig", nullable = false)
    public int getFirstDig() {
        return firstDig;
    }

    public void setFirstDig(int firstDig) {
        this.firstDig = firstDig;
    }

    @Column(name = "SecondDig", nullable = false)
    public int getSecondDig() {
        return secondDig;
    }

    public void setSecondDig(int secondDig) {
        this.secondDig = secondDig;
    }

    @Column(name = "ThirdDig", nullable = false)
    public int getThirdDig() {
        return thirdDig;
    }

    public void setThirdDig(int thirdDig) {
        this.thirdDig = thirdDig;
    }

    @OneToMany(mappedBy="eu")
    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "EuropeanCode{" +
                "firstDig=" + firstDig +
                ", secondDig=" + secondDig +
                ", thirdDig=" + thirdDig  +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EuropeanCode)) return false;
        EuropeanCode that = (EuropeanCode) o;
        return getFirstDig() == that.getFirstDig() &&
                getSecondDig() == that.getSecondDig() &&
                getThirdDig() == that.getThirdDig() &&
                Objects.equals(getProducts(), that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstDig(), getSecondDig(), getThirdDig());
    }
}
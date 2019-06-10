package edu.hcmuaf.food_order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "INFOPRODUCT")
@NoArgsConstructor
public class Product {

    public Product(String typeproduct, String productname, String decriptionproduct, String addressproduct,
                   String img, int price, String username, String phone) {
        this.typeproduct = typeproduct;
        this.productname = productname;
        this.decriptionproduct = decriptionproduct;
        this.addressproduct = addressproduct;
        this.img = img;
        this.price = price;
        this.username = username;
        this.phone = phone;
    }

    @Id
    @Column(name = "postid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postid;
    @Column(name = "typeproduct", nullable = false)
    private String typeproduct;
    @Column(name = "productname", nullable = false)
    private String productname;
    @Column(name = "decriptionproduct", nullable = false)
    private String decriptionproduct;
    @Column(name = "addressproduct", nullable = false)
    private String addressproduct;
    @Column(name = "img", nullable = false)
    private String img;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "posttime", nullable = false)
    private Date posttime;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "phone", nullable = false)
    private String phone;

}

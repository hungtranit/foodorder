package edu.hcmuaf.food_order.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "INFOPRODUCT")
@NoArgsConstructor
@Indexed
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


    @Field
    @Column(name = "productname", nullable = false)
    private String productname;

    @Field(analyze = Analyze.NO, store = Store.NO)
    @Column(name = "decriptionproduct", nullable = false)
    private String decriptionproduct;

    @Field(analyze = Analyze.NO, store = Store.YES)
    @Column(name = "addressproduct", nullable = false)
    private String addressproduct;

    @Column(name = "img", nullable = false)
    private String img;

    @Field
    @NumericField
    @Column(name = "price", nullable = false)
    private int price;


    @Temporal(value = TemporalType.TIMESTAMP)
    @Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    @DateBridge(resolution = Resolution.DAY)
    @Column(name = "posttime", nullable = false)
    private Date posttime;


    @Field()
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "typeproduct", nullable = false)
    @Field(index = Index.YES,analyze = Analyze.YES, store = Store.NO)
    private String typeproduct;


    @Field
    @Column(name = "phone", nullable = false)
    private String phone;


    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getTypeproduct() {
        return typeproduct;
    }

    public void setTypeproduct(String typeproduct) {
        this.typeproduct = typeproduct;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDecriptionproduct() {
        return decriptionproduct;
    }

    public void setDecriptionproduct(String decriptionproduct) {
        this.decriptionproduct = decriptionproduct;
    }

    public String getAddressproduct() {
        return addressproduct;
    }

    public void setAddressproduct(String addressproduct) {
        this.addressproduct = addressproduct;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

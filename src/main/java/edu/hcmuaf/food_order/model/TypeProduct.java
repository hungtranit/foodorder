package edu.hcmuaf.food_order.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TYPEPRODUCT")
@NoArgsConstructor
public class TypeProduct {

    @Id
    @Column(name = "typeproduct", nullable = false)
    private String typeproduct;
    @Column(name = "img", nullable = false)
    private String img;
    @Column(name = "nametype", nullable = false)
    private String nametype;

    @Override
    public String toString() {
        return "TypeProduct{" +
                "typeproduct='" + typeproduct + '\'' +
                ", img='" + img + '\'' +
                ", nametype='" + nametype + '\'' +
                '}';
    }
}

package edu.hcmuaf.food_order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INFOUSER")
public class InfoUser {

    @Id
    @Column(name = "username", nullable = false)
    @NotEmpty(message = "Enter last name.")
    private String username;
    @Column(name = "passworduser", nullable = false)
    @NotEmpty(message = "Enter last password.")
    private String password;
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "addressofuser", nullable = false)
    private String address;
    @Column(name = "email", nullable = false)
    private String mail;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "avatar", nullable = false)
    private String avatar;

    @Override
    public String toString() {
        return "InfoUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}

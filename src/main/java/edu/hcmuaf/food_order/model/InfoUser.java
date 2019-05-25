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
    @NotEmpty(message = "{username.not.empty}")
    private String username;
    @Column(name = "passworduser", nullable = false)
    @NotEmpty(message = "{password.not.empty}")
    private String password;
    @Column(name = "fulltname", nullable = false)
    @NotEmpty(message = "{fulltname.not.empty}")
    private String fulltname;
    @Column(name = "addressofuser", nullable = false)
    @NotEmpty(message = "{address.not.empty}")
    private String address;
    @Column(name = "email", nullable = false)
    @NotEmpty(message = "{email.not.valid}")
    private String email;
    @Column(name = "phone", nullable = false)
    @NotEmpty(message = "{phone.not.empty}")
    private String phone;
    @Column(name = "avatar", nullable = false)
    @NotEmpty(message = "{avatar.not.empty}")
    private String avatar;

    @Override
    public String toString() {
        return "InfoUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fulltname='" + fulltname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}

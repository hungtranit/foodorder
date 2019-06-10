package edu.hcmuaf.food_order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

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
    @ContainedIn
    @Column(name = "username", nullable = false)
    @NotEmpty(message = "{username.not.empty}")
    private String username;

    @Column(name = "passworduser", nullable = false)
    @NotEmpty(message = "{passworduser.not.empty}")
    private String passworduser;

    @Column(name = "fullname", nullable = false)
    @NotEmpty(message = "{fullname.not.empty}")
    @Field
    private String fullname;

    @Column(name = "addressofuser", nullable = false)
    @NotEmpty(message = "{address.not.empty}")
    private String addressofuser;

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
                ", passworduser='" + passworduser + '\'' +
                ", fullname='" + fullname + '\'' +
                ", addressofuser='" + addressofuser + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

}

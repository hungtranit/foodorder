package edu.hcmuaf.food_order.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "REPCMT")
public class Rep {

    @Id
    @Column(name = "repid", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int repID;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "timerep", nullable = false)
    private Date timerep;
    @Column(name = "cmtid", nullable = false)
    private int cmtid;
    @Column(name = "username", nullable = false)
    private String username;

}

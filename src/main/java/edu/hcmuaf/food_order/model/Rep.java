package edu.hcmuaf.food_order.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "REPCMT")
public class Rep {

    @Id
    @Column(name = "repid", nullable = false)
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

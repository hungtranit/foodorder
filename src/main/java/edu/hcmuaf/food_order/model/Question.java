package edu.hcmuaf.food_order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CMTTOQUESTION")
public class Question {

    @Id
    @Column(name = "questionid", nullable = false)
    private int questionid;
    @Column(name = "typeproduct", nullable = false)
    private int type;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "timecmt", nullable = false)
    private Date timecmt;
    @Column(name = "username", nullable = false)
    private String username;


}

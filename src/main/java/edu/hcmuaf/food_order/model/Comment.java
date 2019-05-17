package edu.hcmuaf.food_order.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "CMTTOQUESTION")
public class Comment {

    public Comment() {
    }

    public Comment(int cmtID, String content, Date timecmt, String userCMT, int questionID) {
        this.cmtID = cmtID;
        this.content = content;
        this.timecmt = timecmt;
        this.userCMT = userCMT;
        this.questionID = questionID;
    }

    public Comment(int questionID, String content, String userCMT) {
        this.content = content;
        this.userCMT = userCMT;
        this.questionID = questionID;
    }

    @Id
    @Column(name = "cmtid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cmtID;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "timecmt", nullable = false)
    private Date timecmt;
    @Column(name = "username", nullable = false)
    private String userCMT;
    @Column(name = "questionid", nullable = false)
    private int questionID;

}

package edu.hcmuaf.food_order.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "CMTTOQUESTION")
public class Comment {

    public Comment() {
    }

    public Comment(int cmtID, String content, Date timecmt, String username, int questionID) {
        this.cmtID = cmtID;
        this.content = content;
        this.timecmt = timecmt;
        this.username = username;
        this.questionID = questionID;
    }

    @Id
    @Column(name = "cmtid", nullable = false)
    private int cmtID;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "timecmt", nullable = false)
    private Date timecmt;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "questionid", nullable = false)
    private int questionID;

}

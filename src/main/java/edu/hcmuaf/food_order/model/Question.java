package edu.hcmuaf.food_order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "QUESTION")
public class Question {

    public Question(String typeproduct) {
        this.typeproduct = typeproduct;
    }

    public Question() {
    }

    public Question(int questionid, String typeproduct, String content, Date timeQuestion, String username) {
        this.questionid = questionid;
        this.typeproduct = typeproduct;
        this.content = content;
        this.timeQuestion = timeQuestion;
        this.username = username;
    }

    @Id
    @Column(name = "questionid", nullable = false)
    private int questionid;
    @Column(name = "typeproduct", nullable = false)
    private String typeproduct;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "timequestion", nullable = false)
    private Date timeQuestion;
    @Column(name = "username", nullable = false)
    private String username;


}

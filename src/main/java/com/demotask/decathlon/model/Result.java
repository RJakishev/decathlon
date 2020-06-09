package com.demotask.decathlon.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private Double points;

    private Double eventResult;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

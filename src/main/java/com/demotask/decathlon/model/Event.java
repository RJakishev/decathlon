package com.demotask.decathlon.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double a;
    private double b;
    private double c;

    @OneToMany(mappedBy = "event")
    private List<Result> results;
}

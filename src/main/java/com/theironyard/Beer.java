package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Beer {
    @Id
    @GeneratedValue
    Integer id;

    String name;
    String type;
    Integer calories;

    @ManyToOne
    User user;
}

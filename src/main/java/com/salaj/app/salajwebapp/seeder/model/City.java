package com.salaj.app.salajwebapp.seeder.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    private static final long serialVersionUID = 454136713213455311L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

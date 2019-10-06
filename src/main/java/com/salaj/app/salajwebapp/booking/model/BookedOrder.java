package com.salaj.app.salajwebapp.booking.model;

import com.salaj.app.salajwebapp.io.entity.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "booked_order")
public class BookedOrder implements Serializable {

    private static final long serialVersionUID = 4545154848846556L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "created_at",nullable = false)
    private Date createdAt;

    @Column(name = "no_of_guests")
    private int noOfGuest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getNoOfGuest() {
        return noOfGuest;
    }

    public void setNoOfGuest(int noOfGuest) {
        this.noOfGuest = noOfGuest;
    }
}

package com.shopstore.app.booking.service;

import com.shopstore.app.booking.model.Restaurant;

import java.util.Collection;

public interface RestaurantService {

    Collection<Restaurant> findAllByName(String name, int page, int limit) throws Exception;

    void add(Restaurant restaurant) throws Exception;

    void update(Restaurant restaurant) throws Exception;

    void delete(String id) throws Exception;

    Restaurant findById(String restaurantId) throws Exception;
}

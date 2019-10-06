package com.salaj.app.salajwebapp.booking.service;

import com.salaj.app.salajwebapp.booking.model.Restaurant;
import com.salaj.app.salajwebapp.io.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RestaurantServiceImp implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Collection<Restaurant> findAllByName(String name, int page, int limit) throws Exception {
        if (page > 0) page = page - 1;

        Pageable pageable = PageRequest.of(page,limit, Sort.Direction.DESC, "id");
        Page<Restaurant> restaurantPage = restaurantRepository.findAllByName(name, pageable);
        Collection<Restaurant> restaurants = restaurantPage.getContent();

        return restaurants;
    }

    @Override
    public void add(Restaurant restaurant) throws Exception {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) throws Exception {

    }

    @Override
    public void delete(String id) throws Exception {

    }

    @Override
    public Restaurant findById(String restaurantId) throws Exception {
        Restaurant restaurant = restaurantRepository.findRestaurantById(Long.valueOf(restaurantId));
        return restaurant;
    }
}

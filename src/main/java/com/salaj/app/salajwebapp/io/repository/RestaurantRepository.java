package com.salaj.app.salajwebapp.io.repository;

import com.salaj.app.salajwebapp.booking.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {

    Page<Restaurant> findAllByName(String name, Pageable pageable);
    Restaurant findRestaurantById(Long Id);
}

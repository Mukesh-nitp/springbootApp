package com.shopstore.app.io.repository;

import com.shopstore.app.address.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    City findCityById(Long id);
}

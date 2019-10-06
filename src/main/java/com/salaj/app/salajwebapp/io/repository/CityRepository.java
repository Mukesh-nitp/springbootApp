package com.salaj.app.salajwebapp.io.repository;

import com.salaj.app.salajwebapp.address.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    City findCityById(Long id);
}

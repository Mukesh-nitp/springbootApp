package com.shopstore.app.booking.controller;

import com.shopstore.app.booking.model.Restaurant;
import com.shopstore.app.booking.service.RestaurantService;
import com.shopstore.app.io.repository.CityRepository;
import com.shopstore.app.io.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Collection<Restaurant>> getRestaurantDetails(@RequestParam(name = "name") String name,
                                                                       @RequestParam(name = "page", defaultValue = "1") int page,
                                                                       @RequestParam(name = "limit", defaultValue = "25") int limit) throws Exception {
        name = name.trim().toLowerCase();
        Collection<Restaurant> restaurants = null;
        try {
            restaurants = restaurantService.findAllByName(name, page, limit);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurants.size() > 0 ? new ResponseEntity<>(restaurants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{restaurant_id}", method = RequestMethod.GET,
                    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Restaurant> findById(@PathVariable("restaurant_id") String id) throws Exception {
        id = id.trim();
        Restaurant restaurant = null;
        try {
            restaurant = restaurantService.findById(id);
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurant != null ? new ResponseEntity<>(restaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "add-restaurant", method = RequestMethod.POST,
                    consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Restaurant> add(@RequestParam(value = "name") String name,
                                          @RequestParam(value = "city_id") String cityId,
                                          @RequestParam(value = "state_id") String stateId,
                                          @RequestParam(value = "address") String address) {

        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setCity(cityRepository.findCityById(Long.valueOf(cityId)));
        restaurant.setState(stateRepository.findStateById(Long.valueOf(stateId)));
        try {
            restaurantService.add(restaurant);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

}

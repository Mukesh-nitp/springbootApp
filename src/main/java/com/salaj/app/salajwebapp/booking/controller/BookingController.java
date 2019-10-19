package com.salaj.app.salajwebapp.booking.controller;

import com.salaj.app.salajwebapp.booking.model.BookedOrder;
import com.salaj.app.salajwebapp.booking.service.BookingService;
import com.salaj.app.salajwebapp.booking.service.RestaurantService;
import com.salaj.app.salajwebapp.io.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping("/v1/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/book-table", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BookedOrder> add(@RequestParam(value = "restaurant_id") String restaurantId,
                                           @RequestParam(value = "noOfGuest") int noOfGuest) throws Exception{

        BookedOrder bookedOrder = new BookedOrder();
        bookedOrder.setRestaurant(restaurantService.findById(restaurantId));
        bookedOrder.setNoOfGuest(noOfGuest);
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bookedOrder.setUserEntity(userRepository.findByEmail(userEmail));
        bookedOrder.setCreatedAt(Calendar.getInstance().getTime());

        bookingService.add(bookedOrder);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

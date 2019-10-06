package com.salaj.app.salajwebapp.booking.service;

import com.salaj.app.salajwebapp.CustomException.BookingException;
import com.salaj.app.salajwebapp.booking.model.BookedOrder;
import com.salaj.app.salajwebapp.io.repository.BookingRepository;
import com.salaj.app.salajwebapp.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImp implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void add(BookedOrder bookedOrder) throws Exception {
        int previousGuestsCount = Utils.TOTAL_NO_GUESTS;
        int currentGuestCount = bookedOrder.getNoOfGuest() + previousGuestsCount;
        if (currentGuestCount > 20 || bookedOrder.getNoOfGuest() > 6 || bookedOrder.getNoOfGuest() <= 0)
            throw new BookingException(" Guest exceeding of total limit");
        bookingRepository.save(bookedOrder);
        Utils.TOTAL_NO_GUESTS = currentGuestCount;
    }
}

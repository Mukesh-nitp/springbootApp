package com.salaj.app.salajwebapp.booking.service;

import com.salaj.app.salajwebapp.booking.model.BookedOrder;

public interface BookingService {

    void add(BookedOrder bookedOrder) throws Exception;
}

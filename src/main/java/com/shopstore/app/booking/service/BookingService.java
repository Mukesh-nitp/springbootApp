package com.shopstore.app.booking.service;

import com.shopstore.app.booking.model.BookedOrder;

public interface BookingService {

    void add(BookedOrder bookedOrder) throws Exception;
}

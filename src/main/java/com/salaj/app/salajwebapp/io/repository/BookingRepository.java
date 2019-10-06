package com.salaj.app.salajwebapp.io.repository;

import com.salaj.app.salajwebapp.booking.model.BookedOrder;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends PagingAndSortingRepository<BookedOrder, Long> {
}

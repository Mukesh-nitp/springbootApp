package com.shopstore.app.io.repository;

import com.shopstore.app.booking.model.BookedOrder;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends PagingAndSortingRepository<BookedOrder, Long> {
}

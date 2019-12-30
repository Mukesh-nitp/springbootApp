package com.shopstore.app.io.repository;

import com.shopstore.app.address.model.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {
    State findStateById(Long id);
}

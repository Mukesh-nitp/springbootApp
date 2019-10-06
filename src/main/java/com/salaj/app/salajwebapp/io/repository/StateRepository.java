package com.salaj.app.salajwebapp.io.repository;

import com.salaj.app.salajwebapp.address.model.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State, Long> {
    State findStateById(Long id);
}

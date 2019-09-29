package com.salaj.app.salajwebapp.io.repository;

import com.salaj.app.salajwebapp.io.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
    UserEntity findUserByUserId(String userId);

}

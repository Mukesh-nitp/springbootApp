package com.shopstore.app.photo.album.repository;

import com.shopstore.app.photo.album.model.Photo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends PagingAndSortingRepository<Photo, Long> {

    Photo findPhotoById(Integer id);
}


package com.shopstore.app.photo.album.repository;

import com.shopstore.app.photo.album.model.Album;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {

    Album findAlbumById(Integer id);
}
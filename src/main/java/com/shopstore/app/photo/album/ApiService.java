package com.shopstore.app.photo.album;

import com.shopstore.app.photo.album.model.Album;
import com.shopstore.app.photo.album.model.Photo;
import com.shopstore.app.photo.album.repository.AlbumRepository;
import com.shopstore.app.photo.album.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public void addAlbums(List<Album> albums) {
        albumRepository.saveAll(albums);
    }

    public void addPhotos(List<Photo> photos) {
        photoRepository.saveAll(photos);
    }

    public Album getAlbumByAlbumId(Integer id) {
        return albumRepository.findAlbumById(id);
    }

    public Photo getPhotoByPhotoId(Integer id) {
        return photoRepository.findPhotoById(id);
    }

    public List<Album> getAllAlbums(int page, int limit) {
        List<Album> albumList = new ArrayList<>();
        if (page > 0) page = page - 1;

        Pageable pageable = PageRequest.of(page,limit);
        Page<Album> albums = albumRepository.findAll(pageable);
        albumList = albums.getContent();

        return albumList;
    }

    public List<Photo> getAllPhotos(int page, int limit) {
        List<Photo> photoList = new ArrayList<>();
        if (page > 0) page = page - 1;

        Pageable pageable = PageRequest.of(page, limit);
        Page<Photo> photos = photoRepository.findAll(pageable);
        photoList = photos.getContent();
        return photoList;
    }
}


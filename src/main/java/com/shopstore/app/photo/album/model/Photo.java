package com.shopstore.app.photo.album.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "photo")
public class Photo implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "album_id")
    private int albumId;

    @Column(name = "photo_id")
    private int PhotoId;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "thumbnailUrl")
    private String thumbnailUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getPhotoId() {
        return PhotoId;
    }

    public void setPhotoId(int photoId) {
        PhotoId = photoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}

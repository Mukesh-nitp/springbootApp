package com.shopstore.app.photo.album.controller;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.shopstore.app.photo.album.model.Album;
import com.shopstore.app.photo.album.ApiCallEXception;
import com.shopstore.app.photo.album.ApiService;
import com.shopstore.app.photo.album.model.Photo;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/v1/api")
public class ApiController {

    private static final ObjectMapper objectMapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
    private static final String DEFAULT_RESPONSE = "no data of this type";

    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "/store-album-photo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void storeAlbumAndPhotoInDb() throws UnirestException, IOException{
        HttpResponse<String> response1 = null;
        HttpResponse<String> response2 = null;
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
        Unirest.setHttpClient(httpClient);
        response1 = Unirest.get("https://jsonplaceholder.typicode.com/albums").asString();

        if (response1.getStatus() != HttpStatus.SC_OK) {
            throw new ApiCallEXception(response1.getStatusText());
        }

        List<Album> albums = objectMapper.readValue(response1.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Album.class));

        apiService.addAlbums(albums);

        List<Photo> photos = new ArrayList<>();
        for (Album album: albums) {
            response2 = Unirest.get("https://jsonplaceholder.typicode.com/photos?albumId="+ album.getId()).asString();
            if (response2.getStatus() != HttpStatus.SC_OK) {
                throw new ApiCallEXception(response2.getStatusText());
            }
            photos.addAll(objectMapper.readValue(response2.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, Photo.class)));
        }
        if (photos != null)
            apiService.addPhotos(photos);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAlbumOrPhotoBYId( @RequestParam(value = "type") String type,
                                       @RequestParam(value = "id") String id) throws UnirestException, IOException {

        Gson gson = new Gson();
        if (type.equalsIgnoreCase("album")){
            return gson.toJson(apiService.getAlbumByAlbumId(Integer.valueOf(id)));
        } else if (type.equalsIgnoreCase("photo")) {
            return gson.toJson(apiService.getPhotoByPhotoId(Integer.valueOf(id)));
        }
        return gson.toJson(DEFAULT_RESPONSE);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String allAlbumOrPhoto( @RequestParam(value = "type") String type,
                                   @RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) throws UnirestException, IOException {
        Gson gson = new Gson();
        if (type.equalsIgnoreCase("album")){
            return gson.toJson(apiService.getAllAlbums(page, limit));
        } else if (type.equalsIgnoreCase("photo")) {
            return gson.toJson(apiService.getAllPhotos(page, limit));
        }
        return gson.toJson(DEFAULT_RESPONSE);

    }

}

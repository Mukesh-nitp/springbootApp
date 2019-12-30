package com.shopstore.app.booking.zomatoResource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.shopstore.app.CustomException.ZomatoApiCallException;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/zomato")
public class PageResource {

    @Value("${zomatoApiKey}")
    private String apiKey;

    @Value("${zomatoApiUrl}")
    private String url;

    private static final ObjectMapper objectMapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);

    @RequestMapping(value = "/find-restaurant", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonNode findRestaurantByCity(@RequestParam(value = "city") String city,
                                         @RequestParam(value = "count", defaultValue = "10") int count,
                                         @RequestParam(value = "page", defaultValue = "1") int page) throws UnirestException, IOException {

        JsonNode jsonNodeResponse = null;
        HttpResponse<String> response = null;
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
        Unirest.setHttpClient(httpClient);
        response = Unirest.get(url + "cities?q=" + city + "&count=1" + "&apikey=" + apiKey).asString();
        if (response.getStatus() != HttpStatus.SC_OK) {
            throw new ZomatoApiCallException(response.getStatusText());
        }
        jsonNodeResponse = objectMapper.readTree(response.getBody());
        String searchQuery = "entity_id=" + jsonNodeResponse.get("location_suggestions").get(0).get("id") + "&entity_type=city&q=restaurant" + "&start=" + page + "&count=" + count;
        response = Unirest.get(url + "search?" + searchQuery + "&apikey=" + apiKey).asString();
        if (response.getStatus() != HttpStatus.SC_OK) {
            throw new ZomatoApiCallException(response.getStatusText());
        }
        jsonNodeResponse = objectMapper.readTree((response.getBody()));
        return jsonNodeResponse;
    }

    @RequestMapping(value = "/free-search", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonNode getRestaurantDetailsByFreeSearchKeyword(@RequestParam(value = "searchKeyword") String searchKeyword,
                                                            @RequestParam(value = "count", defaultValue = "10") int count,
                                                            @RequestParam(value = "page", defaultValue = "1") int page) throws UnirestException, IOException {
        JsonNode jsonNodeResponse = null;
        String searchQuery = searchKeyword +"&start="+page+ "&count="+count;
        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
        Unirest.setHttpClient(httpClient);
        HttpResponse<String> response = Unirest.get(url+"search?q="+searchQuery+"&apikey="+apiKey).asString();
        if (response.getStatus() != HttpStatus.SC_OK) {
            throw new ZomatoApiCallException(response.getStatusText());
        }
        jsonNodeResponse = objectMapper.readTree((response.getBody()));
        return jsonNodeResponse;
    }
}

package com.irenter.rest.services.impls;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.irenter.rest.services.GoogleMapService;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class GoogleMapServiceImpl implements GoogleMapService {


    public AddressComponent nearByPlaces() throws InterruptedException, ApiException, IOException {
        PlacesSearchResponse placesSearchResponse;
        AddressComponent addressComponent = new AddressComponent();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAhS6-wGyFUu9_TlMQXRlguOUS0Fmt2fXQ")
                .build();

        // context.setBaseUrlForTesting("http://127.0.0.1:" + server.getPort());
        try {
            LatLng location = new LatLng(39.113925, -77.204346);
             placesSearchResponse = PlacesApi.textSearchQuery(context, "restaurant")
                    .location(location)
                    .radius(1000)
                    .minPrice(PriceLevel.INEXPENSIVE)
                    .maxPrice(PriceLevel.VERY_EXPENSIVE)
                    .name("name")
                    .openNow(true)
                    .rankby(RankBy.DISTANCE)
                    .type(PlaceType.AIRPORT)
                    .await();

            System.out.println(gson.toJson(placesSearchResponse));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return addressComponent;
    }

    public Gson geocodingApiData(String address) throws InterruptedException, ApiException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAhS6-wGyFUu9_TlMQXRlguOUS0Fmt2fXQ")
                .build();


        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(results[0].addressComponents));
        return gson;
    }

}

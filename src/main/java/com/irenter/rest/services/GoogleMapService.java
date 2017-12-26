package com.irenter.rest.services;

import com.google.gson.Gson;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface GoogleMapService {
     AddressComponent nearByPlaces() throws InterruptedException, ApiException, IOException;
     Gson geocodingApiData(String address) throws InterruptedException, ApiException, IOException;
}

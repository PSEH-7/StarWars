package com.kashif.starwars.service;

import com.google.gson.Gson;
import com.kashif.starwars.dto.ResponseDTO;
import com.kashif.starwars.dto.Result;
import com.kashif.starwars.dto.SWAPIResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StarWarService {

    private static final String BASE_URI = "https://swapi.co/api/";

    public ResponseDTO getDetailsFor(String type, String name) {

        HttpHelper httpHelper = new HttpHelper();
        String requestURI = BASE_URI + type + "/?format=json";
        String responseFrom = httpHelper.getResponseFrom(requestURI);
        Gson gson = new Gson();
        SWAPIResponseDTO swapiResponseDTO = gson.fromJson(responseFrom, SWAPIResponseDTO.class);
        List<Result> filteredResults = new ArrayList<>();


        List<Result> filteredResult = swapiResponseDTO.getResults().stream().filter(result -> result.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        filteredResult.addAll(filteredResult);
        System.out.println(swapiResponseDTO.toString());
        if(swapiResponseDTO!=null) {
            while (swapiResponseDTO.getNext() != null) {
                String response = httpHelper.getResponseFrom(swapiResponseDTO.getNext());
                swapiResponseDTO = gson.fromJson(response, SWAPIResponseDTO.class);
                swapiResponseDTO.getResults().forEach(result -> {
                    if(result.getName().equalsIgnoreCase(name)) {
                        filteredResults.add(result);
                        System.out.println(name);
                    }
                });
            }
        }
        ResponseDTO responseDTO = new ResponseDTO(type,filteredResults.size(),name,filteredResults);
            return responseDTO;
    }
}

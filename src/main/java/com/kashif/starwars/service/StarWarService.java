package com.kashif.starwars.service;

import com.google.gson.Gson;
import com.kashif.starwars.dto.ResponseDTO;
import com.kashif.starwars.dto.Result;
import com.kashif.starwars.dto.SWAPIResponseDTO;
import ratpack.util.MultiValueMap;

import java.util.*;
import java.util.stream.Collectors;

public class StarWarService {

    private static final String BASE_URI = "https://swapi.co/api/";

    public ResponseDTO getDetailsFor(String type, String name, MultiValueMap<String, String> queryParams) {

        HttpHelper httpHelper = new HttpHelper();
        String requestURI = BASE_URI + type + "/?format=json";
        String nextResponse = httpHelper.getResponseFrom(requestURI);
        Gson gson = new Gson();
        List<Map> responseResult = new ArrayList<>();
        Map map = gson.fromJson(nextResponse, Map.class);
        while (map.get("next")!=null) {
            map = gson.fromJson(nextResponse, Map.class);
            List<Map> list = (List<Map>) map.get("results");
            for (int i = 0; i < list.size(); i++) {
                Map result = list.get(i);
                Set<String> keys = queryParams.keySet();
                Iterator itr = keys.iterator();
                while (itr.hasNext()) {
                String queryKey = (String) itr.next();
                    System.out.println("HI");
                    String queryValue = queryParams.get(queryKey);
                    if (result.containsKey(queryKey)) {
                        if (result.get(queryKey).equals(queryValue)) {
                            responseResult.add(result);
                        }
                    }
                }
            }
            nextResponse = httpHelper.getResponseFrom((String) map.get("next"));
        }
        ResponseDTO responseDTO = new ResponseDTO(type,responseResult.size(),name,responseResult);
            return responseDTO;
    }
}

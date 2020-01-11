package com.kashif.starwars.handlers;

import com.google.gson.Gson;
import com.kashif.starwars.dto.ResponseDTO;
import com.kashif.starwars.service.StarWarService;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Response;
import ratpack.http.Status;
import ratpack.util.MultiValueMap;

public class StarWarHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {
        String type = ctx.getRequest().getQueryParams().get("type");
//        MultiValueMap<String, String> queryParams = ctx.getRequest().getQueryParams();
        String name = ctx.getRequest().getQueryParams().get("name");
        System.out.println(type);
        System.out.println(name);
        StarWarService starWarService = new StarWarService();
        ResponseDTO responseDTO = starWarService.getDetailsFor(type, name);
        Gson gson = new Gson();
        Response response = ctx.getResponse();
        response.contentTypeIfNotSet("application/json");
        response.status(Status.OK).send(
                gson.toJson(responseDTO)
        );
//        ctx.render("Welcome to Baeldung ratpack!!!");
    }
}

package com.kashif.starwars;

import com.kashif.starwars.handlers.StarWarHandler;
import ratpack.server.RatpackServer;

public class Application {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(ratpackServerSpec -> {
            ratpackServerSpec.handlers(
                    chain ->
                            chain
                                    .get("get", new StarWarHandler())
            );
        });
    }
}

package com.example.oauthtest.resources;

import com.example.helloworld.api.Saying;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import com.example.oauthtest.ExampleOAuthAuthenticator;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicLong;


@Path("/auth/example")
@Produces(MediaType.APPLICATION_JSON)
public class AuthAppResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public AuthAppResource(String template, String defaultName) {
	    this.template = template;
	    this.defaultName = defaultName;
	    this.counter = new AtomicLong();
    }

    //@PermitAll
    @GET
    @Path("/callback")
    @Timed
    public Saying sayHello2(@QueryParam("code") String code) {
        System.out.println(code);
        
        return new Saying(counter.incrementAndGet(), code);
    }


    @POST
    public Saying sayHello() {
        return new Saying(counter.incrementAndGet(), "hello");
    }
}

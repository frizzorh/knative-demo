package com.redhat.developer.demos;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class GreeterResource {

    @ConfigProperty(name = "HOSTNAME")
    String hostname;

    public class Greet {

        private String greetings;

        public Greet(String greetings) {
            this.greetings = greetings;
        }

        public String getGreetings() {
            return greetings;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Greet greet(@QueryParam("name") @DefaultValue("Quarkus") String name) {
        return new Greet("Hello " + name + " from " + hostname + "!");
    }

    public static final Logger log = LoggerFactory.getLogger(GreeterResource.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void handleCloudEvent(String cloudEventJson) {
        log.info("received event: " + cloudEventJson);
        // fake processing time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.warn("Woke up while sleeping", e);
        }
    }

}

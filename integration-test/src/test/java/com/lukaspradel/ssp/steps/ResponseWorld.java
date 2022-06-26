package com.lukaspradel.ssp.steps;

import io.restassured.response.Response;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResponseWorld {

    private Response response;

    public Response getLastResponse() {
        return response;
    }

    public void setLastResponse(Response response) {
        this.response = response;
    }
}

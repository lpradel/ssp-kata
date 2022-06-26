package com.lukaspradel.ssp.model.rest;

public class NewPlayerRequestRepresentation {

    public String name;
    public String clientURL;

    public NewPlayerRequestRepresentation() {
    }

    public NewPlayerRequestRepresentation(String name, String clientURL) {
        this.name = name;
        this.clientURL = clientURL;
    }
}

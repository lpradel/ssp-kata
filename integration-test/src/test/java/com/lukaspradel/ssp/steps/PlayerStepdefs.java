package com.lukaspradel.ssp.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukaspradel.ssp.model.rest.NewPlayerRequestRepresentation;
import com.lukaspradel.ssp.model.rest.NewPlayerResponseRepresentation;
import com.lukaspradel.ssp.model.rest.PlayerRepresentation;
import com.lukaspradel.ssp.model.rest.PlayersResponseRepresentation;
import io.cucumber.java.de.Angenommen;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Wenn;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerStepdefs {

    private static final String BASE_URI = "http://localhost:8080/";
    private static final String PLAYERS_URI = BASE_URI + "api/players/";

    @Inject
    ObjectMapper objectMapper;

    @Inject
    ResponseWorld responseWorld;

    @Angenommen("es gibt keine Spieler")
    public void angenommenEsGibtKeineSpieler() {
        Response response = when().delete(PLAYERS_URI);
        response.then().statusCode(204);
    }

    @Angenommen("ich habe einen Spieler mit dem Namen {string} und der ClientURL {string} registriert")
    public void angenommenIchHabeEinenSpielerRegistriert(String name, String clientURL) throws JsonProcessingException {
        wennIchEinenSpielerRegistriere(name, clientURL);
    }

    @Wenn("ich alle Spieler abrufe")
    public void wennIchAlleSpielerAbrufe() {
        Response response = when().get(PLAYERS_URI);
        responseWorld.setLastResponse(response);
    }

    @Wenn("ich einen Spieler mit dem Namen {string} und der ClientURL {string} registriere")
    public void wennIchEinenSpielerRegistriere(String name, String clientURL) throws JsonProcessingException {
        NewPlayerRequestRepresentation newPlayerRequestRepresentation =
                new NewPlayerRequestRepresentation(name, clientURL);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(newPlayerRequestRepresentation))
                .when().put(PLAYERS_URI);
        responseWorld.setLastResponse(response);
    }

    @Dann("ist der Statuscode {int}")
    public void dannIstDerStatuscode(int statusCode) {
        responseWorld.getLastResponse().then().statusCode(statusCode);
    }

    @Dann("erhalte ich eine leere Liste")
    public void dannErhalteIchEineLeereListe() throws JsonProcessingException {
        PlayersResponseRepresentation playersResponseRepresentation =
                objectMapper.readValue(responseWorld.getLastResponse().then().extract().body().asString(), PlayersResponseRepresentation.class);
        assertTrue(playersResponseRepresentation.players.isEmpty());
    }

    @Dann("erhalte ich eine Antwort mit einer Spieler-ID")
    public void dannErhalteIchAntwortMitSpielerId() throws JsonProcessingException {
        NewPlayerResponseRepresentation newPlayerResponseRepresentation =
                objectMapper.readValue(responseWorld.getLastResponse().then().extract().body().asString(), NewPlayerResponseRepresentation.class);
        assertNotNull(newPlayerResponseRepresentation);
        assertNotEquals("0", newPlayerResponseRepresentation.id);
    }

    @Dann("enthält die Spielerliste einen Spieler mit dem Namen {string} und der ClientURL {string} und einem Score von {int} und {int} Siegen und {int} Niederlagen")
    public void dannEnthältDieSpielerlisteEinenSpieler(String name, String clientURL, int score, int won, int lost) throws JsonProcessingException {
        PlayersResponseRepresentation playersResponseRepresentation =
                objectMapper.readValue(responseWorld.getLastResponse().then().extract().body().asString(), PlayersResponseRepresentation.class);
        assertEquals(1, playersResponseRepresentation.players.size());
        PlayerRepresentation player = playersResponseRepresentation.players.get(0);
        assertEquals(name, player.name);
        assertEquals(clientURL, player.clientURL);
        assertEquals(score, player.score);
        assertEquals(won, player.won);
        assertEquals(lost, player.lost);
    }
}

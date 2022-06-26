package com.lukaspradel.ssp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayersController {

    @Autowired
    private PlayersRepository playersRepository;

    @GetMapping("/api/players")
    public ResponseEntity<PlayersRepresentation> getAllPlayers() {
        List<Player> allPlayers = playersRepository.findAll();
        PlayersRepresentation result = PlayersRepresentation.from(allPlayers);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/api/players")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPlayers() {
        playersRepository.deleteAll();
    }

    @PutMapping("/api/players")
    public ResponseEntity<NewPlayerResponseRepresentation> createPlayer(@RequestBody NewPlayerRequestRepresentation newPlayerRepresentation) {
        Player newPlayer = new Player(newPlayerRepresentation.getName(), newPlayerRepresentation.getClientURL());
        newPlayer = playersRepository.save(newPlayer);

        return new ResponseEntity<>(NewPlayerResponseRepresentation.from(newPlayer), HttpStatus.CREATED);
    }
}

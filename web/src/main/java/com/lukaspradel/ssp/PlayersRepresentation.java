package com.lukaspradel.ssp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value.Immutable
@JsonSerialize(as = ImmutablePlayersRepresentation.class)
@JsonDeserialize(as = ImmutablePlayersRepresentation.class)
public interface PlayersRepresentation {
    List<PlayerRepresentation> players();

    static PlayersRepresentation from(List<Player> players) {
        return ImmutablePlayersRepresentation.builder()
                .players(players.stream()
                        .map(PlayerRepresentation::from)
                        .collect(Collectors.toList()))
                .build();
    }
}

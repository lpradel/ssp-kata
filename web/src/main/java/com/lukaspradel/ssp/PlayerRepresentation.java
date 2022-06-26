package com.lukaspradel.ssp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutablePlayerRepresentation.class)
@JsonDeserialize(as = ImmutablePlayerRepresentation.class)
public interface PlayerRepresentation {
    int id();
    String name();
    String clientURL();
    int score();
    int won();
    int lost();

    static PlayerRepresentation from(Player player) {
        return ImmutablePlayerRepresentation.builder()
                .id(player.getId())
                .name(player.getName())
                .clientURL(player.getUrl())
                .score(player.getScore())
                .won(player.getWon())
                .lost(player.getLost())
                .build();
    }
}

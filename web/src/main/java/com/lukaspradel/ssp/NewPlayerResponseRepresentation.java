package com.lukaspradel.ssp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableNewPlayerResponseRepresentation.class)
@JsonDeserialize(as = ImmutableNewPlayerResponseRepresentation.class)
public interface NewPlayerResponseRepresentation {

    String getId();

    static NewPlayerResponseRepresentation from(Player player) {
        return ImmutableNewPlayerResponseRepresentation.builder()
                .id(String.valueOf(player.getId()))
                .build();
    }
}

package com.lukaspradel.ssp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableNewPlayerRequestRepresentation.class)
@JsonDeserialize(as = ImmutableNewPlayerRequestRepresentation.class)
public interface NewPlayerRequestRepresentation {

    String getName();
    String getClientURL();
}

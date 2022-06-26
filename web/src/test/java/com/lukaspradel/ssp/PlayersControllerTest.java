package com.lukaspradel.ssp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PlayersControllerTest {

    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    private static final Player PLAYER_1 = new Player("lukas", "https://localhost/lukas");
    private static final Player PLAYER_2 = new Player("tobias", "https://localhost/tobias");


    private static final String NEW_PLAYER_1_NAME = "oliver";
    private static final String NEW_PLAYER_1_CLIENT_URL = "https://localhost/oliver";
    private static final NewPlayerRequestRepresentation NEW_PLAYER_1_REPRESENTATION =
            ImmutableNewPlayerRequestRepresentation.builder()
                    .name(NEW_PLAYER_1_NAME)
                    .clientURL(NEW_PLAYER_1_CLIENT_URL)
                    .build();
    private static final Player NEW_PLAYER_1 = new Player(NEW_PLAYER_1_NAME, NEW_PLAYER_1_CLIENT_URL);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayersRepository playersRepository;

    @Nested
    class GivenNoPlayers {

        @BeforeEach
        void setUp() {
            when(playersRepository.findAll()).thenReturn(Collections.emptyList());
        }

        @Nested
        class WhenGetAllPlayers {

            private ResultActions resultActions;

            @BeforeEach
            void setUp() throws Exception {
                resultActions = mockMvc.perform(get("/api/players"));
            }

            @Test
            void thenStatusIs200Ok() throws Exception {
                resultActions.andExpect(status().isOk());
            }

            @Test
            void thenReturnsEmptyPlayers() throws Exception {
                PlayersRepresentation expectedPlayersRepresentation =
                        ImmutablePlayersRepresentation.builder().players(Collections.EMPTY_LIST).build();

                resultActions.andExpect(content().json(OBJECT_MAPPER.writeValueAsString(expectedPlayersRepresentation)));
            }
        }

        @Nested
        class WhenCreateNewPlayer {
            private ResultActions resultActions;

            @BeforeEach
            void setUp() throws Exception {
                when(playersRepository.save(any(Player.class))).thenReturn(NEW_PLAYER_1);

                resultActions = mockMvc.perform(put("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(NEW_PLAYER_1_REPRESENTATION)));
            }

            @Test
            void thenStatusIs201Created() throws Exception {
                resultActions.andExpect(status().isCreated());
            }

            @Test
            void thenNewPlayerIsSaved() {
                verify(playersRepository).save(NEW_PLAYER_1);
            }

            @Test
            void thenNewPlayerIsReturned() throws Exception {
                NewPlayerResponseRepresentation expectedNewPlayerResponseRepresentation =
                        ImmutableNewPlayerResponseRepresentation.builder()
                                .id("0")
                                .build();

                resultActions.andExpect(content().json(OBJECT_MAPPER.writeValueAsString(expectedNewPlayerResponseRepresentation)));
            }
        }
    }

    @Nested
    class GivenExistingPlayers {

        List<Player> players;

        @BeforeEach
        void setUp() {
            players = new ArrayList<>();
            players.add(PLAYER_1);
            players.add(PLAYER_2);
            when(playersRepository.findAll()).thenReturn(players);
        }

        @Nested
        class WhenGetAllPlayers {

            private ResultActions resultActions;

            @BeforeEach
            void setUp() throws Exception {
                resultActions = mockMvc.perform(get("/api/players"));
            }

            @Test
            void thenStatusIs200Ok() throws Exception {
                resultActions.andExpect(status().isOk());
            }

            @Test
            void thenReturnsExistingPlayers() throws Exception {
                PlayersRepresentation expectedPlayersRepresentation =
                        ImmutablePlayersRepresentation.builder().addPlayers(
                                PlayerRepresentation.from(PLAYER_1), PlayerRepresentation.from(PLAYER_2))
                                        .build();

                resultActions.andExpect(content().json(OBJECT_MAPPER.writeValueAsString(expectedPlayersRepresentation)));
            }
        }

        @Nested
        class WhenDeleteAllPlayers {

            private ResultActions resultActions;

            @BeforeEach
            void setUp() throws Exception {
                resultActions = mockMvc.perform(delete("/api/players"));
            }

            @Test
            void thenStatusIs204NoContent() throws Exception {
                resultActions.andExpect(status().isNoContent());
            }

            @Test
            void thenAllPlayersAreDeleted() {
                verify(playersRepository).deleteAll();
            }
        }
    }
}

package com.memcards.controller;

import com.memcards.model.DeckBuilder;
import com.memcards.service.DeckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeckResource.class)
class DeckResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeckService deckService;

    @Test
    void shouldReturnAListOfDecks() throws Exception {
        var deck = new DeckBuilder()
                .withDeckName("Spanish 100")
                .build();
        var decks = singletonList(deck);
        when(deckService.getAllDecks()).thenReturn(decks);

        mockMvc.perform(get("/decks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(decks.get(0).getName()));
    }

    @Test
    void shouldCreateADeck() throws Exception {
        var newDeck = new DeckBuilder()
                .withDeckName("Portuguese")
                .build();
        when(deckService.createDeck(any())).thenReturn(newDeck);

        String createdDeckAsJson = "{\"name\": \"Portuguese 100\"}";

        mockMvc.perform(post("/decks").contentType(MediaType.APPLICATION_JSON).content(createdDeckAsJson)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(newDeck.getName()));

    }

    @Test
    void shouldUpdateADeck() throws Exception {
        var updatedDeck = new DeckBuilder().build();

        when(deckService.updateDeck(any(), any())).thenReturn(updatedDeck);

        String updatedDeckAsJson = "{\"name\":" + "\"" + updatedDeck.getName() + "\"}";

        mockMvc.perform(put("/decks/" + updatedDeck.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedDeckAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedDeck.getName()))
                .andExpect(jsonPath("$.id").value(updatedDeck.getId()));
    }

    @Test
    void shouldDeleteDeck() throws Exception {
        var deckToDelete = new DeckBuilder().build();

        mockMvc.perform(delete("/decks/" + deckToDelete.getId().toString()))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetDeck() throws Exception {
        var deckToReturn = new DeckBuilder().build();
        when(deckService.getDeck(any())).thenReturn(deckToReturn);

        mockMvc.perform(get("/decks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deck.name").value(deckToReturn.getName()))
                .andExpect(jsonPath("$.flashcards").isArray());
    }
}
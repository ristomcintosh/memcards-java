package com.memcards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memcards.controller.dto.CreateFlashcardRequestBuilder;
import com.memcards.controller.dto.CreatedFlashcardRequest;
import com.memcards.controller.dto.UpdateFlashcardRequestBuilder;
import com.memcards.model.Flashcard;
import com.memcards.model.FlashcardBuilder;
import com.memcards.service.FlashcardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlashcardResource.class)
class FlashcardResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlashcardService flashcardService;

    @Test
    void shouldCreateAFlashcard() throws Exception {
        var newFlashcard = new CreateFlashcardRequestBuilder().build();

        when(flashcardService.createFlashcard(any(), any())).thenReturn(new FlashcardBuilder().build());

        ObjectMapper mapper = new ObjectMapper();
        String newFlashcardAsJson = mapper.writeValueAsString(newFlashcard);
        var postUrl = "/decks/1/flashcards";
        mockMvc.perform(post(postUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newFlashcardAsJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateAFlashcard() throws Exception {
        var updatedFlashcard = new UpdateFlashcardRequestBuilder().build();

        Flashcard newFlashcard = new FlashcardBuilder().withFlashcardId(UUID.fromString(updatedFlashcard.getId())).build();
        when(flashcardService.updateFlashcard(any(), any())).thenReturn(newFlashcard);

        ObjectMapper mapper = new ObjectMapper();
        String updatedFlashcardAsJson = mapper.writeValueAsString(updatedFlashcard);
        var putUrl = "/decks/1/flashcards/" + newFlashcard.getId();
        mockMvc.perform(put(putUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedFlashcardAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newFlashcard.getId().toString()));
    }

    @Test
    void shouldDeleteFlashcard() throws Exception {

        mockMvc.perform(delete("/decks/1/flashcards/1"))
                .andExpect(status().isNoContent());
    }
}
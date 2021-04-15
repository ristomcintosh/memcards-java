package com.memcards.model;

import com.memcards.controller.dto.FlashcardResponse;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class DeckBuilder {
    private Long id = 1L;
    private String name = "Test Deck";
    private List<Flashcard> flashcards = emptyList();

    public DeckBuilder withDeckId(Long deckId) {
        this.id = deckId;
        return this;
    }

    public DeckBuilder withDeckName(String deckName) {
        this.name = deckName;
        return this;
    }

    public DeckBuilder withFlashcards(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
        return this;
    }

    public Deck build() {
        Deck deck = new Deck();
        deck.setId(id);
        deck.setName(name);
        deck.setFlashcards(flashcards);
        return deck;
    }
}

package com.memcards.controller.dto;

import com.memcards.model.Deck;
import com.memcards.model.Flashcard;

import java.util.List;
import java.util.stream.Collectors;

public class DeckWithFlashcardsResponse {
    private DeckResponse deck;
    private List<FlashcardResponse> flashcards;

    public DeckWithFlashcardsResponse(Deck deck) {
        this.deck = new DeckResponse(deck);
        this.flashcards = deck.getFlashcards()
            .stream()
            .map(FlashcardResponse::new)
            .collect(Collectors.toList());
    }

    public DeckResponse getDeck() {
        return deck;
    }

    public void setDeck(DeckResponse deck) {
        this.deck = deck;
    }

    public List<FlashcardResponse> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<FlashcardResponse> flashcards) {
        this.flashcards = flashcards;
    }
}

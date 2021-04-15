package com.memcards.controller.dto;

import com.memcards.model.Deck;

import java.util.List;
import java.util.stream.Collectors;

public class DecksResponse {
    private final List<DeckResponse> decks;

    public List<DeckResponse> getDecks() {
        return decks;
    }

    public DecksResponse(List<Deck> decks) {
        this.decks = decks.stream().map(DeckResponse::new).collect(Collectors.toList());
    }
}

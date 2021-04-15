package com.memcards.controller.dto;

import com.memcards.model.Deck;

public class DeckResponse {
    private String id;
    private String name;
    private Integer cardCount;

    public DeckResponse(Deck deck) {
        this.id = deck.getId().toString();
        this.name = deck.getName();
        this.cardCount = deck.getFlashcards() == null ? 0 : deck.getFlashcards().size();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCardCount() {
        return cardCount;
    }

    public void setCardCount(Integer cardCount) {
        this.cardCount = cardCount;
    }
}

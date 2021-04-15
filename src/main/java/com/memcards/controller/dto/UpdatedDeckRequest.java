package com.memcards.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.memcards.model.Deck;
import com.sun.istack.NotNull;

public class UpdatedDeckRequest {
    @NotNull
    private String name;

    public UpdatedDeckRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    public Deck toDeck() {
        var deck = new Deck();
        deck.setName(this.name);
        return deck;
    }
}

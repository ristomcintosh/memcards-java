package com.memcards.model;

import java.util.UUID;

public class FlashcardBuilder {
    private UUID id = UUID.randomUUID();
    private Deck deck = new DeckBuilder().build();
    private String front = "Test Front";
    private String back = "Test Back";
    private String image_src = "image src";
    private String image_alt = "image alt";
    private String image_thumb = "image thumb";

    public FlashcardBuilder withFlashcardId(UUID flashcardId){
        this.id = flashcardId;
        return this;
    }

    public FlashcardBuilder withFront(String front) {
        this.front = front;
        return this;
    }

    public FlashcardBuilder withBack(String back) {
        this.back = back;
        return this;
    }

    public Flashcard build() {
        Flashcard flashcard = new Flashcard();
        flashcard.setId(id);
        flashcard.setDeck(deck);
        flashcard.setFront(front);
        flashcard.setBack(back);
        flashcard.setImage_src(image_src);
        flashcard.setImage_alt(image_alt);
        flashcard.setImage_thumb(image_thumb);
        return flashcard;
    }
}

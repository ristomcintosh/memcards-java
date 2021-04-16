package com.memcards.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Flashcard {

    @Id
    @GeneratedValue
    private UUID id;
    //    private Long deckId;
    private String front;
    private String back;
    private String image_src;
    private String image_alt;
    private String image_thumb;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;


    public Flashcard(String front, String back, String image_src, String image_alt, String image_thumb) {
        this.front = front;
        this.back = back;
        this.image_src = image_src;
        this.image_alt = image_alt;
        this.image_thumb = image_thumb;
    }

    public Flashcard() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public String getImage_alt() {
        return image_alt;
    }

    public void setImage_alt(String image_alt) {
        this.image_alt = image_alt;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}

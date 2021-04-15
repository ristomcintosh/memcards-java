package com.memcards.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.memcards.model.Flashcard;

public class CreatedFlashcardRequest {
    private String front;
    private String back;
    private ImageDto image;

    public CreatedFlashcardRequest(String front,
                                   String back,
                                  ImageDto image) {
        this.front = front;
        this.back = back;
        this.image = image;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public ImageDto getImage() {
        return image;
    }

    public Flashcard toFlashcard() {
        var flashcard = new Flashcard();
        flashcard.setFront(front);
        flashcard.setBack(back);
       if(image != null) {
           addImageToFlashcard(flashcard);
       }
        return flashcard;
    }

    private void addImageToFlashcard(Flashcard flashcard) {
        flashcard.setImage_src(image.getSrc());
        flashcard.setImage_alt(image.getAlt());
        flashcard.setImage_thumb(image.getThumb());
    }
}

package com.memcards.controller.dto;

import com.memcards.model.Flashcard;

import java.util.UUID;

public class UpdatedFlashcardRequest {
    private String id;
    private String front;
    private String back;
    private ImageDto image;

    public UpdatedFlashcardRequest(String id, String front,
                                   String back,
                                   ImageDto image) {
        this.id = id;
        this.front = front;
        this.back = back;
        this.image = image;
    }

    public String getId() {
        return id;
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
        flashcard.setId(UUID.fromString(id));
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

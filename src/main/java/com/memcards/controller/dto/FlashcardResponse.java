package com.memcards.controller.dto;

import com.memcards.model.Flashcard;

import java.util.List;

public class FlashcardResponse {
    private String id;
    private String front;
    private String back;
    private ImageDto image;

    public FlashcardResponse(Flashcard flashcard) {
        this.id = flashcard.getId().toString();
        this.front = flashcard.getFront();
        this.back = flashcard.getBack();
        if (flashcard.getImage_src() != null) {
            addImagesToResponse(flashcard);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    private void addImagesToResponse(Flashcard flashcard) {
        this.image = new ImageDto(flashcard.getImage_src(), flashcard.getImage_alt(), flashcard.getImage_thumb());
    }
}

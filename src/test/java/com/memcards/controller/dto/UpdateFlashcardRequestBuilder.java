package com.memcards.controller.dto;

import java.util.UUID;

public class UpdateFlashcardRequestBuilder {
    private String id = UUID.randomUUID().toString();
    private String front = "Test Front";
    private String back = "Test Back";
    private String image_src = "image src";
    private String image_alt = "image alt";
    private String image_thumb = "image thumb";


    public UpdateFlashcardRequestBuilder withFront(String front) {
        this.front = front;
        return this;
    }

    public UpdateFlashcardRequestBuilder withBack(String back) {
        this.back = back;
        return this;
    }

    public UpdatedFlashcardRequest build() {
        var image = new ImageDto(image_src, image_alt, image_thumb);
        return new UpdatedFlashcardRequest(id, front, back, image);
    }
}

package com.memcards.controller.dto;

public class CreateFlashcardRequestBuilder {
    private String front = "Test Front";
    private String back = "Test Back";
    private String image_src = "image src";
    private String image_alt = "image alt";
    private String image_thumb = "image thumb";


    public CreateFlashcardRequestBuilder withFront(String front) {
        this.front = front;
        return this;
    }

    public CreateFlashcardRequestBuilder withBack(String back) {
        this.back = back;
        return this;
    }

    public CreatedFlashcardRequest build() {
        var image = new ImageDto(image_src, image_alt, image_thumb);
        return new CreatedFlashcardRequest(front, back, image);
    }
}

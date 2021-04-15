package com.memcards.controller.dto;

public class ImageDto {
    private String src;
    private String alt;
    private String thumb;

    public ImageDto(){}

    public ImageDto(String src, String alt, String thumb) {
        this.src = src;
        this.alt = alt;
        this.thumb = thumb;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}

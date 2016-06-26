package me.cafecode.android.orchididentity.api;

import com.google.gson.annotations.SerializedName;

public class Orchid {

    private String name;
    private String detail;
    @SerializedName("image_url")
    private String imageUrl;

    public Orchid() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package me.cafecode.android.orchididentity.api;

import com.google.gson.annotations.SerializedName;

public class Orchid {

    //region Fields
    @SerializedName("paph_id")
    private int id;

    @SerializedName("sci_name")
    private String scienceName;

    @SerializedName("other_name")
    private String otherName;

    @SerializedName("native_place")
    private String nativePlace;

    private String general;

    private String shoot;

    private String leaf;

    private String flower;

    private String bloom;

    private String nature;

    @SerializedName("photoImage")
    private String photoImage;

    @SerializedName("orchid_image")
    private String orchidImage;
    //endregion

    public Orchid() {
    }

    //region Getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScienceName() {
        return scienceName;
    }

    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getShoot() {
        return shoot;
    }

    public void setShoot(String shoot) {
        this.shoot = shoot;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getFlower() {
        return flower;
    }

    public void setFlower(String flower) {
        this.flower = flower;
    }

    public String getBloom() {
        return bloom;
    }

    public void setBloom(String bloom) {
        this.bloom = bloom;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(String photoImage) {
        this.photoImage = photoImage;
    }

    public String getOrchidImage() {
        return orchidImage;
    }

    public void setOrchidImage(String orchidImage) {
        this.orchidImage = orchidImage;
    }
    //endregion
}

package me.cafecode.android.orchididentity.api;

public class ApiResponse {

    private String message;
    private Orchid orchid;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Orchid getOrchid() {
        return orchid;
    }

    public void setOrchid(Orchid orchid) {
        this.orchid = orchid;
    }
}

package me.cafecode.android.orchididentity.api;

public interface ResponseCallback<T> {

    void startRequest();
    void endRequest();
    void onSuccess(T response);
    void onFailure(Throwable error);

}

package me.cafecode.android.orchididentity.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdentifyOrchidListener implements Callback<Orchid> {

    private ResponseCallback<Orchid> mCallback;

    public IdentifyOrchidListener(ResponseCallback<Orchid> callback) {
        mCallback = callback;

        mCallback.startRequest();
    }

    @Override
    public void onResponse(Call<Orchid> call, Response<Orchid> response) {

        if (response.body() != null) {
            final Orchid orchid = response.body();
            mCallback.onSuccess(orchid);
        } else {
            mCallback.onFailure();
        }

        mCallback.endRequest();
    }

    @Override
    public void onFailure(Call<Orchid> call, Throwable t) {
        mCallback.onFailure();
        mCallback.endRequest();
    }

}

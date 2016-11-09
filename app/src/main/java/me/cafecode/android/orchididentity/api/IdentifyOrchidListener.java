package me.cafecode.android.orchididentity.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IdentifyOrchidListener implements Callback<ApiResponse> {

    private ResponseCallback<ApiResponse> mCallback;

    public IdentifyOrchidListener(ResponseCallback<ApiResponse> callback) {
        mCallback = callback;

        mCallback.startRequest();
    }

    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

        if (response.isSuccessful()) {
            final ApiResponse apiResponse = response.body();
            mCallback.onSuccess(apiResponse);
        } else {
            mCallback.onFailure(new Throwable("Bad request."));
        }

        mCallback.endRequest();
    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {
        mCallback.onFailure(t);
        mCallback.endRequest();
    }

}

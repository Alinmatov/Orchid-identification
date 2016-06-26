package me.cafecode.android.orchididentity.api;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ApiManager {

    public static Call<ApiResponse> identifyOrchid(File photoFile) {
//        final RequestBody body = RequestBody.create(MediaType.parse("image/*"), photoFile);

        final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), photoFile);
        final MultipartBody.Part body = MultipartBody.Part.createFormData("photo", photoFile.getName(), requestBody);

        return new ApiBuilder().getEndpoint().identifyOrchid(body);
    }

    public static void identifyOrchid(File photoFile, ResponseCallback<ApiResponse> callback) {
        final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), photoFile);
        final MultipartBody.Part body = MultipartBody.Part.createFormData("photo", photoFile.getName(), requestBody);


        final Call<ApiResponse> call = new ApiBuilder().getEndpoint().identifyOrchid(body);
        final IdentifyOrchidListener listener = new IdentifyOrchidListener(callback);
        call.enqueue(listener);
    }

}

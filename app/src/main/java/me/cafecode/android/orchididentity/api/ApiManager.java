package me.cafecode.android.orchididentity.api;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ApiManager {

    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static Call<ApiResponse> identifyOrchid(File photoFile, String source) {
//        final RequestBody body = RequestBody.create(MediaType.parse("image/*"), photoFile);

        final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), photoFile);
        final MultipartBody.Part body = MultipartBody.Part.createFormData("photo", photoFile.getName(), requestBody);

        return new ApiBuilder().getEndpoint().identifyOrchid(body, createPartFromString(source));
    }

    public static void identifyOrchid(File photoFile, String source, ResponseCallback<ApiResponse> callback) {
        final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), photoFile);
        final MultipartBody.Part body = MultipartBody.Part.createFormData("photo", photoFile.getName(), requestBody);


        final Call<ApiResponse> call = new ApiBuilder().getEndpoint().identifyOrchid(body, createPartFromString(source));
        final IdentifyOrchidListener listener = new IdentifyOrchidListener(callback);
        call.enqueue(listener);
    }

    private static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }
}

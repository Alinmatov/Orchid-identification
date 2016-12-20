package me.cafecode.android.orchididentity.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

interface ApiInterface {

    @Multipart
    @POST("/orchid-api/api/orchid.php")
    Call<ApiResponse> identifyOrchid(@Part MultipartBody.Part photo, @Part("source") RequestBody source);

}

package me.cafecode.android.orchididentity.api;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @Multipart
    @POST("/orchid-api/api/orchid.php")
    Call<ApiResponse> identifyOrchid(@Part MultipartBody.Part photo);

}

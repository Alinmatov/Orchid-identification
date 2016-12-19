package me.cafecode.android.orchididentity.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import me.cafecode.android.orchididentity.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

    private ApiInterface mApiEndpointInterface;
    private Retrofit retrofit;

    public ApiBuilder() {
        // Create gson
        final Gson gson = new GsonBuilder()
                .create();

        // Create client
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        // Create retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOST_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        mApiEndpointInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getEndpoint() {
        return mApiEndpointInterface;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}

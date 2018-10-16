package update.version.versionupdater.api;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cyberman on 6/10/2018.
 */

public class ServiceGenerator {

    public static final String BASE_URL = "http://10.0.3.2/";



    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(buildGson())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private static Converter.Factory buildGson() {
        return GsonConverterFactory.create();

    }

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(
            Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());

            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }


}

package update.version.versionupdater.api;



import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import update.version.versionupdater.api.model.QueryResponse;
import update.version.versionupdater.api.model.SuccessResponse;
import update.version.versionupdater.api.model.Version;
import update.version.versionupdater.api.model.WhatsNew;

public interface Api {
    @POST("test/")
    Observable<QueryResponse> getData();


    @POST("test/version/")
    Observable<Version> addVersion(@Body Version version);


    @POST("test/version/update/")
    @FormUrlEncoded
    Observable<Version> updateVersion(@Body Version version);



    @POST("test/whats_new/")
    Observable<SuccessResponse> addWhatsNew(@Body List<WhatsNew> whatsNewList);


    @POST("test/whats_new/update/")
    @FormUrlEncoded
    Observable<List<WhatsNew>> updateWhatsNew(@Body WhatsNew whatsNew);





}

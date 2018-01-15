package com.example.stas.rustal;

import com.example.stas.rustal.OrderModel.OrderListModel;
import com.example.stas.rustal.StatusModel.StatusModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface RestApi {

    @POST("delivery/rest/user/login/")
    @FormUrlEncoded
    Observable<LoginModel> login(
            @Field(("login")) String yes,
            @Field("email") String email,
            @Field("password") String pass);

    @GET("delivery/rest/order/list/{token}/")
    Call<List<OrderListModel>> getOrderList(@Path("token") String token);

    @GET("delivery/rest/order/{id}/{token}/")
    Observable<OrderListModel> getOrder(@Path("token") String token,
                                        @Path("id") String id);

    @Multipart
    @POST("delivery/rest/order/{id}/{token}/")
    Observable<ResponseBody> uploadImage(@Path("id") String id,
                                   @Path("token") String token,
                                   @Part MultipartBody.Part files);

    @Multipart
    @POST("delivery/rest/user/geolocation/{token}/")
    Call<ResponseBody> sendLocation(@Path("token") String token,
                                    @Part MultipartBody.Part latitude,
                                    @Part MultipartBody.Part longitude);

    @GET("delivery/rest/order/element/property/status/{token}/")
    Call<StatusModel> getPropertys(@Path("token") String token);

    @FormUrlEncoded
    @POST("delivery/rest/order/{id}/{token}/")
    Observable<ResponseBody> setStatus(@Path("id") String id,
                                 @Path("token") String token,
                                 @Field("status") String statusId);

}



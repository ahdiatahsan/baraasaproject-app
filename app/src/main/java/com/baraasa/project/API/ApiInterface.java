package com.baraasa.project.API;


import com.baraasa.project.Model.DataAbout;
import com.baraasa.project.Model.DataBlog;
import com.baraasa.project.Model.DataCertificate;
import com.baraasa.project.Model.DataEditForum;
import com.baraasa.project.Model.DataEvent;
import com.baraasa.project.Model.DataHapusForum;
import com.baraasa.project.Model.DataKomen;
import com.baraasa.project.Model.DataKomenList;
import com.baraasa.project.Model.DataPassword;
import com.baraasa.project.Model.DataProfileBlog;
import com.baraasa.project.Model.DataProfileThread;
import com.baraasa.project.Model.DataRecruitment;
import com.baraasa.project.Model.DataRecruitmentDaftar;
import com.baraasa.project.Model.DataResearch;
import com.baraasa.project.Model.DataSearch;
import com.baraasa.project.Model.DataTambahForum;
import com.baraasa.project.Model.DataThread;
import com.baraasa.project.Model.DataUpdate;
import com.baraasa.project.Response.LogOut;
import com.baraasa.project.Response.Login_response;
import com.baraasa.project.Response.X0;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<Login_response<X0>> login(@Field("email") String str,
                                   @Field("password") String str1);

    @POST("logout")
    Call<LogOut> logout(@Header("Authorization") String token);

    @GET("about")
    Call<DataAbout> aboutmodel(@Header("Authorization") String token);

    //BLOG
    @GET("dashboard/blogs")
    Call<DataBlog> blogmodel(@Header("Authorization") String token);

    @GET("profile/blogs")
    Call<DataProfileBlog> blogprofilemodel(@Header("Authorization") String token);

    @DELETE("blogAPI/{blogAPI}")
    Call<DataHapusForum> blogAPImodel(@Header("Authorization") String token,
                                      @Path(value = "blogAPI") String id_blog);

    //THREAD
    @GET("dashboard/threads")
    Call<DataThread> threadmodel(@Header("Authorization") String token);

    @GET("profile/threads")
    Call<DataProfileThread> threadprofilemodel(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("threadAPI")
    Call<DataTambahForum> tambahthreasmodel(@Header("Authorization") String token,
                                            @Field("title") String str1,
                                            @Field("body") String str2);

    @FormUrlEncoded
    @POST("threadAPI/{threadAPI}?_method=PATCH")
    Call<DataEditForum> editthreasmodel(@Header("Authorization") String token,
                                        @Path(value = "threadAPI") String id_forum,
                                        @Field("title") String str1,
                                        @Field("body") String str2);

    @DELETE("threadAPI/{threadAPI}")
    Call<DataHapusForum> hapusthreasmodel(@Header("Authorization") String token,
                                          @Path(value = "threadAPI") String id_forum);

    //KOMENTAR
    @FormUrlEncoded
    @POST("commentAPI")
    Call<DataKomen> komenmodel(@Header("Authorization") String token,
                               @Field("thread_id") String thread_id,
                               @Field("body") String str1);

    @GET("dashboard/comments/{commentAPI}")
    Call<DataKomenList> listkomenmodel(@Header("Authorization") String token,
                                       @Path(value = "commentAPI") String thread_id);

    @DELETE("commentAPI/{commentAPI}")
    Call<DataHapusForum> hapuscommentmodel(@Header("Authorization") String token,
                                           @Path(value = "commentAPI") String id_forum);

    //EVENT
    @GET("dashboard/events")
    Call<DataEvent> eventmodel(@Header("Authorization") String token);

    @Multipart
    @POST("participantAPI")
    Call<DataEvent> ikuteventmodel(@Header("Authorization") String token,
                                   @Part("event_id") RequestBody str1,
                                   @Part("reason") RequestBody str2,
                                   @Part MultipartBody.Part gambar);

    //RESEARCH
    @GET("dashboard/researches")
    Call<DataResearch> researchmodel(@Header("Authorization") String token);

    //RECRUITMENT
    @GET("dashboard/recruitment")
    Call<DataRecruitment> recruitmentmodel(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("recruitmentAPI")
    Call<DataRecruitmentDaftar> recruitmentdaftarmodel(@Header("Authorization") String token,
                                                       @Field("file") String str1);

    //CERTIFICATES
    @GET("profile/certificates")
    Call<DataCertificate> certificatemodel(@Header("Authorization") String token);

    //PROFILE
    @FormUrlEncoded
    @POST("profile/password")
    Call<DataPassword> passwordmodel(@Header("Authorization") String token,
                                     @Field("password") String str1,
                                     @Field("password_confirm") String str2);

    @Multipart
    @POST("profile/update")
    Call<DataUpdate> editProfile(@Header("Authorization") String token,
                                 @Part("name") RequestBody str,
                                 @Part("gender") RequestBody str1,
                                 @Part("birthplace") RequestBody str2,
                                 @Part("date_of_birth") RequestBody str3,
                                 @Part("address") RequestBody str4,
                                 @Part("phone_number") RequestBody str5,
                                 @Part("email") RequestBody str6,
                                 @Part MultipartBody.Part gambar);

    //REGISTER
    @Multipart
    @POST("register")
    Call<Login_response<X0>> regitermodel(@Part("name") RequestBody str,
                                          @Part("gender") RequestBody str1,
                                          @Part("birthplace") RequestBody str2,
                                          @Part("date_of_birth") RequestBody str3,
                                          @Part("address") RequestBody str4,
                                          @Part("phone_number") RequestBody str5,
                                          @Part("email") RequestBody str6,
                                          @Part("password") RequestBody str7,
                                          @Part("password_confirm") RequestBody str8,
                                          @Part MultipartBody.Part gambar);


    //SEARCH
    @FormUrlEncoded
    @POST("dashboard/search")
    Call<DataSearch> searchmodel(@Header("Authorization") String token,
                                 @Field("search") String str1);
}

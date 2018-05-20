package com.android.themoviedatabaseapi.network;

import com.android.themoviedatabaseapi.model.request.Request;
import com.android.themoviedatabaseapi.model.response.MovieDetails;
import com.android.themoviedatabaseapi.model.response.NowPlaying;
import com.android.themoviedatabaseapi.model.response.Response;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface iAppServices {

    /*@GET("now_playing")
    Call<Response> requestForLatestMovies(@Header("request") Request request);*/

    /*Call<Response> requestForLatestMovies(@Path("p1") String param1,
                                          @Path("p2") String param2,
                                          @Path("p3") String param3);*/
//    ?api_key={p1}&language={p2}&page={p3}
    @GET("movie/now_playing")
    Call<Response> requestForLatestMovies(@Query("api_key") String api_key,
                                          @Query("language") String language,
                                          @Query("page") String page,
                                          @Query("region") String region);

    @GET("movie/{movie_id}")
    Call<MovieDetails> requestForMovieDetails(@Path("movie_id") int movie_id,
                                              @Query("api_key") String api_key,
                                              @Query("language") String language,
                                              @Query("append_to_response") String append_to_response);
}

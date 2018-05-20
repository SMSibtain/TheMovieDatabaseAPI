/*package com.android.themoviedatabaseapi.network;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.android.themoviedatabaseapi.activities.MainActivity;
import com.android.themoviedatabaseapi.activities.MoviesActivity;
import com.android.themoviedatabaseapi.model.response.NowPlaying;
import com.android.themoviedatabaseapi.model.response.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

*//**//**
 * Created by sibta on 5/20/2018.
 *//**//*
public void getNowPlayingMovies2(View view) {
        progressDialogGettingMovies.show();

        NetworkCalls.nowPlayingMovie("1", "", new NetworkCalls.NetworkCallListener<ArrayList<NowPlaying>>() {
@Override
public void success(@Nullable ArrayList<NowPlaying> nowPlayings) {
        progressDialogGettingMovies.hide();
        if (nowPlayings != null) {
        Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
        intent.putExtra("mNowPlayingList", nowPlayings);
        startActivity(intent);
        } else {
        Toast.makeText(MainActivity.this, "Empty result", Toast.LENGTH_SHORT).show();
        }

        }

@Override
public void failure(String message) {
        if (progressDialogGettingMovies.isShowing())
        progressDialogGettingMovies.hide();
        createSimpleOkDialog(message);
        }
        });
        }*//*
public class NetworkCalls {

    public static void nowPlayingMovie(String page, String region, NetworkCallListener<ArrayList<NowPlaying>> callListener){
        Call<Response> responseCall = NetworkHandler.getInstance().getServices()
                .requestForLatestMovies(NetworkConstants.API_KEY,
                        NetworkConstants.LANGUAGE,
                        page, region);
        responseCall.enqueue(new Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Response body = response.body();
                    if (body != null) {
                        callListener.success(body.getResults());
                    } else {
                        callListener.success(null);
                    }
                } else {
                    callListener.failure(response.message());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                callListener.failure("Internet connection error");
            }
        });

    }

    public interface NetworkCallListener<T>{
        void success(@Nullable T t);
        void failure(String message);

    }
}
*/
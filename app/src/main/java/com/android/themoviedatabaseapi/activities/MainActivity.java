package com.android.themoviedatabaseapi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.android.themoviedatabaseapi.R;
import com.android.themoviedatabaseapi.databinding.ActivityMainBinding;
import com.android.themoviedatabaseapi.model.response.Response;
import com.android.themoviedatabaseapi.network.NetworkConstants;
import com.android.themoviedatabaseapi.network.NetworkHandler;
import com.android.themoviedatabaseapi.utils.DialogUtils;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProgressDialog progressDialogGettingMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        progressDialogGettingMovies = DialogUtils.createProgressDialog(MainActivity.this, "Getting movie collection");
    }

    public void getNowPlayingMovies(View view) {
        progressDialogGettingMovies.show();

        Call<Response> responseCall = NetworkHandler.getInstance().getServices()
                .requestForLatestMovies(NetworkConstants.API_KEY,
                        NetworkConstants.LANGUAGE,
                        "1", "");
        responseCall.enqueue(new Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                progressDialogGettingMovies.hide();
                if (response.isSuccessful()) {
                    Response body = response.body();
                    if (body != null) {
                        Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
                        intent.putExtra("mNowPlayingList", body.getResults());
                        intent.putExtra("mMaximumPages", body.getTotal_pages());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Empty result", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    createSimpleOkDialog(response.message());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                progressDialogGettingMovies.hide();
                createSimpleOkDialog(getString(R.string.connectionError));
            }
        });
    }



    public void createSimpleOkDialog(String mMessage) {
        DialogUtils.createSimpleOkDialog(MainActivity.this, "", mMessage,
                dialogInterface -> {
                    dialogInterface.dismiss();
                    dialogInterface.cancel();
                }).show();
    }
}

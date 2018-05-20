package com.android.themoviedatabaseapi.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.themoviedatabaseapi.R;
import com.android.themoviedatabaseapi.databinding.ActivityMovieDetailsBinding;
import com.android.themoviedatabaseapi.model.response.MovieDetails;
import com.bumptech.glide.Glide;

import static com.android.themoviedatabaseapi.network.NetworkConstants.IMAGE_URL;

public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding activityMovieDetailsBinding;
    MovieDetails movieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        if (getIntent().getSerializableExtra("movieDetails") != null) {
            movieDetails = (MovieDetails) getIntent().getSerializableExtra("movieDetails");
        }
        activityMovieDetailsBinding.setMovieDetails(movieDetails);
        setViews();
    }

    private void setViews() {
        Glide.with(this)
                .load(IMAGE_URL + movieDetails.getBackdrop_path())
                .placeholder(R.drawable.movie)
                .into(activityMovieDetailsBinding.imgViewMovie);

        if (movieDetails.getGenres() != null) {
            String strGenres = "";
            for (int i = 0; i < movieDetails.getGenres().size(); i++) {
                if (i == 0)
                    strGenres = strGenres.concat(movieDetails.getGenres().get(i).getName());
                else {
                    strGenres = strGenres.concat(", " + movieDetails.getGenres().get(i).getName());
                }
            }
            if (strGenres.length() > 0) {
                activityMovieDetailsBinding.txtViewGenre.setText(strGenres);
            }
        }

    }

    public void okButtonClicked(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
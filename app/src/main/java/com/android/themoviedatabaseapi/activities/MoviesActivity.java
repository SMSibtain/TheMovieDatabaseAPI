package com.android.themoviedatabaseapi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.themoviedatabaseapi.R;
import com.android.themoviedatabaseapi.adapter.NowPlayingListAdapter;
import com.android.themoviedatabaseapi.databinding.ActivityMoviesBinding;
import com.android.themoviedatabaseapi.model.response.MovieDetails;
import com.android.themoviedatabaseapi.model.response.NowPlaying;
import com.android.themoviedatabaseapi.network.NetworkConstants;
import com.android.themoviedatabaseapi.network.NetworkHandler;
import com.android.themoviedatabaseapi.utils.DatePickerHelper;
import com.android.themoviedatabaseapi.utils.DialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class MoviesActivity extends AppCompatActivity {

    private ActivityMoviesBinding mMoviesBinding;
    private EditText m_etxtFromDate;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<NowPlaying> mNowPlayingArrayList;
    private NowPlayingListAdapter nowPlayingListAdapter;
    private DatePickerHelper datePickerHelper;
    private ProgressDialog progressDialogMovieDetails, progressDialogGettingMovies;
    private int mMaximumPages, mMoviePage = 1;
    private boolean isFilterEnable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMaximumPages = Integer.parseInt(getIntent().getStringExtra("mMaximumPages"));

        mMoviesBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies);

        mMoviesBinding.includeViewFromDate.setEnabled(false);
        mMoviesBinding.includeViewFromDate.setAlpha(0.5f);
        m_etxtFromDate = mMoviesBinding.includeViewFromDate.findViewById(R.id.eTextCalendarDate);

        mLayoutManager = new LinearLayoutManager(this);
        mMoviesBinding.recViewMoviesCollection.setLayoutManager(mLayoutManager);
        mMoviesBinding.recViewMoviesCollection.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mMoviesBinding.recViewMoviesCollection.setHasFixedSize(true);
        mMoviesBinding.recViewMoviesCollection.setNestedScrollingEnabled(false);

        progressDialogGettingMovies = DialogUtils.createProgressDialog(MoviesActivity.this, "Getting movie collection");
        progressDialogMovieDetails = DialogUtils.createProgressDialog(MoviesActivity.this, "Getting movie details");

        setAdapters();
        setListeners();
    }

    private void setAdapters() {
        mNowPlayingArrayList = new ArrayList<>();
        mNowPlayingArrayList = (ArrayList<NowPlaying>) getIntent().getSerializableExtra("mNowPlayingList");
        nowPlayingListAdapter = new NowPlayingListAdapter(mNowPlayingArrayList);
        mMoviesBinding.recViewMoviesCollection.setAdapter(nowPlayingListAdapter);


        nowPlayingListAdapter.setItemSelectedListener((NowPlaying nowPlaying) -> {
            Call<MovieDetails> movieDetailsCall = NetworkHandler.getInstance().getServices()
                    .requestForMovieDetails(nowPlaying.getId(),
                            NetworkConstants.API_KEY,
                            NetworkConstants.LANGUAGE,
                            "");

            progressDialogMovieDetails.show();
            movieDetailsCall.enqueue(new Callback<MovieDetails>() {
                @Override
                public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                    progressDialogMovieDetails.hide();
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(MoviesActivity.this, MovieDetailsActivity.class);
                        intent.putExtra("movieDetails", response.body());
                        startActivity(intent);
                    } else {
                        createSimpleOkDialog(response.message());
                    }
                }

                @Override
                public void onFailure(Call<MovieDetails> call, Throwable t) {
                    progressDialogMovieDetails.hide();
                    createSimpleOkDialog(getString(R.string.connectionError));
                }
            });
        });
    }

    private void setListeners() {
        mMoviesBinding.includeViewFromDate.setOnClickListener(view -> {
            m_etxtFromDate.setError(null);
            m_etxtFromDate.clearFocus();
            datePickerHelper = new DatePickerHelper(MoviesActivity.this, m_etxtFromDate);
            datePickerHelper.showDialog();
        });

        mMoviesBinding.recViewMoviesCollection.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 && !isFilterEnable) {
                    // Recycle view scrolling down...
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        progressDialogGettingMovies.show();

                        if (mMoviePage == mMaximumPages) {
                            Toast.makeText(MoviesActivity.this, "Maximum movies fetched", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Call<com.android.themoviedatabaseapi.model.response.Response> responseCall = NetworkHandler.getInstance().getServices()
                                .requestForLatestMovies(NetworkConstants.API_KEY,
                                        NetworkConstants.LANGUAGE,
                                        String.valueOf(++mMoviePage), "");
                        responseCall.enqueue(new Callback<com.android.themoviedatabaseapi.model.response.Response>() {
                            @Override
                            public void onResponse(Call<com.android.themoviedatabaseapi.model.response.Response> call, retrofit2.Response<com.android.themoviedatabaseapi.model.response.Response> response) {
                                com.android.themoviedatabaseapi.model.response.Response body = response.body();
                                progressDialogGettingMovies.hide();
                                if (response.isSuccessful()) {
                                    assert body != null;
                                    if (body.getResults() != null) {
                                        mNowPlayingArrayList.addAll(response.body().getResults());
                                        nowPlayingListAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    createSimpleOkDialog(response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<com.android.themoviedatabaseapi.model.response.Response> call, Throwable t) {
                                progressDialogGettingMovies.hide();
                                createSimpleOkDialog(getString(R.string.connectionError));
                            }
                        });
                    }

                }
            }
        });


        mMoviesBinding.switchCompatFilter.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                mMoviesBinding.includeViewFromDate.setAlpha(1.0f);
                mMoviesBinding.includeViewFromDate.setEnabled(true);
                m_etxtFromDate.setText("");
            } else {
                m_etxtFromDate.setText("");
                mMoviesBinding.includeViewFromDate.setEnabled(false);
                mMoviesBinding.includeViewFromDate.setAlpha(0.5f);
            }
        });

        m_etxtFromDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isFilterEnable = m_etxtFromDate.getText().toString().length() != 0;
                if (mNowPlayingArrayList.size() > 0) {
                    nowPlayingListAdapter.getFilter().filter((charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void createSimpleOkDialog(String mMessage) {
        DialogUtils.createSimpleOkDialog(MoviesActivity.this, "", mMessage,
                dialogInterface -> {
                    dialogInterface.dismiss();
                    dialogInterface.cancel();
                }).show();
    }
}

package com.android.themoviedatabaseapi.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.android.themoviedatabaseapi.R;
import com.android.themoviedatabaseapi.databinding.RecyclerMovieItemBinding;
import com.android.themoviedatabaseapi.model.response.NowPlaying;

import java.util.ArrayList;

public class NowPlayingListAdapter extends RecyclerView.Adapter<NowPlayingListAdapter.NowPlayingViewHolder> implements Filterable {

    private ArrayList<NowPlaying> mNowPlayingArrayList = new ArrayList<>();
    private ArrayList<NowPlaying> mNowPlayingArrayListFiltered = new ArrayList<>();

    @Nullable
    private ItemSelectedListener itemSelectedListener;

    public NowPlayingListAdapter(ArrayList<NowPlaying> mNowPlayingArrayList) {
        this.mNowPlayingArrayList = mNowPlayingArrayList;
        this.mNowPlayingArrayListFiltered = mNowPlayingArrayList;
    }

    @Override
    public NowPlayingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerMovieItemBinding binding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.recycler_movie_item, parent, false);
        return new NowPlayingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NowPlayingViewHolder holder, int position) {
        NowPlaying nowPlayingModel = mNowPlayingArrayListFiltered.get(position);
        holder.bindData(position, nowPlayingModel);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty() || charString.equals("")) {
                    mNowPlayingArrayListFiltered = mNowPlayingArrayList;
                } else {
                    ArrayList<NowPlaying> filteredList = new ArrayList<>();

                    for (NowPlaying nowPlaying : mNowPlayingArrayList) {
                        if (nowPlaying.getRelease_date().equals(charString))
                            filteredList.add(nowPlaying);
                    }
                    mNowPlayingArrayListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mNowPlayingArrayListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mNowPlayingArrayListFiltered = (ArrayList<NowPlaying>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class NowPlayingViewHolder extends RecyclerView.ViewHolder {

        private RecyclerMovieItemBinding binding;
        private int position;
        private NowPlaying nowPlayingModel;

        NowPlayingViewHolder(RecyclerMovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.recyclerViewMovieItem.setOnClickListener(view -> {
                if (itemSelectedListener != null) {
                    itemSelectedListener.onItemClicked(nowPlayingModel);
                }
            });
        }

        void bindData(int position, NowPlaying nowPlayingModel) {
            this.position = position;
            this.nowPlayingModel = nowPlayingModel;
            Context context = binding.recyclerViewMovieItem.getContext();
            if (position % 2 == 0) {
                binding.recyclerViewMovieItem.setBackgroundColor(context.getResources().getColor(R.color.screenBackground));
            } else {
                binding.recyclerViewMovieItem.setBackgroundColor(context.getResources().getColor(R.color.white));
            }

            binding.setNowPlaying(nowPlayingModel);
            /*binding.txtMovieTitle.setText(nowPlayingModel.getTitle());
            binding.txtReleaseDate.setText(nowPlayingModel.getRelease_date());
            binding.txtVoteAverage.setText(String.valueOf(nowPlayingModel.getVote_average()));*/
        }
    }

    @Override
    public int getItemCount() {
        return mNowPlayingArrayListFiltered.size();
    }

    public void setItemSelectedListener(@Nullable ItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    public interface ItemSelectedListener {
        void onItemClicked(NowPlaying nowPlaying);

    }
}

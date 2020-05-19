package com.example.parcial_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterView extends RecyclerView.Adapter<AdapterView.ViewHolder> {

    private List<Song> Songs;
    private int Item;
    //private Context context;

    public AdapterView(List<Song> songs , int item) {
        this.Songs = songs;
        this.Item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(Item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = Songs.get(position);
        holder.name.setText(song.getTitle());
        holder.artist.setText(song.getArtist().getName());
        holder.duration.setText(song.getDuration());

    }

    @Override
    public int getItemCount() {
        return Songs.size();
    }

    public void AddSonsList(ArrayList<Song> songs){
        songs.addAll(songs);
        notifyDataSetChanged();
    }

    public void AddSongToPlayList(Song song){
        Songs.add(song);
        notifyDataSetChanged();


    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView artist;
        private TextView duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.txt_name);
            artist = (TextView) itemView.findViewById(R.id.txt_artist);
            duration = (TextView) itemView.findViewById(R.id.txt_duration);
        }
    }
}

package com.example.parcial_api;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private  AdapterView songAdapter;
    private int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Canciones");

        retrofit = new Retrofit.Builder().baseUrl("https://api.deezer.com/playlist/93489551/").addConverterFactory(GsonConverterFactory.create()).build();

        GetSongs();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.MagnamentIntent);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManagementActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
            }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle songRecived = data.getExtras();
        Song song;
        Toast.makeText(getApplicationContext(),"Cancion añadida",Toast.LENGTH_SHORT).show();
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                song = (Song)songRecived.getSerializable("data");
                if (song !=null){
                    songAdapter.AddSongToPlayList(song);
                    Log.e("TAG","Name: " + song.getTitle() + "Artista: " + song.getArtist().getName());
                }
            }
        }
    }

    public  void GetSongs(){
        DeezerApi serviceApi = retrofit.create(DeezerApi.class);
        Call<SongResponse> callResponse = serviceApi.getSong();

        callResponse.enqueue(new Callback<SongResponse>() {
            @Override
            public void onResponse(Call<SongResponse> call, Response<SongResponse> response) {
                if(response.isSuccessful())
                {
                    SongResponse songResponse = response.body();
                    ArrayList<Song> Songs = songResponse.getData();
                    DisplayRecycler(Songs);
                }
                else {
                    Log.i("TAG","onResponse: "+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SongResponse> call, Throwable t) {
                Log.e("TAG","onResponse: " + t.getMessage());
            }
        });
    }

    public void DisplayRecycler(ArrayList<Song> songs){

        recyclerView = (RecyclerView)findViewById(R.id.recyclerApi);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        songAdapter = new AdapterView(songs,R.layout.item_adapter);
        recyclerView.setAdapter(songAdapter);

    }
}

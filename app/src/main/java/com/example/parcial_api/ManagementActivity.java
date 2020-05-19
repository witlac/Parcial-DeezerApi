package com.example.parcial_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManagementActivity extends AppCompatActivity {

    private Retrofit retrofit;
    Button btn_addplaylist;
    EditText name,search,artist,duration;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management2);
        setTitle("Añadir cancion");

        retrofit = new Retrofit.Builder().baseUrl("https://api.deezer.com/").addConverterFactory(GsonConverterFactory.create()).build();

        name = (EditText) findViewById(R.id.editText2);
        artist =(EditText)findViewById(R.id.editText3);
        search =(EditText)findViewById(R.id.ed_searh);
        duration=(EditText)findViewById(R.id.editText4);
        btn_addplaylist=(Button)findViewById(R.id.btn_addplaylist);

        btn_addplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Artist artistSend = new Artist(artist.getText().toString());
                song = new Song(name.getText().toString(),artistSend,duration.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putSerializable("data",song);

                Intent intent = new Intent(ManagementActivity.this,MainActivity.class);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    public void GetSong(View view){
        DeezerApi serviceApi = retrofit.create(DeezerApi.class);
        String query = search.getText().toString();
        Call<SongResponse> callResponse = serviceApi.SearchSong(query);

        callResponse.enqueue(new Callback<SongResponse>() {
            @Override
            public void onResponse(Call<SongResponse> call, Response<SongResponse> response) {
                if(response.isSuccessful())
                {
                    SongResponse songResponse = response.body();
                    ArrayList<Song> Songs = songResponse.getData();
                    Song song = Songs.get(0);

                    name.setText(song.getTitle());
                    artist.setText(song.getArtist().getName());
                    duration.setText(song.getDuration());
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




}

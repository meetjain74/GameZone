package com.example.gamezone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.gamezone.data.Game;
import com.example.gamezone.data.GameCustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Game> games = new ArrayList<>();
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getJson();

        recyclerView = findViewById(R.id.gameList);
        GameCustomAdapter ad = new GameCustomAdapter(this,games);
        ad.setOnItemClickListener(new GameCustomAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClickEvent(int position) {
                url = games.get(position).getGameUrl();
                Intent intent=new Intent(MainActivity.this,PlayGame.class);
                intent.putExtra("GameURL",url);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ad);
    }

    public void getJson()
    {
        String json;
        try {
            InputStream inputStream = getAssets().open("gamesData.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer,"UTF-8");
            JSONArray result = new JSONArray(json);
            for (int i=0;i< result.length();i++)
            {
                JSONObject object = result.getJSONObject(i);
                Game game = new Game();
                game.setName(object.getString("name"));
                game.setRating(object.getDouble("rating"));
                game.setImageUrl(object.getString("imageUrl"));
                game.setGameUrl(object.getString("gameUrl"));
                JSONArray keys = object.getJSONArray("keywords");
                List<String> keywords = new ArrayList<>();
                for (int j=0;j< keys.length();j++)
                    keywords.add(keys.getString(j));
                game.setKeyWords(keywords);

                games.add(game);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
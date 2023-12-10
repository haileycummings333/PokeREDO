package com.example.pokewatchlistredo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPokemon;
    private Button buttonAddPokemon, clearProfButton, clearListButton;
    private ListView pokeList;
    private ImageView imageView;
    private TextView PokeNameText, pokedexIDText, weightText, heightText, baseXPText, moveText, abilityText;
    private ArrayList<Pokemon> watchlist;
    private PokemonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize stuff from xml
        editTextPokemon = findViewById(R.id.editTextPokemon);
        buttonAddPokemon = findViewById(R.id.buttonAddPokemon);
        clearProfButton = findViewById(R.id.clearProfButton);
        clearListButton = findViewById(R.id.clearListButton);
        pokeList = findViewById(R.id.pokeList);
        imageView = findViewById(R.id.imageView);

        PokeNameText = findViewById(R.id.PokeNameText);
        pokedexIDText = findViewById(R.id.pokedexIDText);
        weightText = findViewById(R.id.weightText);
        heightText = findViewById(R.id.heightText);
        baseXPText = findViewById(R.id.baseXPText);
        moveText = findViewById(R.id.moveText);
        abilityText = findViewById(R.id.abilityText);


        watchlist = new ArrayList<>();
        adapter = new PokemonListAdapter(this, watchlist);
        pokeList.setAdapter(adapter);

        //button listeners
        buttonAddPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPokemonToWatchlist();
            }
        });

        clearProfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCurrentPokemon();
            }
        });

        clearListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllPokemon();
            }
        });
    }

    private void addPokemonToWatchlist() {
        //adding pokemon to the watchlist
    }

    private void clearCurrentPokemon() {
        // clearing the current Pokemon profile
    }

    private void clearAllPokemon() {
        //clearing all Pokemon from the watchlist
    }

    // need to add handling the api requests

}

package com.example.pokewatchlistredo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class PokemonListAdapter extends ArrayAdapter<Pokemon> {

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        super(context, 0, pokemonList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Pokemon pokemon = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, parent, false);
        }

        TextView pokemonNameTextView = convertView.findViewById(R.id.PokeNameText);
        TextView pokedexIdTextView = convertView.findViewById(R.id.pokedexIDText);

        // Set the data to the views
        if (pokemon != null) {
            pokemonNameTextView.setText(pokemon.getName());
            pokedexIdTextView.setText(String.valueOf(pokemon.getPokedexId()));
        }

        return convertView;
    }
}

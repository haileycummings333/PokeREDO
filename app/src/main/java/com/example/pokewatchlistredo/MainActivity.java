package com.example.pokewatchlistredo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPokemon;
    private Button buttonAddPokemon, clearProfButton, clearListButton;
    private ListView pokeList;
    private ImageView imageView;
    private TextView PokeNameText, pokedexIDText, weightText, heightText, baseXPText, moveText, abilityText;
    private ArrayList<Pokemon> watchlist;
    private List<String> watchlistNames;
    private ArrayAdapter<String> watchlistAdapter;
    private RequestQueue requestQueue;

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

        //button listeners
        buttonAddPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonNameOrId = editTextPokemon.getText().toString().trim();
                if (isValidName(pokemonNameOrId)) {
                    // valid Pokemon name, proceed with adding to the watchlist and fetching data
                    fetchPokemonData(pokemonNameOrId);
                } else {
                    // notify user of invalid Pokemon name
                    Toast.makeText(MainActivity.this, "Invalid Pokemon name", Toast.LENGTH_SHORT).show();
                }
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

        requestQueue = Volley.newRequestQueue(this);


        watchlistNames = new ArrayList<>();
        watchlistAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, watchlistNames);


        pokeList.setAdapter(watchlistAdapter);


        pokeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pokemon selectedPokemon = watchlist.get(position);
                fetchPokemonData(selectedPokemon.getName());
            }
        });

        //try values
        fetchPokemonData("pikachu"); //should work with just name
        fetchPokemonData("1000"); //should work with just pokeid
        fetchPokemonData("eon4$$"); //should be invalid
        fetchPokemonData("charmander1000");//should be invalid
        fetchPokemonData("1000charmander");//should be invalid
    }

    private void addPokemonToWatchlist(Pokemon pokemon) {
        if(!watchlist.contains(pokemon)){
            watchlist.add(pokemon);
            watchlistNames.add(pokemon.getName() + " - " + pokemon.getPokedexId());
            watchlistAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Added to watchlist: " + pokemon.getName(), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Pokemon already in list.", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isValidName(String name) {
        // check if the name contains invalid characters
        if (name.matches(".*[%&*(@)!;:<>].*")) {
            return false;
        }
        // check if the name is a valid number
        try {
            int numericValue = Integer.parseInt(name);
            // check if the numeric value is negative or greater than 1010
            if (numericValue < 0 || numericValue > 1010) {
                return false;
            }
        } catch (NumberFormatException e) {
            // the name is not a valid numeric value
        }
        // the name is valid
        return true;
    }

    private void clearCurrentPokemon() {
        // reset data fields to default entry
        PokeNameText.setText(getString(R.string.name));
        pokedexIDText.setText(getString(R.string.id));
        weightText.setText(getString(R.string.weightplain));
        heightText.setText(getString(R.string.heightplain));
        baseXPText.setText(getString(R.string.xp));
        moveText.setText(getString(R.string.moveplain));
        abilityText.setText(getString(R.string.abilityplain));
        // clear the image view
        imageView.setImageDrawable(null);
        // set the search bar to an empty string
        editTextPokemon.setText("");
    }

    private void clearAllPokemon() {
        if(!watchlist.isEmpty()){
            watchlist.clear();
            watchlistAdapter.notifyDataSetChanged();
            clearCurrentPokemon();}
        else{
            Toast.makeText(MainActivity.this, "List is already empty.", Toast.LENGTH_SHORT).show();
        }
    }


    private void fetchPokemonData(String pokemonNameOrId) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonNameOrId.toLowerCase();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parsePokemonData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error fetching Pokemon data", Toast.LENGTH_SHORT).show();
                    }
                });

        // add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
    private String getPokemonImageUrl(int pokemonId) {
        String baseUrl = "https://github.com/HybridShivam/Pokemon/blob/master/assets/images/";

        // construct the URL based id number
        if (pokemonId < 10) {
            return baseUrl + "00" + pokemonId + ".png?raw=true";
        } else if (pokemonId < 100) {
            return baseUrl + "0" + pokemonId + ".png?raw=true";
        } else {
            return baseUrl + pokemonId + ".png?raw=true";
        }
    }

    private void parsePokemonData(JSONObject response) {
        try {
            String name = response.getString("name");
            int pokedexId = response.getInt("id");
            double weight = response.getDouble("weight");
            double height = response.getDouble("height");
            int baseXP = response.getInt("base_experience");

            // get the first move and ability
            String move = response.getJSONArray("moves").getJSONObject(0).getJSONObject("move").getString("name");
            String ability = response.getJSONArray("abilities").getJSONObject(0).getJSONObject("ability").getString("name");

            //set textviews with data
            PokeNameText.setText(name);
            pokedexIDText.setText(String.valueOf(pokedexId));
            weightText.setText(String.valueOf(weight));
            heightText.setText(String.valueOf(height));
            baseXPText.setText(String.valueOf(baseXP));
            moveText.setText(move);
            abilityText.setText(ability);

            String imageUrl = getPokemonImageUrl(pokedexId);
            Picasso.get().load(imageUrl).into(imageView);

            Pokemon p = new Pokemon(name, pokedexId, weight, height, baseXP, move, ability);
            addPokemonToWatchlist(p);
            // Optionally, load the Pokemon image using an image loading library like Picasso or Glide
            // You can set the image URL using response.getJSONObject("sprites").getString("front_default")

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

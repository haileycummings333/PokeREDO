package com.example.pokewatchlistredo;
public class Pokemon {
    private String name;
    private int pokedexId;
    private double weight;
    private double height;
    private int baseXP;
    private String move;
    private String ability;
    // You may need additional fields based on the PokeAPI response

    // Constructors, getters, and setters

    // Example constructor
    public Pokemon(String name, int pokedexId, double weight, double height, int baseXP, String move, String ability) {
        this.name = name;
        this.pokedexId = pokedexId;
        this.weight = weight;
        this.height = height;
        this.baseXP = baseXP;
        this.move = move;
        this.ability = ability;
    }

    // Getters and setters for other fields
}

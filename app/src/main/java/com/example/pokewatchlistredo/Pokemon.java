package com.example.pokewatchlistredo;
public class Pokemon {
    private String name;
    private int pokedexId;
    private double weight;
    private double height;
    private int baseXP;
    private String move;
    private String ability;

    public Pokemon(String name, int pokedexId, double weight, double height, int baseXP, String move, String ability) {
        this.name = name;
        this.pokedexId = pokedexId;
        this.weight = weight;
        this.height = height;
        this.baseXP = baseXP;
        this.move = move;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }
    public int getPokedexId() {
        return pokedexId;
    }
    public double getWeight() {
        return weight;
    }
    public double getHeight() {
        return height;
    }
    public int getBaseXP() {
        return baseXP;
    }
    public String getMove() {
        return move;
    }
    public String getAbility() {
        return ability;
    }



}

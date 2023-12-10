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

    public void setName(String name) {
        this.name = name;
    }

    public int getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(int pokedexId) {
        this.pokedexId = pokedexId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getBaseXP() {
        return baseXP;
    }

    public void setBaseXP(int baseXP) {
        this.baseXP = baseXP;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

}

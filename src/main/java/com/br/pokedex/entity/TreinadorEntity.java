package com.br.pokedex.entity;

import java.util.List;

public class TreinadorEntity
{
    private int id;
    private String name;
    private List<PokemonEntity> pokemons;
    private List<ItensObject> itens;

    public TreinadorEntity(int id, String name, List<PokemonEntity> pokemons, List<ItensObject> itens) {
        this.id = id;
        this.name = name;
        this.pokemons = pokemons;
        this.itens = itens;
    }

    public TreinadorEntity(String name, List<PokemonEntity> pokemons, List<ItensObject> itens) {
        this.name = name;
        this.pokemons = pokemons;
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonEntity> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonEntity> pokemons) {
        this.pokemons = pokemons;
    }

    public List<ItensObject> getItens() {
        return itens;
    }

    public void setItens(List<ItensObject> itens) {
        this.itens = itens;
    }

}

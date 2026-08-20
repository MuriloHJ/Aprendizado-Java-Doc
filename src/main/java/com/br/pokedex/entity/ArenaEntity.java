package com.br.pokedex.entity;

import com.br.pokedex.entity.enums.Lider;
import com.br.pokedex.entity.enums.Type;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ArenaEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Type type;
    private Lider lider;
    @OneToMany
    private List<PokemonEntity> pokemons;

    public ArenaEntity(int id, String name, Type type, Lider lider, List<PokemonEntity> pokemons)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.lider = lider;
        this.pokemons = pokemons;
    }

    public ArenaEntity(String name, Type type, Lider lider, List<PokemonEntity> pokemons) {
        this.name = name;
        this.type = type;
        this.lider = lider;
        this.pokemons = pokemons;
    }

    public ArenaEntity() {

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Lider getLider() {
        return lider;
    }

    public void setLider(Lider lider) {
        this.lider = lider;
    }

    public List<PokemonEntity> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonEntity> pokemons) {
        this.pokemons = pokemons;
    }
}
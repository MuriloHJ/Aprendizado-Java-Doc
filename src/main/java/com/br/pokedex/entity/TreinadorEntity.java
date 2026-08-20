package com.br.pokedex.entity;

import com.br.pokedex.entity.enums.Lider;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TreinadorEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany
    private List<PokemonEntity> pokemons;
    @OneToMany
    private List<ItensObject> itens;

    @ElementCollection
    private List<Lider> insignias = new ArrayList<>();

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

    public TreinadorEntity()
    {
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

    public List<Lider> getInsignias() {
        return insignias;
    }

    public void setInsignias(List<Lider> insignias) {
        this.insignias = insignias;
    }

    public void andar()
    {
        System.out.println("O pokemon andou.");
    }

    public void adicionarInsignia(Lider lider)
    {
        if(this.insignias == null)
        {
            this.insignias = new ArrayList<>();
        }

        if(!this.insignias.contains(lider))
        {
            this.insignias.add(lider);
        }
    }

    public TreinadorEntity verificarTreinador(TreinadorEntity treinador)
    {
        return treinador;
    }
}

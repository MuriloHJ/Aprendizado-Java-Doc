package com.br.pokedex.service;

import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.entity.TreinadorEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TreinadorService implements com.br.pokedex.interfaces.TreinadorService
{
    public TreinadorService()
    {

    }

    @Override
    public void usarItem(TreinadorEntity treinador)
    {

    }

    @Override
    public void realizarCompra(TreinadorEntity treinador)
    {

    }

    @Override
    public void desfiarLider(TreinadorEntity treinador)
    {

    }

    @Override
    public void curarPokemon(TreinadorEntity treinador)
    {

    }

    @Override
    public void batalhar(TreinadorEntity treinador)
    {

    }

    @Override
    public TreinadorEntity capturar(TreinadorEntity treinador, List<PokemonEntity>pokemons)
    {
        Random random = new Random();

        int indice = random.nextInt(pokemons.size());


        PokemonEntity pokemonPego = pokemons.get(indice);
        List<PokemonEntity> pokemonsPego = new ArrayList<>();

        pokemonsPego.add(pokemonPego);
        treinador.setPokemons(pokemonsPego);

        return treinador;
    }

    @Override
    public TreinadorEntity updateNome(TreinadorEntity treinador, String name)
    {
        treinador.setName(name);
        System.out.println("Nome do treinador alterado!!!!!!");
        return treinador;
    }
}

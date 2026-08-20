package com.br.pokedex.service;

import com.br.pokedex.entity.ArenaEntity;
import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.entity.TreinadorEntity;
import com.br.pokedex.excpetions.InvalidInputExcpetion;

import java.util.List;

public class ArenaService implements com.br.pokedex.interfaces.ArenaService
{
    @Override
    public String entregarIsignia(ArenaEntity arena, TreinadorEntity treinador)
    {
        if(arena == null)
        {
            throw new InvalidInputExcpetion("Erro!!! Arena não pode ser nula");
        }

        if(treinador == null)
        {
            throw new InvalidInputExcpetion("Erro!!! Treinador não pode ser nulo");
        }

        treinador.adicionarInsignia(arena.getLider());

        System.out.println("Insígnia da arena "+arena.getName()+" entregue ao treinador "+treinador.getName()+"!");

        return "O treinador "+treinador.getName()+" recebeu a insígnia de "+arena.getLider().name()+"!";
    }

    @Override
    public List<PokemonEntity> anunciarPokemon(ArenaEntity arena)
    {
        if(arena == null || arena.getPokemons() == null)
        {
            throw new InvalidInputExcpetion("Erro!!! Arena inválida ou sem pokemons cadastrados");
        }

        System.out.println("Os pokemons da arena "+arena.getName()+" são:");

        for (PokemonEntity pokemon : arena.getPokemons())
        {
            System.out.println("- "+pokemon.getName()+" (nível "+pokemon.getLevel()+", tipo "+pokemon.getType()+")");
        }

        return arena.getPokemons();
    }
}

package com.br.pokedex.service;

import com.br.pokedex.entity.ItensObject;
import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.excpetions.InvalidInputExcpetion;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class PokemonService implements com.br.pokedex.interfaces.PokemonService
{
    private final PokemonService pokemonService = new PokemonService();
    public PokemonService(){}

   @Override
    public String mudarEstado(ItensObject item, PokemonEntity pokemon)
    {
        PokemonEntity pokemonValidate = pokemon.validarPokemon(pokemon);

        PokemonEntity.State stateOld = pokemonValidate.getState();
        pokemon.usarItem(item);
        return "O pokemon "+pokemonValidate.getName()+" estava "+stateOld+" e agora ele esta"+pokemonValidate.getState().name();
    }



    @Override
    public String evoluir(PokemonEntity pokemon)
    {
       PokemonEntity pokemonValidate =  pokemon.validarPokemon(pokemon);
       pokemonValidate.setStage(pokemonValidate.getStage() == 3 ? pokemonValidate.getStage() : pokemonValidate.getStage() + 1);
       return "O pokemon agora está no estágio: " + pokemonValidate.getStage();
    }
}

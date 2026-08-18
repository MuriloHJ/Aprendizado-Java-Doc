package com.br.pokedex.service;

import com.br.pokedex.entity.ItensObject;
import com.br.pokedex.entity.PokemonEntity;

import java.util.Optional;

public class PokemonService implements com.br.pokedex.interfaces.PokemonService
{

   @Override
    public String mudarEstado(ItensObject item, PokemonEntity pokemon)
    {
        PokemonEntity.State stateOld = pokemon.getState();
        pokemon.usarItem(item);

        return "O pokemon "+pokemon.getName()+" estava "+stateOld+" e agora ele esta"+pokemon.getState().name();
    }



    @Override
    public String evoluir(PokemonEntity pokemon, Optional<ItensObject> item)
    {
       int stageOld =  pokemon.getStage();

       if(!item.isEmpty())
       {
           pokemon.usarItem(item.get());
       }

        pokemon.setStage(pokemon.getStage() == 3 ? pokemon.getStage() : pokemon.getStage() + 1);

       return "O pokemon agora está no estágio: " + pokemon.getStage();
    }
}

package com.br.pokedex.interfaces;

import com.br.pokedex.entity.ArenaEntity;
import com.br.pokedex.entity.PokemonEntity;

import java.util.List;

public interface ArenaService
{
    void entregarIsignia();
    List<PokemonEntity> anunciarPokemon(ArenaEntity arena);
}

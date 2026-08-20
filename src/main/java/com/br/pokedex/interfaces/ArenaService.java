package com.br.pokedex.interfaces;

import com.br.pokedex.entity.ArenaEntity;
import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.entity.TreinadorEntity;

import java.util.List;

public interface ArenaService
{
    String entregarIsignia(ArenaEntity arena, TreinadorEntity treinador);
    List<PokemonEntity> anunciarPokemon(ArenaEntity arena);
}

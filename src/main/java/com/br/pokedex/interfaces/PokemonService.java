package com.br.pokedex.interfaces;

import com.br.pokedex.entity.ItensObject;
import com.br.pokedex.entity.PokemonEntity;

import java.util.Optional;

public interface PokemonService
{
    String mudarEstado(Optional<ItensObject> item, Optional<PokemonEntity> pokemon);
    String evoluir(Optional<PokemonEntity> pokemon);
}

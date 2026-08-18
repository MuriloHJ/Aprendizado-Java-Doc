package com.br.pokedex.interfaces;

import com.br.pokedex.entity.ItensObject;
import com.br.pokedex.entity.PokemonEntity;

import java.util.Optional;

public interface PokemonService
{
    String mudarEstado(ItensObject item, PokemonEntity pokemon);
    String evoluir(PokemonEntity pokemon,Optional<ItensObject> item);
}

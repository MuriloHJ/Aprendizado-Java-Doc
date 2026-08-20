package com.br.pokedex.interfaces;

import com.br.pokedex.entity.ItensObject;
import com.br.pokedex.entity.PokemonEntity;

import java.util.Optional;

public interface PokemonService
{
    String mudarEstado(ItensObject item, PokemonEntity pokemon);
    String evoluir(PokemonEntity pokemon);
    String subirNivel(PokemonEntity pokemon);
    String atacar(PokemonEntity atacante, PokemonEntity alvo);
    String esquivar(PokemonEntity pokemon, String nomeAtacante);
    String fugir(PokemonEntity pokemon);
    String movimentar(PokemonEntity pokemon);
}

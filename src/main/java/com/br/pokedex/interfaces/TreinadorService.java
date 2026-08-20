package com.br.pokedex.interfaces;

import com.br.pokedex.entity.ArenaEntity;
import com.br.pokedex.entity.ItensObject;
import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.entity.TreinadorEntity;

import java.util.List;
import java.util.Optional;


public interface TreinadorService
{
    String moverPeloMapa(TreinadorEntity treinador);
    String usarItem(TreinadorEntity treinador, ItensObject item, PokemonEntity pokemon);
    TreinadorEntity realizarCompra(TreinadorEntity treinador, ItensObject item);
    String desafiarLider(TreinadorEntity treinador, ArenaEntity arena);
    String batalhar(TreinadorEntity treinador, PokemonEntity pokemonAdversario);
    TreinadorEntity capturar(TreinadorEntity treinador, List<PokemonEntity> pokemon);
    TreinadorEntity adicionarPokemonAoTime(TreinadorEntity treinador, PokemonEntity pokemon);
    String curarPokemons(TreinadorEntity treinador);
    TreinadorEntity updateNome(TreinadorEntity treinador,String nome);
}

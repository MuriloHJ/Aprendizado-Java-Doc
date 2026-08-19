package com.br.pokedex.interfaces;

import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.entity.TreinadorEntity;

import java.util.List;
import java.util.Optional;


public interface TreinadorService
{
    void usarItem(TreinadorEntity treinador);
    void realizarCompra(TreinadorEntity treinador);
    void desfiarLider(TreinadorEntity treinador);
    void curarPokemon(TreinadorEntity treinador);
    void batalhar(TreinadorEntity treinador);
    TreinadorEntity capturar(TreinadorEntity treinador, List<PokemonEntity> pokemon);
    TreinadorEntity updateNome(TreinadorEntity treinador,String nome);
}

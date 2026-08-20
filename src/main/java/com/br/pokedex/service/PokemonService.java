package com.br.pokedex.service;

import com.br.pokedex.entity.ItensObject;
import com.br.pokedex.entity.PokemonEntity;

public class PokemonService implements com.br.pokedex.interfaces.PokemonService
{
    public PokemonService(){}

    @Override
    public String mudarEstado(ItensObject item, PokemonEntity pokemon)
    {
        PokemonEntity pokemonValidate = pokemon.validarPokemon(pokemon);

        PokemonEntity.State stateOld = pokemonValidate.getState();
        pokemon.usarItem(item);
        return "O pokemon "+pokemonValidate.getName()+" estava "+stateOld+" e agora ele está "+pokemonValidate.getState().name();
    }

    @Override
    public String evoluir(PokemonEntity pokemon)
    {
        PokemonEntity pokemonValidate = pokemon.validarPokemon(pokemon);
        pokemonValidate.setStage(pokemonValidate.getStage() == 3 ? pokemonValidate.getStage() : pokemonValidate.getStage() + 1);

        pokemonValidate.setMaxPs(pokemonValidate.getMaxPs() + 10);
        pokemonValidate.setActualPs(pokemonValidate.getActualPs() + 10);

        return "O pokemon "+pokemonValidate.getName()+" evoluiu e agora está no estágio: " + pokemonValidate.getStage();
    }

    @Override
    public String subirNivel(PokemonEntity pokemon)
    {
        PokemonEntity pokemonValidate = pokemon.validarPokemon(pokemon);
        int nivelAntigo = pokemonValidate.getLevel();

        pokemonValidate.subirNivel();

        return "O pokemon "+pokemonValidate.getName()+" subiu do nível "+nivelAntigo+" para o nível "+pokemonValidate.getLevel();
    }

    @Override
    public String atacar(PokemonEntity atacante, PokemonEntity alvo)
    {
        atacante.validarPokemon(atacante);
        alvo.validarPokemon(alvo);

        atacante.atacar(alvo);

        return "O pokemon "+atacante.getName()+" atacou "+alvo.getName()+ ". PS restante de "+alvo.getName()+": "+alvo.getActualPs()+"/"+alvo.getMaxPs();

    }

    @Override
    public String esquivar(PokemonEntity pokemon, String nomeAtacante)
    {
        pokemon.validarPokemon(pokemon);

        boolean conseguiu = pokemon.esquivar(nomeAtacante);

        return conseguiu
                ? "O pokemon "+pokemon.getName()+" esquivou do ataque de "+nomeAtacante
                : "O pokemon "+pokemon.getName()+" não conseguiu esquivar do ataque de "+nomeAtacante;
    }

    @Override
    public String fugir(PokemonEntity pokemon)
    {
        pokemon.validarPokemon(pokemon);

        boolean conseguiu = pokemon.fugir();

        return conseguiu
                ? "O pokemon "+pokemon.getName()+" fugiu da batalha"
                : "O pokemon "+pokemon.getName()+" não conseguiu fugir da batalha";
    }

    @Override
    public String movimentar(PokemonEntity pokemon)
    {
        pokemon.movimentar();
        return "O pokemon andou.";
    }
}

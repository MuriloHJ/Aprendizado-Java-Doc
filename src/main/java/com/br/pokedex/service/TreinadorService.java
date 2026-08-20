package com.br.pokedex.service;

import com.br.pokedex.entity.ArenaEntity;
import com.br.pokedex.entity.ItensObject;
import com.br.pokedex.entity.PokemonEntity;
import com.br.pokedex.entity.TreinadorEntity;
import com.br.pokedex.entity.enums.NameItem;
import com.br.pokedex.excpetions.InvalidInputExcpetion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TreinadorService implements com.br.pokedex.interfaces.TreinadorService
{
    private static final int TAMANHO_MAXIMO_TIME = 6;

    private final ArenaService arenaService = new ArenaService();

    public TreinadorService()
    {
    }

    @Override
    public String moverPeloMapa(TreinadorEntity treinador)
    {
        if(treinador == null)
        {
            throw new InvalidInputExcpetion("Erro!!! Treinador não pode ser nulo");
        }

        treinador.andar();
        return "O pokemon andou.";
    }

    @Override
    public String usarItem(TreinadorEntity treinador, ItensObject item, PokemonEntity pokemon)
    {
        if(treinador.getItens() == null)
        {
            throw new InvalidInputExcpetion("O treinador não possui itens");
        }

        Optional<ItensObject> itemEncontrado = treinador.getItens()
                .stream()
                .filter(i -> i.getNameItem().equals(item.getNameItem()))
                .findFirst();

        if(itemEncontrado.isEmpty())
        {
            throw new InvalidInputExcpetion("O treinador não possui esse item");
        }

        ItensObject itemDoTreinador = itemEncontrado.get();

        pokemon.usarItem(itemDoTreinador);

        itemDoTreinador.setQuantidade(itemDoTreinador.getQuantidade() - 1);

        if(itemDoTreinador.getQuantidade() <= 0)
        {
            treinador.getItens().remove(itemDoTreinador);
        }

        return "O treinador "+treinador.getName()+" usou "+item.getNameItem().name()+" em "+pokemon.getName();
    }

    @Override
    public TreinadorEntity realizarCompra(TreinadorEntity treinador, ItensObject item)
    {
        if(treinador.getItens() == null)
        {
            treinador.setItens(new ArrayList<>());
        }

        Optional<ItensObject> itemExistente = treinador.getItens()
                .stream()
                .filter(i -> i.getNameItem().equals(item.getNameItem()))
                .findFirst();

        if(itemExistente.isPresent())
        {
            ItensObject itemDoTreinador = itemExistente.get();
            itemDoTreinador.setQuantidade(itemDoTreinador.getQuantidade() + item.getQuantidade());
        }
        else
        {
            treinador.getItens().add(item);
        }

        System.out.println("Compra realizada! "+treinador.getName()+" comprou "+item.getQuantidade()+"x "+item.getNameItem().name());

        return treinador;
    }

    @Override
    public String desafiarLider(TreinadorEntity treinador, ArenaEntity arena)
    {
        if(treinador.getPokemons() == null || treinador.getPokemons().isEmpty())
        {
            throw new InvalidInputExcpetion("O treinador não possui pokemons para desafiar o líder");
        }

        System.out.println("O treinador "+treinador.getName()+" desafiou o líder "+arena.getLider().name()+"!");

        Random random = new Random();
        boolean venceu = random.nextBoolean();

        if(venceu)
        {
            return arenaService.entregarIsignia(arena, treinador);
        }

        return "O treinador "+treinador.getName()+" perdeu a batalha contra "+arena.getLider().name()+" e não recebeu a insígnia";
    }

    @Override
    public String batalhar(TreinadorEntity treinador, PokemonEntity pokemonAdversario)
    {
        if(treinador.getPokemons() == null || treinador.getPokemons().isEmpty())
        {
            throw new InvalidInputExcpetion("O treinador não possui pokemons para batalhar");
        }

        PokemonEntity pokemonDoTreinador = treinador.getPokemons()
                .stream()
                .filter(p -> p.getState() != PokemonEntity.State.DESMAIADO)
                .findFirst()
                .orElseThrow(() -> new InvalidInputExcpetion("Todos os pokemons do treinador estão desmaiados"));

        pokemonDoTreinador.atacar(pokemonAdversario);

        if(pokemonAdversario.getState() == PokemonEntity.State.DESMAIADO)
        {
            return "O pokemon "+pokemonDoTreinador.getName()+" venceu a batalha contra "+pokemonAdversario.getName()+"!";
        }

        return "A batalha entre "+pokemonDoTreinador.getName()+" e "+pokemonAdversario.getName()+" continua!";
    }

    @Override
    public TreinadorEntity capturar(TreinadorEntity treinador, List<PokemonEntity> pokemons)
    {
        Optional<ItensObject> pokebola = treinador.getItens()
                .stream()
                .filter(item -> item.getNameItem().equals(NameItem.POKEBOLA))
                .findFirst();

        if(pokebola.isEmpty())
        {
            throw new InvalidInputExcpetion("O treinador não tem pokebola");
        }

        Random random = new Random();

        int indice = random.nextInt(pokemons.size());

        PokemonEntity pokemonPego = pokemons.get(indice);

        adicionarPokemonAoTime(treinador, pokemonPego);

        ItensObject itemPokebola = pokebola.get();
        itemPokebola.setQuantidade(itemPokebola.getQuantidade() - 1);
        if(itemPokebola.getQuantidade() <= 0)
        {
            treinador.getItens().remove(itemPokebola);
        }

        System.out.println("Pokemon "+pokemonPego.getName()+" capturado com sucesso!");

        return treinador;
    }

    @Override
    public TreinadorEntity adicionarPokemonAoTime(TreinadorEntity treinador, PokemonEntity pokemon)
    {
        if(treinador.getPokemons() == null)
        {
            treinador.setPokemons(new ArrayList<>());
        }

        if(treinador.getPokemons().size() >= TAMANHO_MAXIMO_TIME)
        {
            throw new InvalidInputExcpetion("O time do treinador já está cheio (máximo de "+TAMANHO_MAXIMO_TIME+" pokemons)");
        }

        treinador.getPokemons().add(pokemon);

        System.out.println("Pokemon "+pokemon.getName()+" adicionado ao time de "+treinador.getName());

        return treinador;
    }

    @Override
    public String curarPokemons(TreinadorEntity treinador)
    {
        if(treinador.getPokemons() == null || treinador.getPokemons().isEmpty())
        {
            throw new InvalidInputExcpetion("O treinador não possui pokemons para curar");
        }

        for (PokemonEntity pokemon : treinador.getPokemons())
        {
            pokemon.setActualPs(pokemon.getMaxPs());
            pokemon.setState(PokemonEntity.State.NORMAL);
        }

        return "Todos os pokemons de "+treinador.getName()+" foram curados!";
    }

    @Override
    public TreinadorEntity updateNome(TreinadorEntity treinador, String name)
    {
        if (name.equals(treinador.getName())) {
            throw new InvalidInputExcpetion("O novo nome não pode ser igual ao atual");
        }

        treinador.setName(name);
        System.out.println("Nome do treinador alterado!!!!!!");
        return treinador;
    }
}

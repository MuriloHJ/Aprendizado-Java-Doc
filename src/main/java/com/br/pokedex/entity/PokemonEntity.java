package com.br.pokedex.entity;
import com.br.pokedex.entity.enums.Type;
import com.br.pokedex.excpetions.InvalidInputExcpetion;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Random;

@Entity
public class PokemonEntity
{
    @Id
    private Integer id;
    private String name;
    private String description;
    private State state;
    private int level;
    private int actualPs;
    private int maxPs;
    private boolean shiny;
    private Type type;
    private int stage;

    public PokemonEntity(Integer id, String name, String description,State state, int level, int actualPs, int maxPs, boolean shiny, Type type, int stage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.level = level;
        this.actualPs = actualPs;
        this.maxPs = maxPs;
        this.shiny = shiny;
        this.type = type;
        this.stage = stage;
    }

    public PokemonEntity(String name, String description, int level,State state, int actualPs, int maxPs, boolean shiny, Type type, int stage) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.state = state;
        this.actualPs = actualPs;
        this.maxPs = maxPs;
        this.shiny = shiny;
        this.type = type;
        this.stage = stage;
    }

    public PokemonEntity() {

    }


    public enum State
    {
        PARALISADO,
        QUEIMADO,
        ENVENENADO,
        SONO,
        CONGELADO,
        CONFUSO,
        NORMAL,
        DESMAIADO
    }


    public int getActualPs() {
        return actualPs;
    }

    public void setActualPs(int actualPs) {
        this.actualPs = actualPs;
    }

    public int getMaxPs() {
        return maxPs;
    }

    public void setMaxPs(int maxPs) {
        this.maxPs = maxPs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isShiny() {
        return shiny;
    }

    public void setShiny(boolean shiny) {
        this.shiny = shiny;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }



    public void movimentar()
    {
        System.out.println("O pokemon andou.");
    }


    public boolean esquivar(String namePokemon)
    {
        if(this.state == State.DESMAIADO)
        {
            System.out.println("Pokemon "+this.name+" está desmaiado e não pode esquivar!");
            return false;
        }

        Random random = new Random();
        boolean conseguiuEsquivar = random.nextBoolean();

        if(conseguiuEsquivar)
        {
            System.out.println("Pokemon "+this.name+" se esquivou do ataque de "+namePokemon+"!");
        }
        else
        {
            System.out.println("Pokemon "+this.name+" tentou esquivar do ataque de "+namePokemon+", mas não conseguiu!");
        }

        return conseguiuEsquivar;
    }

    public void atacar(PokemonEntity pokemon)
    {
        if(this.state == State.DESMAIADO)
        {
            System.out.println("Pokemon "+this.name+" está desmaiado e não pode atacar!");
            return;
        }

        Random random = new Random();
        int dano = (this.level * 2) + random.nextInt(10);

        System.out.println("Pokemon "+this.name+" atacou o "+pokemon.getName()+" causando "+dano+" de dano!");

        int psRestante = pokemon.getActualPs() - dano;
        pokemon.setActualPs(Math.max(psRestante, 0));

        pokemon.reagirDesmaio();
    }

    public void reagirDesmaio()
    {
        if(this.actualPs <= 0)
        {
            this.actualPs = 0;
            setState(State.DESMAIADO);
            System.out.println("Pokemon "+this.name+" desmaiou!");
        }
    }

    public boolean fugir()
    {
        Random random = new Random();
        boolean conseguiuFugir = random.nextBoolean();

        if(conseguiuFugir)
        {
            System.out.println("Pokemon "+this.name+" fugiu da batalha!");
        }
        else
        {
            System.out.println("Pokemon "+this.name+" tentou fugir, mas não conseguiu!");
        }

        return conseguiuFugir;
    }

    public void subirNivel()
    {
        this.level = this.level + 1;

        int incrementoPs = 5;
        this.maxPs = this.maxPs + incrementoPs;
        this.actualPs = this.actualPs + incrementoPs;

        System.out.println("Pokemon "+this.name+" subiu para o nível "+this.level+"!");
    }

    public void usarItem(ItensObject item)
    {
        if(item.getNameItem().getTypeItem1().equals(com.br.pokedex.entity.enums.TypeItem.CURA)
                || (item.getNameItem().getTypeItem2() != null && item.getNameItem().getTypeItem2().equals(com.br.pokedex.entity.enums.TypeItem.CURA)))
        {
            setActualPs(getMaxPs());
        }

        if(item.getNameItem().getTypeItem1().equals(com.br.pokedex.entity.enums.TypeItem.ANTIEFFECT)
                || (item.getNameItem().getTypeItem2() != null && item.getNameItem().getTypeItem2().equals(com.br.pokedex.entity.enums.TypeItem.ANTIEFFECT)))
        {
            setState(State.NORMAL);
        }

        if(item.getNameItem().getTypeItem1().equals(com.br.pokedex.entity.enums.TypeItem.CAPTURA))
        {
            System.out.println("Pokebola usada!");
        }

        System.out.println("Item "+item.getNameItem().name()+" usado em "+this.name+"!");
    }

    public PokemonEntity validarPokemon(PokemonEntity pokemon)
    {

        if(pokemon.getStage() < 1 || pokemon.getStage() > 3)
        {
            throw new InvalidInputExcpetion("Erro!!!! Estágio de pokemon inválido!!!!");
        }

        if(pokemon.getActualPs() > pokemon.getMaxPs())
        {
            throw new InvalidInputExcpetion("Erro!!!! A vida atual não pode ser maior que a vida máxima!!!!");
        }

        return pokemon;
    }
}

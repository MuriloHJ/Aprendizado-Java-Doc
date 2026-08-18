package com.br.pokedex.entity;
import com.br.pokedex.entity.enums.Type;

import java.util.Optional;

public class PokemonEntity
{
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
    private boolean needStone;


    public PokemonEntity(Integer id, String name, String description,State state, int level, int actualPs, int maxPs, boolean shiny, Type type, int stage, boolean needStone) {
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
        this.needStone = needStone;
    }

    public PokemonEntity(String name, String description, int level,State state, int actualPs, int maxPs, boolean shiny, Type type, int stage, boolean needStone) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.state = state;
        this.actualPs = actualPs;
        this.maxPs = maxPs;
        this.shiny = shiny;
        this.type = type;
        this.stage = stage;
        this.needStone = needStone;
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

    public boolean isNeedStone() {
        return needStone;
    }

    public void setNeedStone(boolean needStone) {
        this.needStone = needStone;
    }

    public void movimentar()
    {
        System.out.println("Pokemon "+this.name+" está se movimentando");
    }

    public void esquivar(String namePokemon)
    {
        System.out.println("Pokemon "+this.name+" se esquivou do ataque de "+namePokemon);
    }

    public void atacar(PokemonEntity pokemon)
    {
        System.out.println("Pokemon "+this.name+" atacou o "+pokemon.getName());
    }

    public void usarItem(ItensObject item)
    {
        if(item.getNameItem().getTypeItem1().name() == "CURA" || item.getNameItem().getTypeItem1().name() == "ANTIEFFECT" )
        {
            setState(State.NORMAL);
        }

    }
}

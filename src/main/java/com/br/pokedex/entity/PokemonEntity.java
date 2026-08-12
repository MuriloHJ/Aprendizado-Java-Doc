package com.br.pokedex.entity;

public class PokemonEntity
{
    private Integer id;
    private String name;
    private String description;
    private int level;
    private int actualPs;
    private int maxPs;
    private boolean shiny;
    private Type type;
    private int stage;


    public PokemonEntity(Integer id, String name, String description, int level, int actualPs, int maxPs, boolean shiny, Type type, int stage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
        this.actualPs = actualPs;
        this.maxPs = maxPs;
        this.shiny = shiny;
        this.type = type;
        this.stage = stage;
    }

    public PokemonEntity(String name, String description, int level, int actualPs, int maxPs, boolean shiny, Type type, int stage) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.actualPs = actualPs;
        this.maxPs = maxPs;
        this.shiny = shiny;
        this.type = type;
        this.stage = stage;
    }

    public enum Type{
        FIRE,
        WATER,
        GRASS,
        ELECTRIC,
        GROUND,
        ROCK,
        FLYING,
        BUG,
        FIGHTING,
        PSYCHIC,
        GHOST,
        FAIRY,
        DARK,
        DRAGON,
        ICE,
        METAL,
        POISON,
        NORMAL
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
}

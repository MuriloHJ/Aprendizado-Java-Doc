package com.br.pokedex.entity;

import com.br.pokedex.entity.enums.NameItem;

public class ItensObject
{
    private NameItem nameItem;
    private int quantidade;

    public ItensObject(NameItem nameItem, int quantidade) {
        this.nameItem = nameItem;
        this.quantidade = quantidade;
    }

    public NameItem getNameItem() {
        return nameItem;
    }

    public void setNameItem(NameItem nameItem) {
        this.nameItem = nameItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
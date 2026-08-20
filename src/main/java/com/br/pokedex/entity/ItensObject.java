package com.br.pokedex.entity;

import com.br.pokedex.entity.enums.NameItem;
import com.br.pokedex.excpetions.InvalidInputExcpetion;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ItensObject
{
    @Id
    private int id;
    private NameItem nameItem;
    private int quantidade;

    public ItensObject(NameItem nameItem, int quantidade) {
        this.nameItem = nameItem;
        this.quantidade = quantidade;
    }

    public ItensObject()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public ItensObject validarItem(ItensObject item)
    {
        if(item.getNameItem() == null)
        {
            throw new InvalidInputExcpetion("Erro!!! Nome do item não pode ser nulo");
        }

        return item;
    }
}
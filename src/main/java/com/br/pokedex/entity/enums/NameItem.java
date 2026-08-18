package com.br.pokedex.entity.enums;

public enum NameItem
{
    POCAO(TypeItem.CURA,TypeItem.ANTIEFECT),
    FRUTA(TypeItem.CURA,TypeItem.ANTIEFECT),
    POKEBOLA(TypeItem.CAPTURA),
    PEDRAEVO(TypeItem.EVOLUCAO);

   private TypeItem typeItem1;
   private TypeItem typeItem2;

   NameItem(TypeItem typeItem1, TypeItem typeItem2)
   {
       this.typeItem1 = typeItem1;
       this.typeItem2 = typeItem2;
   }

   NameItem(TypeItem typeItem1) {
       this.typeItem1 = typeItem1;
   }

    public TypeItem getTypeItem2() {
        return typeItem2;
    }

    public void setTypeItem2(TypeItem typeItem2) {
        this.typeItem2 = typeItem2;
    }

    public TypeItem getTypeItem1() {
        return typeItem1;
    }

    public void setTypeItem1(TypeItem typeItem1) {
        this.typeItem1 = typeItem1;
    }
}



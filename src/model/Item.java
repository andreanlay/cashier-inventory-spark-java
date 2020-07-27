package model;

import lombok.Getter;

public class Item{
    @Getter private Integer id;
    @Getter private String name;
    @Getter private String category;
    @Getter private Integer stock;
    @Getter private Integer price;

    public Item(){
        id = price = 0;
        stock = 1;
        name = category = "";
    }
    public Item(Integer id, String name, String category, Integer stock, Integer price){
        this.id = id;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }
}

package model;

import lombok.Getter;

public class CartItem{
    @Getter private Integer id;
    @Getter private Integer p_id;
    @Getter private String name;
    @Getter private Integer quantity;
    @Getter private Integer price;
    @Getter private Integer total;

    public CartItem(){
        id = p_id = quantity = price = total = 0;
        name = "";
    }
    public CartItem(Integer id, Integer p_id, String name, Integer quantity, Integer price, Integer total){
        this.id = id;
        this.p_id = p_id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
}

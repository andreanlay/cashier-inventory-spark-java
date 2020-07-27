package model;

import lombok.Getter;

public class Category{
    @Getter private Integer id;
    @Getter private String name;

    public Category(){
        id = 0;
        name = "";
    }
    public Category(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}

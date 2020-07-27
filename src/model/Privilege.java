package model;

import lombok.Getter;

public class Privilege{
    @Getter private Integer id;
    @Getter private String type;

    public Privilege(){
        id = 0;
        type = "";
    }
    public Privilege(Integer id, String type){
        this.id = id;
        this.type = type;
    }
}

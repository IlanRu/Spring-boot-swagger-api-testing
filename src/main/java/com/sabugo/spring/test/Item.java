package com.sabugo.spring.test;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Item")
public class Item {
    @Column(name="ID")
    private @Id long itemNum;
    @Column(name="NAME")
    private String name;
    @Column(name="AMOUNT")
    private int amount;
    @Column(name="CODE")
    private int inventoryCode;

    public Item(){
        
    }
    public Item(long num, String name, int amount, int code){
        itemNum=num;
        this.name=name;
        this.amount=amount;
        inventoryCode=code;
    }
}

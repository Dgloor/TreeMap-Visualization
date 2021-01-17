/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Danny Loor
 */
public abstract class Pesable {
    private String name;
    private Long weight;

    public Pesable(String name, Long weight) {
        this.name = name;
        this.weight = weight;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeight() {
        return weight;
    }

    public void Long(Long weight) {
        this.weight = weight;
    }
    
    abstract double getComputedWeight();
}

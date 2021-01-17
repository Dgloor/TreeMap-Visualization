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
public class Directorio extends Pesable {

    public Directorio(String name, Long weight) {
        super(name, weight);
    }

    @Override
    double getComputedWeight() {
        return 0;
    }
    
}

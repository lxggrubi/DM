/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author leongrubisic
 */
public abstract class Food extends Model {

    public String name;
    public String getType;

    abstract double getProteins();

    abstract double getCarbs();

    abstract double getCalories();

    abstract double getFats();
}

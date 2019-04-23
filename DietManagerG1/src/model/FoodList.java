package model;

import java.util.HashMap;
import java.util.Map;

public class FoodList {
 private Map < String, Food > foods = new HashMap < > ();
 /**
  * Adding food to hashmap
  * @param food 
  */
 public void addFood(Food food) {
  this.foods.put(food.getName(), food);
 }
 /**
  * 
  * @param nameToRemove 
  */
 public void removeFood(String nameToRemove) {
  //todo
 }
 /**
  * 
  * @return the food from hashmap
  */
 public Map < String, Food > getFoods() {
  return foods;
 }

 /**
  * Takes the food fro hashmap and returns it to the user
  * @param key - The name of the food
  * @return food from hashmap
  */
 public Food findFood(String key) {
  for (String food: foods.keySet()) {
   if (food.toLowerCase().equals(key.toLowerCase())) return foods.get(food).copy();
  }

  return null;
 }
}
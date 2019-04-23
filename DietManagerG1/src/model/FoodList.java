package model;

import java.util.HashMap;
import java.util.Map;

public class FoodList {
 private Map < String, Food > foods = new HashMap < > ();
 /**
  * Method used to add food to HashMap
  * @param food 
  */
 public void addFood(Food _food) {
  this.foods.put(_food.getName(), _food);
 }

 /**
  * 
  * @return the food from HashMap
  */
 public Map < String, Food > getFoods() {
  return foods;
 }

 /**
  * Takes the food fro HashMap and returns it to the user
  * @param key - The name of the food
  * @return food from HashMap
  */
 public Food findFood(String key) {
  for (String f: foods.keySet()) {
   if (f.toLowerCase().equals(key.toLowerCase())) return foods.get(f).copy();
  }
  return null;
 }
}
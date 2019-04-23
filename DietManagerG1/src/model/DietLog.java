package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.*;

public class DietLog {

 protected String currentDate;
 protected double caloriesSpentSum;
 protected double fatSum;
 protected double proteinSum;
 protected double carbsSum;
 protected double dailyLimit;
 protected double dailyWeight;
 protected int caloriesSum;
 private int fatPercentage;
 private int carbsPercentage;
 private int proteinPercentage;

 /**
  * Taking date and formatting it 
  */
 protected DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
 protected Date date = new Date();
 private ArrayList < Food > dailyFood = new ArrayList < > ();
 private ArrayList < Workout > dailyExercise = new ArrayList < > ();

 /**
  * Default constructor for log object
  */
 public DietLog() {
  currentDate = dateFormat.format(date);
 }

 /**
  * Accessor for returning total sum of calories amount 
  * @return - Calories Sum as integer
  */
 public int getCaloriesSum() {
  return caloriesSum;
 }
 /**
  * Accessor for returning the total sum of calories that have been spent
  * @return calories that have been spent as double
  */
 public double getCaloriesSpentSum() {
  return caloriesSpentSum;
 }

 /**
  * Accessor for returning the total sum of fat
  * @return fat that has been spent as double
  */
 public double getFatSum() {
  return fatSum;
 }

 /**
  * Accessor for returning the total sum of Proteins
  * @return proteins that have been spent as double
  */
 public double getProteinSum() {
  return proteinSum;
 }

 /**
  * Accessor for returning the total sum of carbs
  * @return carbs that have been spent as double
  */
 public double getCarbsSum() {
  return carbsSum;
 }
 /**
  * Accessor for returning the daily weight
  * @return dailyWeight as double
  */
 public double getDailyWeight() {
  return dailyWeight;
 }
 /**
  * Accessor for returning the Daily limit
  * @return dailyLimit 
  */
 public double getDailyLimit() {
  return dailyLimit;
 }
 /**
  * Accessor for returning the percentage of Fat
  * @return calculated fat percentage as double
  */
 public double getFatPercentage() {
  return fatPercentage;
 }
 /**
  * Accessor for returning the percentage of Carbs
  * @return calculated carbs percentage as double
  */
 public double getCarbsPercentage() {
  return carbsPercentage;
 }
 /**
  * Accessor for returning the percentage of proteins
  * @return calculated proteins percentage as double
  */
 public double getProteinPercentage() {
  return proteinPercentage;
 }
 
 /**
  * Mutator for setting users daily limit of calories
  * @param calorieLimit 
  */

 public void setDailyLimit(double calorieLimit) {
  dailyLimit = calorieLimit;
 }
 
/**
 * Mutator for setting users weight for day
 * @param weight 
 */
 public void setDailyWeight(double weight) {
  dailyWeight = weight;
 }

 /**
  * Calculating the difference between consumed and spend calories
  * @return 
  */
 public double getCaloriesDifference() {
  return caloriesSum - caloriesSpentSum;
 }

/**
 * Loging the food that has been consumed to the list
 * @param foodItem
 * @param servings 
 */
 public void logFood(Food foodItem, double servings) {
  foodItem.setServing(servings);
  dailyFood.add(foodItem);
 }

 /**
  * Method used to calculate the sum of used calories for the user for duration of exercise 
  * @param ex exercise 
  * @param ti minutes of Exercise
  * @return users sum of calories used
  */
 public double caloriesSpend(Workout wo, double ti) {
  double usedCalories = wo.getCaloriesSpent() * (this.dailyWeight / 100.0) * (ti / 60);
  caloriesSpentSum = caloriesSpentSum + usedCalories;
  return usedCalories;
 }

 /**
  * Adds an exercise to the list of exercises performed
  * @param exer the Workout object
  * @param ti2 minutes of Exercise
  */
 public void logExercise(Workout wo2, double ti2) {
  double usedCalories = caloriesSpend(wo2, ti2);

  wo2.setCaloriesSpent(usedCalories);
  wo2.setTime(ti2);
  dailyExercise.add(wo2);
 }

 /**
  * Removes exercise by using index
  * @param ind
  */
 public void removeExerciseByIndex(int ind) {
  dailyExercise.remove(ind);
 }

 /**
  * Accessor used to return all exercise on date
  * @return ArrayList of Workout
  */
 public ArrayList < Workout > getDailyExercise() {
  return dailyExercise;
 }

 /**
  * ArrayList for all exercise
  * @return An ArrayList containing exercise statistic
  */
 public ArrayList < String > getExerciseStatistic() {
  ArrayList < String > ExerciseStatistic = new ArrayList < > ();
  for (Workout e: dailyExercise) {
   String nameOfExercise = e.getWorkoutName();
   double caloriesSpent = e.getCaloriesSpent();
   String exerciseInformation = "Name: " + nameOfExercise + "\nCalories: " + caloriesSpent;
   ExerciseStatistic.add(exerciseInformation);
  }

  return ExerciseStatistic;

 }

/**
 * Removes food item from from list
 * @param foodItem 
 */
 public void removeFood(Food foodItem) {
  dailyFood.remove(foodItem);
 }
/**
 * Removes food item from from list
 * @param index 
 */
 public void removeIndexedFood(int index) {
  dailyFood.remove(index);
 }

 public ArrayList < Food > getDailyFood() {
  return dailyFood;
 }


 public ArrayList < String > getFoodInfo() {
  ArrayList < String > foodInfoList = new ArrayList < > ();

  for (Food food: dailyFood) {
   String foodName = food.getName();
   int foodCalories = food.getCalories();
   double foodServings = food.getServing();

   String foodInfo = "Name: " + foodName + "\nCalories: " + foodCalories + "\nServings:  " + foodServings;
   foodInfoList.add(foodInfo);
  }

  return foodInfoList;
 }



 public DietLog calculatingSum() {
  int _caloriesSum = 0;
  double _totalSpent = 0;
  double _fatSum = 0;
  double _proteinSum = 0;
  double _carbsSum = 0;

  for (Food food: dailyFood) {
   double foodServing = food.getServing();
   _caloriesSum += (food.getCalories() * foodServing);
   _fatSum += (food.getFat() * foodServing);
   _proteinSum += (food.getProtein() * foodServing);
   _carbsSum += (food.getCarbs() * foodServing);
  }

  for (Workout e: dailyExercise) {
   _totalSpent = _totalSpent + e.getCaloriesSpent();
  }

  this.caloriesSum = _caloriesSum;
  this.caloriesSpentSum = _totalSpent;
  this.fatSum = _fatSum;
  this.proteinSum = _proteinSum;
  this.carbsSum = _carbsSum;

  calcPercentages();

  return this;
 }
/**
 * Calculating difference between consumed and spent calories
 * @return 
 */
 public double calculatingCalories() {
  int caloriesConsumed = this.caloriesSum;
  double caloriesSpent = this.caloriesSpentSum;
  double caloriesCalculated = dailyLimit + caloriesSpent - caloriesConsumed;

  return caloriesCalculated;
 }
/**
 * Calculating percentages and rounding to integer
 */
 public void calcPercentages() {
  double totalNutrients = this.carbsSum + this.proteinSum + this.fatSum;

  this.fatPercentage = (int) Math.rint(this.fatSum / totalNutrients * 100);
  this.carbsPercentage = (int) Math.rint(this.carbsSum / totalNutrients * 100);
  this.proteinPercentage = (int) Math.rint(this.proteinSum / totalNutrients * 100);
 }

} //end of class
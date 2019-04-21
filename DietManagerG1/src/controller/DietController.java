package controller;

import model.*;

import java.util.*;
import javax.swing.*;

/**
 * Class that controls the data flow inside the DietManager program.
 * @author G1
 */
public class DietController {

    private FoodList foods;
    private ExerciseList exercises;
    private DataLoad data = new DataLoad();
    private User user = new User();

    public DietController() {
        foods = data.loadFoods();
        exercises = data.loadExercises();
        user.setLogHistory(data.loadLogs(foods, exercises));
    }

    /**
     * Add basic food to the list of foods.
     *
     * @param name
     * @param calories
     * @param carbs
     * @param proteins
     * @param fats
     */
    public void addBasicFood(String name, int calories, double carbs, double proteins, double fats) {
        BasicFood food = new BasicFood(name, calories, carbs, proteins, fats);
        foods.addFood(food);
    }

    /**
     * Adds exercise to the list of exercises.
     *
     * @param name
     * @param calories
     */
    public void addExercise(String name, double calories) {
        try {
            if (name.contains(",")) {
                JOptionPane.showMessageDialog(null, "No special characters allowed!");
            } else {
                Exercise exercise = new Exercise(name, calories);
                exercises.addExercise(exercise);
                JOptionPane.showMessageDialog(null, "Exercise has been addded!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds recipe to the list of recipes.
     *
     * @param name
     * @param recipesList
     * @param quantity
     */
    public void addRecipe(String name, ArrayList<String> recipesList, ArrayList<String> quantity) {
        Recipe recipe = new Recipe(name);

        for (int i = 0; i < recipesList.size(); i++) {
            recipe.addChild(foods.findFood(recipesList.get(i)), quantity.get(i));
        }

        recipe.addNutritionalInfo();
        foods.addFood(recipe);
    }
    
    /**
     * Adds a food to the log.csv on a specified day.
     * @param name
     * @param quantity
     * @param date
     * @return 
     */
    public boolean logFood(String name, double quantity, String date) {
        Food loggedFood = foods.findFood(name);
        user.logFood(loggedFood, quantity, date);
        return true;
    }

    /**
     * Adds exercise to the log.csv on a specified day.
     * @param name
     * @param time
     * @param date
     * @return 
     */
    public boolean logExercise(String name, double time, String date) {
        Exercise loggedExer = exercises.getExercise(name);
        user.logExercise(loggedExer, time, date);
        return true;
    }

    /**
     * Finds a food in the list of foods.
     * @param nameFood
     * @return 
     */
    public Food searchFoodList(String nameFood) {
        return foods.findFood(nameFood);
    }

    /**
     * Finds an exercise in exercise list.
     * @param exerName
     * @return 
     */
    public Exercise searchExerciseList(String exerName) {
        return exercises.getExercise(exerName);
    }

    /**
     * 
     * @return 
     */
    public Set<String> getFoodListKeys() {
        return foods.getFoods().keySet();
    }

    /**
     * Method that gets exercise names.
     * @return 
     */
    public Set<String> getExerciseNames() {
        return exercises.getAllExercises().keySet();
    }

    /**
     * Method that logs the calorie limit on a specified day.
     * @param calories
     * @param date 
     */
    public void logCalorieLimit(double calories, String date) {
        user.setCaloricLimit(calories);
        user.updateLogCalorieLimit(calories, date);
    }

    /**
     * Method that logs the user's weight.
     * @param weight
     * @param date 
     */
    public void logDailyWeight(double weight, String date) {
        user.setWeight(weight);
        user.updateLogWeight(weight, date);
    }

    // attempts to find desired DietLog object and returns it
    /**
     * 
     * @param date
     * @return 
     */
    public DietLog getDesiredDietLog(String date) {
        return user.searchLogHistory(date).calcTotals();
    }

    // returns history of user's dietlog objects
    /**
     * 
     * @return 
     */
    public Map<String, DietLog> getUserHistory() {
        return user.getLogHistory();
    }

    /**
     * Method that returns the current date.
     * @return 
     */
    public String getCurrentDate() {
        return user.getCurrentDate();
    }

    /**
     * Method that sets the current date.
     * @param date 
     */
    public void setCurrentDate(String date) {
        user.setCurrentDate(date);
    }

    /**
     * Method that gets the log for today.
     * @return 
     */
    private DietLog getTodaysLog() {
        return user.getTodaysLog();
    }

    /**
     * Method that gets the foods logged for today.
     * @return 
     */
    public ArrayList<Food> getTodaysLogList() {
        return getTodaysLog().getDailyFood();
    }

    /**
     * Method that gets the exercises logged for today.
     * @return 
     */
    public ArrayList<Exercise> getTodaysExerciseLogList() {
        return getTodaysLog().getDailyExercise();
    }

    /**
     * Method that removes food from the log.csv.
     * @param index 
     */
    public void removeFoodFromLog(int index) {
        getTodaysLog().removeFoodByIndex(index);
    }

    /**
     * Method that removes exercise from the log.csv.
     * @param index 
     */
    public void removeExerciseFromLog(int index) {
        getTodaysLog().removeExerciseByIndex(index);
    }

    /**
     * Method that sets the user's name.
     * @param name 
     */
    public void setUserName(String name) {
        user.setName(name);
    }

    /**
     * Method that returns the user's name.
     * @return 
     */
    public String getUserName() {
        return user.getName();
    }

    /**
     * Method that writes everything to the log.csv.
     */
    public void logToCSV() {
        data.appendFoods(foods);
        data.appendExercises(exercises);
        data.appendLogs(user);
    }

}

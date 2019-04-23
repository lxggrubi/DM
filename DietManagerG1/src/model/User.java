package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class User {

 private String user;
 private String currentDate;
 private double targetWeight;

/**
 * Default constructor for User class
 */
 public User() {
  DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
  LocalDate localDate = LocalDate.now();
  currentDate = date.format(localDate);
  user = "User";
 }

 public Map < String, DietLog > logHistory = new HashMap < String, DietLog > ();
/**
 * Mutator used to set weight of user
 * @param _weight - Double used for users weight
 */
 public void setWeight(double _weight) {
  getCurrentLog().setDailyWeight(_weight);
 }
/**
 * Accessors for returning the weight of user
 */
 public double getWeight() {
  return getCurrentLog().getDailyWeight();
 }
/**
 * Mutator used to set limit of calories per day
 * @param _caloriesLimit - this is daily calories limit
 */
 public void setCaloriesLimit(double _caloriesLimit) {
  getCurrentLog().setDailyLimit(_caloriesLimit);
 }
/**
 * Accessors for returning the daily limit of calories  
 * @return - it will return daily limit of calories
 */
 public double getCaloriesLimit() {
  return getCurrentLog().getDailyLimit();
 }
/**
 * Mutator used to set the desired weight that users want to reach
 * @param _desiredWeight - the weight that will be our goal to reach
 */
 public void setDesiredWeight(double _desiredWeight) {
  this.targetWeight = _desiredWeight;
 }
/**
 * Accessors for returning users desired weight from input
 * @return will return desired weight
 */
 public double getDesiredWeight() {
  return targetWeight;
 }
/**
 * Mutator used to set the users name (default is user)
 * @param _user - it will set the users name
 */
 public void setUserName(String _user) {
  this.user = _user;
 }
/**
 * Accessors for returning name of the user
 * @return - users name
 */
 public String getName() {
  return this.user;
 }
/**
 * Accessors for returning the todays date
 * @return - todays date
 */
 public String getCurrentDate() {
  return currentDate;
 }
/**
 * Mutator that is used for todays date 
 * @param _currentDate - todays date
 */
 public void setCurrentDate(String _currentDate) {
  currentDate = _currentDate;
 }
/**
 * Map that will store the LogHistory
 * @return - History of what has been logged
 */
 public Map < String, DietLog > getLogHistory() {
  return logHistory;
 }
/**
 * Mutator for log history 
 * @param logHistory - History of what has been logged
 */
 public void setLogHistory(Map < String, DietLog > logHistory) {
  this.logHistory = logHistory;
 }
/**
 * Ternary operator for checking log history 
 * @return - if it is null it will return current date and if it is not it will be new log
 */
 public DietLog getCurrentLog() {     
return (logHistory.get(currentDate) == null) ? logHistory.put(currentDate, new DietLog()) : logHistory.get(currentDate);  
 }
/**
 * Logging the food 
 * @param food 
 * @param servings
 * @param date 
 */
 public void logFood(Food food, double servings, String date) {
  searchLogHistory(date).logFood(food, servings);
 }
/**
 * Logging for the exercise
 * @param exer
 * @param time
 * @param date 
 */
 public void logExercise(Workout exer, double time, String date) {
  searchLogHistory(date).logExercise(exer, time);
 }
/**
 * This method will check the last log
 * If there is found it will give us the latest log
 * If it is empty it will set the default vallues
 * @param date
 * @return - it will return Diet log to the user
 */
 public DietLog searchLogHistory(String date) {
  DietLog desiredLog = logHistory.get(date);

  if (desiredLog != null) { return desiredLog;}
  else { desiredLog = new DietLog();
   
   ArrayList < String > sortedLogs = new ArrayList < > (logHistory.keySet());
   sortedLogs.add(date);
   Collections.sort(sortedLogs);
   int previousIndex = sortedLogs.indexOf(date) - 1;

   if (previousIndex >= 0 && previousIndex < sortedLogs.size()) {
    DietLog previousLog = logHistory.get(sortedLogs.get(previousIndex));
    desiredLog.setDailyWeight(previousLog.getDailyWeight());
    desiredLog.setDailyLimit(previousLog.getDailyLimit());
   } else {
    desiredLog.setDailyWeight(150.0);
    desiredLog.setDailyLimit(2000.0);
   }
   logHistory.put(date, desiredLog);
   return logHistory.get(date);
  }
 }
/**
 * Updating the calories limit for the user
 * @param calorieLimit
 * @param date 
 */
 public void updateLogCalorieLimit(double calorieLimit, String date) {
  searchLogHistory(date).setDailyLimit(calorieLimit);
 }
/**
 * Updating the log.csv with users weight
 * @param weight
 * @param date 
 */
 public void updateLogWeight(double weight, String date) {
  searchLogHistory(date).setDailyWeight(weight);
 }
} //end of class
package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class User {

 private String user;
 private String currentDate;
 private double targetWeight;


 public User() {
  DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
  LocalDate localDate = LocalDate.now();
  currentDate = date.format(localDate);
  user = "User";
 }

 public Map < String, DietLog > logHistory = new HashMap < String, DietLog > ();

 public void setWeight(double _weight) {
  getCurrentLog().setDailyWeight(_weight);
 }

 public double getWeight() {
  return getCurrentLog().getDailyWeight();
 }

 public void setCaloriesLimit(double _caloriesLimit) {
  getCurrentLog().setDailyLimit(_caloriesLimit);
 }

 public double getCaloriesLimit() {
  return getCurrentLog().getDailyLimit();
 }

 public void setDesiredWeight(double _desiredWeight) {
  this.targetWeight = _desiredWeight;
 }

 public double getDesiredWeight() {
  return targetWeight;
 }

 public void setUserName(String _user) {
  this.user = _user;
 }

 public String getName() {
  return this.user;
 }

 public String getCurrentDate() {
  return currentDate;
 }

 public void setCurrentDate(String _currentDate) {
  currentDate = _currentDate;
 }

 public Map < String, DietLog > getLogHistory() {
  return logHistory;
 }

 public void setLogHistory(Map < String, DietLog > logHistory) {
  this.logHistory = logHistory;
 }

 public DietLog getCurrentLog() {     
return (logHistory.get(currentDate) == null) ? logHistory.put(currentDate, new DietLog()) : logHistory.get(currentDate);  
 }

 public void logFood(Food food, double servings, String date) {
  searchLogHistory(date).logFood(food, servings);
 }

 public void logExercise(Workout exer, double time, String date) {
  searchLogHistory(date).logExercise(exer, time);
 }

 public DietLog searchLogHistory(String date) {
  DietLog desiredLog = logHistory.get(date);

  if (desiredLog != null) { return desiredLog;}
  else { desiredLog = new DietLog();
   
   // getting previous most recent log
   ArrayList < String > sortedLogs = new ArrayList < > (logHistory.keySet());
   sortedLogs.add(date);
   Collections.sort(sortedLogs);
   int previousIndex = sortedLogs.indexOf(date) - 1;

   // if found, use the previous calorie and weight info
   if (previousIndex >= 0 && previousIndex < sortedLogs.size()) {
    DietLog previousLog = logHistory.get(sortedLogs.get(previousIndex));
    desiredLog.setDailyWeight(previousLog.getDailyWeight());
    desiredLog.setDailyLimit(previousLog.getDailyLimit());
   } else {
    // or else if not set (value would come back as 0.0), we set default values for them
    desiredLog.setDailyWeight(150.0);
    desiredLog.setDailyLimit(2000.0);
   }

   // add it into the User's history
   logHistory.put(date, desiredLog);

   // return the newly created DietLog object to the user
   return logHistory.get(date);
  }
 }

 public void updateLogCalorieLimit(double calorieLimit, String date) {
  searchLogHistory(date).setDailyLimit(calorieLimit);
 }

 public void updateLogWeight(double weight, String date) {
  searchLogHistory(date).setDailyWeight(weight);
 }
} //end of class
package model;

public class Workout {

 public double time;
 public String workoutName;
 public double caloriesSpent; //calories burned per hour


 /**
  * Default constructor
  */
 public Workout() {}

 /**
  * The constructor for a single Exercise
  * @param name - A String for the exercise name
  * @param caloriesSpent - a double for the number of calories burned per hour
  */
 public Workout(String _workoutName, double _caloriesBurned) {
  this.workoutName = _workoutName;
  this.caloriesSpent = _caloriesBurned;
 }

 /**
  * Sets the name of the exercise
  * @param name - The new String name of the exercise
  */
 public void setName(String _workoutName) {
  this.workoutName = _workoutName;
 }

 /**
  * Gets the current name of the exercise
  * @return - String name
  */
 public String getWorkoutName() {
  return workoutName;
 }

 /**
  * Sets the calories burned per hours for this exercise
  * @param caloriesBurned - The new double value
  */
 public void setCaloriesSpent(double _caloriesSpent) {
  this.caloriesSpent = _caloriesSpent;
 }

 /**
  * Get's the current value for calories burned per hour
  * @return - Double value
  */
 public double getCaloriesSpent() {
  return caloriesSpent;
 }

 public void setTime(double time) {
  this.time = time;
 }

 public double getTime() {
  return time;
 }

 /**
  * Function to make the object into a string formatted for the csv
  * @return A string formatted for the csv representing the exercise object
  */
 public String buildCSVFormat() {
  return "e," + workoutName + "," + caloriesSpent;
 }

 public Workout copy() {
  Workout exerciseCopy = new Workout(workoutName, caloriesSpent);
  exerciseCopy.time = time;
  return exerciseCopy;
 }

} //end of class
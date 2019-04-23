package model;

public class Workout {

 public double time;
 public String workoutName;
 public double caloriesSpent; 


 /**
  * Default constructor
  */
 public Workout() {}

 /**
  * The parameterized constructor that will take workout
  * @param _workoutName - the name of the workout
  * @param caloriesSpent -this is the number of the calories 
  */
 public Workout(String _workoutName, double _caloriesBurned) {
  this.workoutName = _workoutName;
  this.caloriesSpent = _caloriesBurned;
 }

 /**
  * Mutator used to set the workout name
  * @param _workoutName - 
  */
 public void setName(String _workoutName) {
  this.workoutName = _workoutName;
 }

 /**
  * Accessors that will be used to return the name of workout
  * @return - String name of the workout
  */
 public String getWorkoutName() {
  return workoutName;
 }

 /**
  * Mutator used to set the caloies that have been spent in hour
  * @param _caloriesSpent - the number of calories that have been spent
  */
 public void setCaloriesSpent(double _caloriesSpent) {
  this.caloriesSpent = _caloriesSpent;
 }

 /**
  * Accessors that will return the number of calories that have been spent 
  * @return - double  
  */
 public double getCaloriesSpent() {
  return caloriesSpent;
 }
 /**
  * Mutator used to set the time 
  * @param time - double 
  */
 public void setTime(double time) {
  this.time = time;
 }
 /**
  * Accessors that will return time
  * @return 
  */
 public double getTime() {
  return time;
 }

 /**
  * Formating string to csv format
  * @return formatted string for csv file
  */
 public String buildCSVFormat() {
  return "e," + workoutName + "," + caloriesSpent;
 }
 /**
  * Creating the copy of exercise
  * @return 
  */
 public Workout copy() {
  Workout exerciseCopy = new Workout(workoutName, caloriesSpent);
  exerciseCopy.time = time;
  return exerciseCopy;
 }

} //end of class
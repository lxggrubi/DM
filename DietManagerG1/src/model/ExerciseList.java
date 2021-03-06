package model;

import java.util.HashMap;
import java.util.Map;

public class ExerciseList extends Workout {

 /**
  * HashMap used for storing different types of workout
  */
 private Map < String, Workout > Exercises = new HashMap < > ();

 /**
  * Method used to add the workouts to the array list (it is containing workout objects)
  * @param exer - It is the workout object
  */
 public void newExercise(Workout exercise) {
  this.Exercises.put(exercise.getWorkoutName(), exercise);
 }

 /**
  * Accessor for an exercise that matches the name given by the user
  * @param workoutName - It is the name of workout
  */
 public Workout getExercise(String workoutName) {
  for (String ex: Exercises.keySet()) {
   if (ex.toLowerCase().equals(workoutName.toLowerCase())) return Exercises.get(ex).copy();
  }
  return null;
 }

 /**
  * Accessor that is used to return the workout object  
  */
 public Map < String, Workout > getExercisesList() {
  return Exercises;
 }

} //end of class
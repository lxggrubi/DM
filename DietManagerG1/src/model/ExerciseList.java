package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExerciseList extends Workout {

    //The ArrayList of Workout objects
    private Map<String, Workout> Exercises = new HashMap<>();

    /**
     * Adds an exercise to the ArrayList of Workout objects
     * @param exer - An Workout object
     */
    public void addExercise(Workout exer){
        this.Exercises.put(exer.getName(), exer);
    }

    /**
     * Gets an exercise that matches the name given by the user
     * @param name - the name of the exercise the user wants to get
     * @return - an Workout object
     */
    public Workout getExercise(String name){
        for (String exer : Exercises.keySet()) {
            if (exer.toLowerCase().equals(name.toLowerCase())) return Exercises.get(exer).copy();
        }

        return null;
    }

    /**
     * Returns the whole ArrayList of Workout objects
     * @return - the ArrayList of Exericse objects;
     */
    public Map<String, Workout> getAllExercises(){
        return Exercises;
    }


}

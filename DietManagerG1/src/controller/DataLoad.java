package controller;

import model.BasicFood;
import model.Food;
import model.FoodList;
import model.Recipe;
import model.User;
import model.DietLog;
import model.Exercise;
import model.ExerciseList;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class DataLoad {

    /*
     *  Methods that writes the foods from the FoodList class.
     */
    public void appendFoods(FoodList foodList) {
        File file = new File("Assets/foods.csv");
        try {
            file.createNewFile();

            FileWriter fw = new FileWriter(file);
            fw.write("");

            Object[] foods = foodList.getFoods().values().toArray();
            for (int i = 0; i < foods.length; i++) {
                Food food = (Food) foods[i];
                String line = (i > 0 ? "\n" : "") + food.buildCSVFormat();
                fw.append(line);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendExercises(ExerciseList exerList) {
        File file = new File("Assets/exercises.csv");
        try {
            file.createNewFile();

            FileWriter fw = new FileWriter(file);
            fw.write("");

            Object[] exercises = exerList.getAllExercises().values().toArray();
            for (int i = 0; i < exercises.length; i++) {
                Exercise exer = (Exercise) exercises[i];

                String line = (i > 0 ? "\n" : "") + exer.buildCSVFormat();
                fw.append(line);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
     * Method that uses FileWriter to log daily intake to .csv file.
     */
    public void appendLogs(User user) {
        File file = new File("Assets/log.csv");
        try {
            file.createNewFile();

            FileWriter fw = new FileWriter(file);
            fw.write("");

            Map<String, DietLog> logs = user.getLogHistory();

            for (Map.Entry<String, DietLog> entry : logs.entrySet()) {

                DietLog value = entry.getValue();

                String[] dateSplit = entry.getKey().split("/");
                
                String dateLine = dateSplit[0] + "," + dateSplit[1] + "," + dateSplit[2] + ",";
                Double weightData = value.getDailyWeight();
                Double calorieLimit = value.getDailyLimit();

                String weightLine = dateLine + "w," + weightData + "\n";
                fw.append(weightLine);

                String calorieLimitLine = dateLine + "c," + calorieLimit + "\n";
                fw.append(calorieLimitLine);

                for (Food food : value.getDailyFood()) {
                    String foodLine = dateLine + "f," + food.getName() + "," + food.getServing() + "\n";
                    fw.append(foodLine);
                }

                for (Exercise e : value.getDailyExercise()) {
                    String exerLine = dateLine + "e," + e.getName() + "," + e.getTime() + "\n";
                    fw.append(exerLine);
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FoodList loadFoods() {

        ArrayList<String> recipes = new ArrayList<String>();
        ArrayList<String> quantity = new ArrayList<String>();
        ArrayList<String> basicFoods = new ArrayList<String>();
        ArrayList<String> recipeFoods = new ArrayList<String>();

        FoodList foodsList = new FoodList();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Assets/foods.csv"));

            String lineRead = "";
            while ((lineRead = br.readLine()) != null) {

                String[] lineArr = lineRead.split(",");

                if (lineArr[0].equals("b")) {
                    basicFoods.add(lineRead);
                } else if (lineArr[0].equals("r")) {
                    recipeFoods.add(lineRead);
                }
            }
            br.close();

            for (int i = 0; i < basicFoods.size(); i++) {

                String[] basicSplitter = basicFoods.get(i).split(",");

                foodsList.addFood(new BasicFood(basicSplitter[1], Integer.parseInt(basicSplitter[2]), Double.parseDouble(basicSplitter[3]),
                        Double.parseDouble(basicSplitter[4]), Double.parseDouble(basicSplitter[5])));
            }

            for (int i = 0; i < recipeFoods.size(); i++) {
                String[] recipeSplitter = recipeFoods.get(i).split(",");

                for (int k = 2; k <= (recipeSplitter.length - 2); k += 2) {
                    recipes.add(recipeSplitter[k]);
                    quantity.add(recipeSplitter[k + 1]);
                }

                Recipe recipe = new Recipe(recipeSplitter[1]);

                for (int j = 0; j < recipes.size(); j++) {
                    recipe.addChild(foodsList.findFood(recipes.get(j)), quantity.get(j));
                }

                recipe.addNutritionalInfo();
                foodsList.addFood(recipe);
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "The .csv file could not be loaded because it doesn't exist. Please provide a valid file.");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "An IO error occurred.");
        }
        return foodsList;
    }

    public ExerciseList loadExercises() {

        ExerciseList exerciseList = new ExerciseList();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Assets/exercises.csv"));

            String lineRead = "";
            while ((lineRead = br.readLine()) != null) {
                String[] lineArr = lineRead.split(",");

                if (lineArr[0].equals("e")) {
                    exerciseList.addExercise(new Exercise(lineArr[1], Double.parseDouble(lineArr[2])));
                }
            }
            br.close();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "The .csv file could not be loaded because it doesn't exist. Please provide a valid file.");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "An IO error occurred.");
        }
        return exerciseList;

    }

    /**
     * Method that reads from the log.csv files
     *
     * @param foodsList
     * @param exercisesList
     * @return
     */
    public Map<String, DietLog> loadLogs(FoodList foodsList, ExerciseList exercisesList) {
        // creating mock map to fill up object to set to user's map
        Map<String, DietLog> mockMap = new HashMap<String, DietLog>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Assets/log.csv"));

            String line = "";
            while ((line = br.readLine()) != null) {

                String[] split = line.split(",");

                String date = split[0] + "/" + split[1] + "/" + split[2];
                DietLog dl = mockMap.get(date);

                if (dl == null) {
                    mockMap.put(date, new DietLog());
                    dl = mockMap.get(date);
                }

                if (split[3] == "w") {
                    dl.setDailyWeight(202.2);
                } else if (split[3] == "c") {
                    dl.setDailyLimit(Double.parseDouble(split[4]));
                } else if (split[3] == "f") {
                    Food food = foodsList.findFood(split[4]);
                    if (food != null) {
                        dl.logFood(food, Double.parseDouble(split[5]));
                    } else {
                        dl.logFood(new BasicFood(split[4]), Double.parseDouble(split[5]));
                    }
                } else if (split[3] == "e") {
                    Exercise exer = exercisesList.getExercise(split[4]);
                    if (exer != null) {
                        dl.logExercise(exer, Double.parseDouble(split[5]));
                    } else {
                        dl.logExercise(new Exercise(split[4], Double.parseDouble(split[5])), Double.parseDouble(split[5]));
                    }
                }
            }

            br.close();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "The .csv file could not be loaded because it doesn't exist. Please provide a valid file.");
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "An IO error occurred.");
        }
        return mockMap;
    }
}

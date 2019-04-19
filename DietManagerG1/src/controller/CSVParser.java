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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CSVParser {
    private static final String FOODS_PATH = "foods.csv";
    private static final String EXERCISES_PATH = "exercises.csv";
    private static final String LOG_PATH = "log.csv";

    /*
        Given the global FoodList, write all of the foods to a CSV file.
        The path of the CSV file is specified above by the FOODS_PATH constant.
     */
    public void writeFoodList(FoodList foodList) {
        File file = new File(FOODS_PATH);
        try {
            // Check if file exists, create it if not
            file.createNewFile();

            FileWriter fw = new FileWriter(file);
            fw.write("");

            // Iterate over food list to write each food
            Object[] foodArr = foodList.getFoods().values().toArray();
            for (int i = 0; i < foodArr.length; i++) {
                Food food = (Food)foodArr[i];

                String line = (i > 0 ? "\n" : "") + food.buildCSVFormat();
                fw.append(line);
            }

            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeExerciseList(ExerciseList exerList) {
        File file = new File(EXERCISES_PATH);
        try {
            // Check if file exists, create it if not
            file.createNewFile();

            FileWriter fw = new FileWriter(file);
            fw.write("");

            // Iterate over exercise list to write each exercise
            Object[] exerArr = exerList.getAllExercises().values().toArray();
            for (int i = 0; i < exerArr.length; i++) {
               Exercise exer = (Exercise)exerArr[i];

                String line = (i > 0 ? "\n" : "") + exer.buildCSVFormat();
                fw.append(line);
            }

            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
        writes the log! more description comes later!
    */
    public void writeToLog(User user) {
        File file = new File(LOG_PATH);
        try {
            // Check if file exists, create it if not
            file.createNewFile();

            FileWriter fw = new FileWriter(file);
            fw.write("");

            /*
                Iterate over user's map of DietLog objects and build
                specific csv formatted strings to log to the file
            */
            Map<String, DietLog> dietLogs = user.getLogHistory();

            for (Map.Entry<String,DietLog> entry : dietLogs.entrySet()) {

                DietLog value = entry.getValue();

                String[] dateSplit = entry.getKey().split("/");
                String dateLine = dateSplit[0] + "," + dateSplit[1] + "," + dateSplit[2] + ",";
                Double weightData = value.getDailyWeight();
                Double calorieLimit = value.getDailyLimit();

                String weightLine = dateLine + "w," + weightData + "\n";
                fw.append(weightLine);

                String calorieLimitLine = dateLine + "c," + calorieLimit + "\n";
                fw.append(calorieLimitLine);

                for(Food food : value.getDailyFood()) {
                    String foodLine = dateLine + "f," + food.getName() + "," + food.getServing() + "\n";
                    fw.append(foodLine);
                }

                for(Exercise e: value.getDailyExercise()){
                    String exerLine = dateLine + "e," + e.getName() + "," + e.getTime() + "\n";
                    fw.append(exerLine);
                }
            }

            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public FoodList readFoodCSV() {

    	ArrayList<String> recipeList = new ArrayList<String>(); 
    	ArrayList<String> servingList = new ArrayList<String>(); 
    	ArrayList<String> basicLineList = new ArrayList<String>();
    	ArrayList<String> recipeLineList = new ArrayList<String>();
   
        FoodList fl = new FoodList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(FOODS_PATH));

            String lineRead = "";
            while( ( lineRead = br.readLine() ) != null ) {
            	
                String[] lineArr = lineRead.split(",");
                
                if ( lineArr[0].equals("b") ) {
                	basicLineList.add( lineRead );
                } else if ( lineArr[0].equals("r") ) {	
                	recipeLineList.add( lineRead );
                }
                
            }
            br.close();
            
            for ( int i = 0; i < basicLineList.size(); i++ ) {
            	
            	String[] basicSeparate = basicLineList.get(i).split(",");
            	
            	fl.addFood(new BasicFood(basicSeparate[1], Integer.parseInt(basicSeparate[2]), Double.parseDouble(basicSeparate[3]),
                        Double.parseDouble(basicSeparate[4]), Double.parseDouble(basicSeparate[5])));
            	
            }
            
            for ( int i = 0; i < recipeLineList.size(); i++ ) {
            	
            	String[] recipeSeparate = recipeLineList.get(i).split(",");
            	
            	for (int k = 2; k <= ( recipeSeparate.length - 2 ); k += 2) {
            		recipeList.add( recipeSeparate[k] );
        	        servingList.add( recipeSeparate[k + 1] );	
            	}
            	
            	Recipe recipe = new Recipe( recipeSeparate[1] );
            	
            	for ( int j = 0; j < recipeList.size(); j++ ) {
            		recipe.addChild( fl.findFood( recipeList.get(j) ), servingList.get(j) );
            	}
            	
            	recipe.addNutritionalInfo();
            	
            	fl.addFood(recipe);
            	
            }
            
        }
        catch(FileNotFoundException fnfe) {
            System.out.println("Information cannot be loaded because food.csv file does not exist.");
        }
        catch(IOException ioe) {
            System.out.println("Error with BufferedReader while reading in food.csv");
        }
        return fl;
    }

    public ExerciseList readExerciseCSV() {

        ExerciseList el = new ExerciseList();

        try {
            BufferedReader br = new BufferedReader(new FileReader(EXERCISES_PATH));

            String lineRead = "";
            while((lineRead = br.readLine()) != null) {

                String[] lineArr = lineRead.split(",");

                if(lineArr[0].equals("e")) {

                    el.addExercise(new Exercise(lineArr[1], Double.parseDouble(lineArr[2])));
                }
            }
            br.close();
        }
        catch(FileNotFoundException fnfe) {
            System.out.println("Information cannot be loaded because exercises.csv file does not exist.");
        }
        catch(IOException ioe) {
            System.out.println("Error with BufferedReader while reading in exercises.csv");
        }
        return el;

    }


    // read from the reallog.csv file and populate user list of DietLog objects
    public Map<String, DietLog> readLogCSV(FoodList fl, ExerciseList el) {
        // creating mock map to fill up object to set to user's map
        Map<String, DietLog> mockMap = new HashMap<String, DietLog>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(LOG_PATH));

            String lineRead = "";
            while((lineRead = br.readLine()) != null) {

                String[] lineArr = lineRead.split(",");


                String date = lineArr[0] + "/" + lineArr[1] + "/" + lineArr[2];
                DietLog dl = mockMap.get(date);

                if(dl == null) {
                    mockMap.put(date, new DietLog());
                    dl = mockMap.get(date);
                }

                switch (lineArr[3]) {
                    case "w":
                        dl.setDailyWeight(Double.parseDouble(lineArr[4]));
                        break;
                    case "c":
                        dl.setDailyLimit(Double.parseDouble(lineArr[4]));
                        break;
                    case "f":
                        Food food = fl.findFood(lineArr[4]);
                        if(food != null) { // means that there is this food in the global food list
                            dl.logFood(food, Double.parseDouble(lineArr[5])); // so then we want to add it to the array of foods in DietLog instanc
                        }
                        else { // food is no longer in the list because the user removed it
                            dl.logFood(new BasicFood(lineArr[4]), Double.parseDouble(lineArr[5]));
                        }
                        break;
                    case "e":
                        Exercise exer = el.getExercise(lineArr[4]);
                        if(exer != null){
                            dl.logExercise(exer, Double.parseDouble(lineArr[5]));
                        }
                        else{
                            dl.logExercise(new Exercise(lineArr[4], Double.parseDouble(lineArr[5])), Double.parseDouble(lineArr[5]));
                        }
                        break;
                }
            }

            br.close();
        }
        catch(FileNotFoundException fnfe) {
            System.out.println("Information cannot be loaded because log.csv file does not exist.");
        }
        catch(IOException ioe) {
            System.out.println("Error with BufferedReader while reading in food.csv");
        }
        return mockMap;
    }
}

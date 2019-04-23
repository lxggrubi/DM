package view;

import javax.swing.*;

import controller.DietController;
import java.awt.Dimension;
import model.DietLog;
import model.Workout;
import model.Food;

import java.awt.event.*;
import java.awt.*;
import java.text.*;
import java.util.*;

public class GUIComponent extends JFrame implements ActionListener {

    private JTextField weightTextField, calorieTextField, foodQtyTextField, timeTextField, dateTextField;
    public JComboBox foodComboBox;
    private DietController dietController;
    private Set<String> foodList;
    private Set<String> exerciseList;
    private JScrollPane consumedScrollPane, exerciseScrollPanel;
    private JPanel consumedFoodPanel, panel, panel_1, exercisesLoggedPanel;
    private JLabel calsLbl, nameLbl, weightLbl, expendedLbl, netLbl;
    private JTextField nameTextField;
    private SpringLayout springLayout;
    private JButton btnAddNewFood;
    private DietLog log = new DietLog();

    public GUIComponent(DietController dietController) {

        this.dietController = dietController;

        foodList = dietController.getFoodListKeys();
        exerciseList = dietController.getExerciseNames();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dietController.logToCSV();
                System.exit(0);
            }
        });

        springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);

        JLabel lblDietManager = new JLabel("DIET MANAGER");
        springLayout.putConstraint(SpringLayout.NORTH, lblDietManager, 10, SpringLayout.NORTH, getContentPane());
        lblDietManager.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
        getContentPane().add(lblDietManager);

        JLabel lblLogFood = new JLabel("LOG FOOD");
        springLayout.putConstraint(SpringLayout.WEST, lblLogFood, 191, SpringLayout.WEST, getContentPane());
        lblLogFood.setFont(new Font("Lucida Grande", Font.BOLD, 35));
        getContentPane().add(lblLogFood);

        JLabel lblLogWeight = new JLabel("LOG WEIGHT");
        springLayout.putConstraint(SpringLayout.NORTH, lblLogWeight, 379, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblLogWeight, 0, SpringLayout.WEST, lblLogFood);
        lblLogWeight.setFont(new Font("Lucida Grande", Font.BOLD, 35));
        getContentPane().add(lblLogWeight);

        JLabel lblLogCaloriesLimit = new JLabel("LOG CALORIES LIMIT");
        springLayout.putConstraint(SpringLayout.NORTH, lblLogCaloriesLimit, 0, SpringLayout.NORTH, lblLogWeight);
        lblLogCaloriesLimit.setFont(new Font("Lucida Grande", Font.BOLD, 35));
        getContentPane().add(lblLogCaloriesLimit);

        JLabel lblSelectFood = new JLabel("Select food:");
        springLayout.putConstraint(SpringLayout.SOUTH, lblLogFood, -17, SpringLayout.NORTH, lblSelectFood);
        lblSelectFood.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        springLayout.putConstraint(SpringLayout.NORTH, lblSelectFood, 190, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, lblSelectFood, 44, SpringLayout.WEST, getContentPane());
        getContentPane().add(lblSelectFood);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        springLayout.putConstraint(SpringLayout.NORTH, lblQuantity, 17, SpringLayout.SOUTH, lblSelectFood);
        springLayout.putConstraint(SpringLayout.EAST, lblQuantity, 0, SpringLayout.EAST, lblSelectFood);
        getContentPane().add(lblQuantity);

        JLabel lblWeight = new JLabel("Weight:");
        springLayout.putConstraint(SpringLayout.NORTH, lblWeight, 427, SpringLayout.NORTH, getContentPane());
        lblWeight.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        springLayout.putConstraint(SpringLayout.EAST, lblWeight, 0, SpringLayout.EAST, lblSelectFood);
        getContentPane().add(lblWeight);

        JLabel lblCalorieLimit = new JLabel("Calorie limit:");
        springLayout.putConstraint(SpringLayout.WEST, lblDietManager, 0, SpringLayout.WEST, lblCalorieLimit);
        springLayout.putConstraint(SpringLayout.NORTH, lblCalorieLimit, 6, SpringLayout.SOUTH, lblLogCaloriesLimit);
        lblCalorieLimit.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        getContentPane().add(lblCalorieLimit);

        foodComboBox = new JComboBox(foodList.toArray());
        springLayout.putConstraint(SpringLayout.SOUTH, foodComboBox, 0, SpringLayout.SOUTH, lblSelectFood);
        springLayout.putConstraint(SpringLayout.EAST, foodComboBox, 0, SpringLayout.EAST, lblLogFood);
        getContentPane().add(foodComboBox);

        weightTextField = new JTextField();
        springLayout.putConstraint(SpringLayout.SOUTH, weightTextField, 0, SpringLayout.SOUTH, lblWeight);
        springLayout.putConstraint(SpringLayout.EAST, weightTextField, 0, SpringLayout.EAST, lblLogWeight);
        getContentPane().add(weightTextField);
        weightTextField.setColumns(10);

        calorieTextField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, calorieTextField, 11, SpringLayout.NORTH, lblWeight);
        springLayout.putConstraint(SpringLayout.EAST, calorieTextField, 0, SpringLayout.EAST, lblLogCaloriesLimit);
        getContentPane().add(calorieTextField);
        calorieTextField.setColumns(10);

        JButton btnLogWeight = new JButton("Log Weight");
        btnLogWeight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dietController.logDailyWeight(Double.parseDouble(weightTextField.getText()), dietController.getCurrentDate());
                    JOptionPane.showMessageDialog(null, "Weight has been logged!");
                    updateValues();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Wrong weight format. Please enter in double format.");
                }
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, btnLogWeight, 6, SpringLayout.SOUTH, weightTextField);
        springLayout.putConstraint(SpringLayout.WEST, btnLogWeight, 250, SpringLayout.WEST, getContentPane());
        getContentPane().add(btnLogWeight);

        JButton btnLogCalories = new JButton("Log Calories");
        springLayout.putConstraint(SpringLayout.NORTH, btnLogCalories, 0, SpringLayout.NORTH, btnLogWeight);
        springLayout.putConstraint(SpringLayout.WEST, btnLogCalories, 442, SpringLayout.EAST, btnLogWeight);
        btnLogCalories.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dietController.logCalorieLimit(Double.parseDouble(calorieTextField.getText()), dietController.getCurrentDate());
                    JOptionPane.showMessageDialog(null, "Calorie limit has been logged!");
                    updateValues();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Wrong calorie format. Please enter in double format.");
                }
            }
        });
        getContentPane().add(btnLogCalories);

        foodQtyTextField = new JTextField();
        springLayout.putConstraint(SpringLayout.SOUTH, foodQtyTextField, 0, SpringLayout.SOUTH, lblQuantity);
        springLayout.putConstraint(SpringLayout.EAST, foodQtyTextField, 0, SpringLayout.EAST, lblLogFood);
        getContentPane().add(foodQtyTextField);
        foodQtyTextField.setColumns(10);
        foodQtyTextField.setText("1.0");

        btnAddNewFood = new JButton("Add New Food");
        springLayout.putConstraint(SpringLayout.WEST, btnAddNewFood, 739, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnAddNewFood, -77, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, btnAddNewFood, 29, SpringLayout.SOUTH, btnLogCalories);
        btnAddNewFood.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddFood newFood = new AddFood(dietController);
            }
        });
        getContentPane().add(btnAddNewFood);

        JButton btnAddNewRecipe = new JButton("Add New Recipe");
        springLayout.putConstraint(SpringLayout.NORTH, btnAddNewRecipe, 0, SpringLayout.NORTH, btnAddNewFood);
        springLayout.putConstraint(SpringLayout.WEST, btnAddNewRecipe, 555, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnAddNewRecipe, 54, SpringLayout.NORTH, btnAddNewFood);
        springLayout.putConstraint(SpringLayout.EAST, btnAddNewRecipe, -15, SpringLayout.WEST, btnAddNewFood);
        btnAddNewRecipe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddRecipeForm newRecipe = new AddRecipeForm(dietController);
            }
        });
        getContentPane().add(btnAddNewRecipe);

        JButton btnLog = new JButton("Log");
        btnLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dietController.logFood(foodComboBox.getSelectedItem().toString(),
                            Double.parseDouble(foodQtyTextField.getText()),
                            dietController.getCurrentDate());
                    resetLogPanel();
                    updateValues();
                    JOptionPane.showMessageDialog(null,
                            "Food successfully logged");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Food failed to log, please ensure your serving value is valid in double format.");
                }
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, btnLog, 6, SpringLayout.SOUTH, foodQtyTextField);
        springLayout.putConstraint(SpringLayout.EAST, btnLog, 0, SpringLayout.EAST, btnLogWeight);
        getContentPane().add(btnLog);

        JButton btnAddNewExercise = new JButton("Add New Exercise");
        springLayout.putConstraint(SpringLayout.NORTH, btnAddNewExercise, 0, SpringLayout.NORTH, btnAddNewFood);
        springLayout.putConstraint(SpringLayout.WEST, btnAddNewExercise, 356, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnAddNewExercise, -77, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnAddNewExercise, -6, SpringLayout.WEST, btnAddNewRecipe);
        btnAddNewExercise.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddExerciseForm exercise = new AddExerciseForm(dietController);
                exercise.setVisible(true);
            }
        });
        getContentPane().add(btnAddNewExercise);

        JLabel lblLogExercise = new JLabel("LOG EXERCISE:");
        springLayout.putConstraint(SpringLayout.NORTH, lblLogExercise, 0, SpringLayout.NORTH, lblLogFood);
        springLayout.putConstraint(SpringLayout.WEST, lblLogExercise, 423, SpringLayout.EAST, lblLogFood);
        lblLogExercise.setFont(new Font("Lucida Grande", Font.BOLD, 35));
        getContentPane().add(lblLogExercise);

        JLabel lblSelectExercise = new JLabel("Select exercise:");
        springLayout.putConstraint(SpringLayout.WEST, lblCalorieLimit, 0, SpringLayout.WEST, lblSelectExercise);
        springLayout.putConstraint(SpringLayout.NORTH, lblSelectExercise, 0, SpringLayout.NORTH, lblSelectFood);
        springLayout.putConstraint(SpringLayout.WEST, lblSelectExercise, 215, SpringLayout.EAST, foodComboBox);
        lblSelectExercise.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        getContentPane().add(lblSelectExercise);

        JLabel lblTimeinMinutes = new JLabel("Time (in minutes):");
        springLayout.putConstraint(SpringLayout.NORTH, lblTimeinMinutes, 0, SpringLayout.NORTH, lblQuantity);
        springLayout.putConstraint(SpringLayout.EAST, lblTimeinMinutes, 0, SpringLayout.EAST, lblSelectExercise);
        lblTimeinMinutes.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
        getContentPane().add(lblTimeinMinutes);

        JComboBox exerciseComboBox = new JComboBox(exerciseList.toArray());
        springLayout.putConstraint(SpringLayout.SOUTH, exerciseComboBox, 0, SpringLayout.SOUTH, lblSelectFood);
        springLayout.putConstraint(SpringLayout.EAST, exerciseComboBox, 0, SpringLayout.EAST, lblLogExercise);
        getContentPane().add(exerciseComboBox);

        timeTextField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, timeTextField, 11, SpringLayout.NORTH, lblQuantity);
        springLayout.putConstraint(SpringLayout.EAST, timeTextField, 0, SpringLayout.EAST, lblLogExercise);
        getContentPane().add(timeTextField);
        timeTextField.setColumns(10);

        JButton btnLogExercise = new JButton("Log Exercise");
        springLayout.putConstraint(SpringLayout.NORTH, btnLogExercise, 0, SpringLayout.NORTH, btnLog);
        springLayout.putConstraint(SpringLayout.WEST, btnLogExercise, 457, SpringLayout.EAST, btnLog);
        btnLogExercise.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dietController.logExercise(exerciseComboBox.getSelectedItem().toString(),
                            Double.parseDouble(timeTextField.getText()),
                            dietController.getCurrentDate());
                    resetExercisePanel();
                    updateValues();
                    JOptionPane.showMessageDialog(null,
                            "Exercise successfully logged");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Exercise failed to log, please ensure your exercise value is valid in double format.");
                }
            }
        });
        getContentPane().add(btnLogExercise);

        JLabel lblDate = new JLabel("Date:");
        lblDate.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        getContentPane().add(lblDate);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateTextField = new JTextField();
        dateTextField.setText(dateFormat.format(date));
        springLayout.putConstraint(SpringLayout.NORTH, dateTextField, 17, SpringLayout.SOUTH, lblDietManager);
        springLayout.putConstraint(SpringLayout.NORTH, lblDate, -4, SpringLayout.NORTH, dateTextField);
        springLayout.putConstraint(SpringLayout.EAST, lblDate, -24, SpringLayout.WEST, dateTextField);
        springLayout.putConstraint(SpringLayout.WEST, dateTextField, 0, SpringLayout.WEST, lblTimeinMinutes);
        getContentPane().add(dateTextField);
        dateTextField.setColumns(10);

        JButton btnChangeDate = new JButton("Change Date");
        springLayout.putConstraint(SpringLayout.NORTH, btnChangeDate, 17, SpringLayout.SOUTH, lblDietManager);
        btnChangeDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DateFormat df = new SimpleDateFormat("yyyy/mm/dd");
                try {
                    df.setLenient(false);
                    dietController.setCurrentDate(df.parse(dateTextField.getText()).toString());
                    updateValues();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to change date, please ensure your date is in YYYY/MM/DD format.");
                }
            }
        });
        springLayout.putConstraint(SpringLayout.WEST, lblLogCaloriesLimit, 0, SpringLayout.WEST, btnChangeDate);
        springLayout.putConstraint(SpringLayout.WEST, btnChangeDate, 6, SpringLayout.EAST, dateTextField);
        getContentPane().add(btnChangeDate);

        panel = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, panel, 131, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, panel, 103, SpringLayout.EAST, lblLogExercise);
        springLayout.putConstraint(SpringLayout.EAST, panel, -64, SpringLayout.EAST, getContentPane());
        getContentPane().add(panel);

        panel_1 = new JPanel();
        springLayout.putConstraint(SpringLayout.WEST, panel_1, 229, SpringLayout.EAST, btnLogExercise);
        springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -323, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, panel);
        getContentPane().add(panel_1);

        JPanel panel_2 = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel_2);
        springLayout.putConstraint(SpringLayout.WEST, panel_2, 103, SpringLayout.EAST, timeTextField);
        springLayout.putConstraint(SpringLayout.SOUTH, panel_2, -365, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, panel_2, 0, SpringLayout.EAST, panel);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNetCalories = new JLabel("Net calories:");
        panel_1.add(lblNetCalories);

        netLbl = new JLabel("0.0");
        panel_1.add(netLbl);
        getContentPane().add(panel_2);

        JPanel panel_3 = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, panel_2, 6, SpringLayout.SOUTH, panel_3);
        springLayout.putConstraint(SpringLayout.WEST, panel_3, 0, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.SOUTH, panel_3, -407, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, panel_3, 0, SpringLayout.EAST, panel);
        panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblCaloriesExpended = new JLabel("Calories expended:");
        panel_2.add(lblCaloriesExpended);

        expendedLbl = new JLabel("0.0");
        panel_2.add(expendedLbl);
        getContentPane().add(panel_3);

        JPanel panel_4 = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, panel_3, 6, SpringLayout.SOUTH, panel_4);
        springLayout.putConstraint(SpringLayout.SOUTH, panel, -6, SpringLayout.NORTH, panel_4);
        springLayout.putConstraint(SpringLayout.SOUTH, panel_4, -449, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, panel_4, 173, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, panel_4, 0, SpringLayout.EAST, panel);
        springLayout.putConstraint(SpringLayout.WEST, panel_4, 103, SpringLayout.EAST, exerciseComboBox);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblCaloriesConsumed = new JLabel("Calories consumed:");
        panel_3.add(lblCaloriesConsumed);

        calsLbl = new JLabel("0");
        panel_3.add(calsLbl);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblName = new JLabel("Name:");
        panel.add(lblName);

        nameLbl = new JLabel("default");
        panel.add(nameLbl);
        getContentPane().add(panel_4);
        panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblWeight_1 = new JLabel("Weight");
        panel_4.add(lblWeight_1);

        weightLbl = new JLabel("0.0 lbs");
        panel_4.add(weightLbl);

        // PANES SHIT
        consumedScrollPane = new JScrollPane();
        springLayout.putConstraint(SpringLayout.NORTH, consumedScrollPane, 24, SpringLayout.SOUTH, panel_1);
        springLayout.putConstraint(SpringLayout.WEST, consumedScrollPane, 0, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.SOUTH, consumedScrollPane, -199, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, consumedScrollPane, -64, SpringLayout.EAST, getContentPane());
        getContentPane().add(consumedScrollPane);

        consumedFoodPanel = new JPanel();
        consumedScrollPane.setViewportView(consumedFoodPanel);
        consumedFoodPanel.setLayout(new BoxLayout(consumedFoodPanel, BoxLayout.PAGE_AXIS));

        exerciseScrollPanel = new JScrollPane();
        springLayout.putConstraint(SpringLayout.EAST, btnAddNewFood, -284, SpringLayout.WEST, exerciseScrollPanel);
        springLayout.putConstraint(SpringLayout.NORTH, exerciseScrollPanel, 23, SpringLayout.SOUTH, consumedScrollPane);
        springLayout.putConstraint(SpringLayout.WEST, exerciseScrollPanel, 0, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.SOUTH, exerciseScrollPanel, -70, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, exerciseScrollPanel, -64, SpringLayout.EAST, getContentPane());
        getContentPane().add(exerciseScrollPanel);

        exercisesLoggedPanel = new JPanel();
        exerciseScrollPanel.setViewportView(exercisesLoggedPanel);
        exercisesLoggedPanel.setLayout(new BoxLayout(exercisesLoggedPanel, BoxLayout.PAGE_AXIS));

        JLabel lblNewLabel = new JLabel("Name:");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 41, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, lblDate);
        getContentPane().add(lblNewLabel);

        nameTextField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, nameTextField, 4, SpringLayout.NORTH, lblDate);
        springLayout.putConstraint(SpringLayout.WEST, nameTextField, 24, SpringLayout.EAST, lblNewLabel);
        getContentPane().add(nameTextField);
        nameTextField.setColumns(10);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dietController.setUserName(nameTextField.getText());
                updateValues();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, btnSubmit, 4, SpringLayout.NORTH, lblDate);
        springLayout.putConstraint(SpringLayout.EAST, btnSubmit, 0, SpringLayout.EAST, lblLogFood);
        getContentPane().add(btnSubmit);

        JButton btnSaveExit = new JButton("Save & Exit");
        springLayout.putConstraint(SpringLayout.NORTH, btnSaveExit, 20, SpringLayout.SOUTH, btnAddNewRecipe);
        springLayout.putConstraint(SpringLayout.WEST, btnSaveExit, 0, SpringLayout.WEST, btnAddNewRecipe);
        btnSaveExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dietController.logToCSV();
                System.exit(0);
            }
        });
        btnSaveExit.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        getContentPane().add(btnSaveExit);
        
        JButton btnViewNutrients = new JButton("View Nutrients");
        btnViewNutrients.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                    NutrientPanel nutrients = new NutrientPanel(dietController);
                    nutrients.setVisible(true);
        	}
        });
        springLayout.putConstraint(SpringLayout.NORTH, btnViewNutrients, 6, SpringLayout.SOUTH, exerciseScrollPanel);
        springLayout.putConstraint(SpringLayout.EAST, btnViewNutrients, -166, SpringLayout.EAST, getContentPane());
        getContentPane().add(btnViewNutrients);

        this.setPreferredSize(new Dimension(1600, 680));
        this.pack();
        this.setVisible(true);
    }

    private JScrollPane showFoodConsumed() {
        DietLog log = dietController.getDesiredDietLog(dietController.getCurrentDate());

        ArrayList<Food> dailyFood = log.getDailyFood();
        for (int i = 0; i < dailyFood.size(); i++) {
            Food food = dailyFood.get(i);

            JPanel innerPanel = new JPanel();
            JLabel innerLabel = new JLabel("Food: " + food.getName() + "  Servings: " + food.getServing()
                    + "  Calories: " + food.getCalories());
            JButton innerButton = new JButton("-");
            final int index = i;
            innerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dietController.removeFoodFromLog(index);
                    updateValues();
                }
            });

            innerPanel.add(innerLabel);
            innerPanel.add(innerButton);
            consumedFoodPanel.add(innerPanel);
        }

        return consumedScrollPane;
    }

    private JScrollPane showExercisesLogged() {
        DietLog log = dietController.getDesiredDietLog(dietController.getCurrentDate());

        ArrayList<Workout> dailyExercise = log.getDailyExercise();
        for (int i = 0; i < dailyExercise.size(); i++) {
            Workout exercise = dailyExercise.get(i);

            JPanel innerPanel = new JPanel();
            JLabel innerLabel = new JLabel("Exercise: " + exercise.getWorkoutName() + "  Calories Burned: " + exercise.getCaloriesSpent());

            JButton innerButton = new JButton("-");
            final int index = i;
            innerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dietController.removeExerciseFromLog(index);
                    updateValues();
                }
            });

            innerPanel.add(innerLabel);
            innerPanel.add(innerButton);
            exercisesLoggedPanel.add(innerPanel);
        }

        return exerciseScrollPanel;
    }

    public void updateValues() {
        DietLog log = dietController.getDesiredDietLog(dietController.getCurrentDate());

        nameLbl.setText(dietController.getUserName());
        weightLbl.setText(Double.toString(log.getDailyWeight()) + " lbs.");
        calsLbl.setText(Integer.toString(log.getTotalCalories()));
        expendedLbl.setText(Double.toString(log.getTotalCaloriesBurned()));
        netLbl.setText(Double.toString(log.getNetCals()));

        remove(consumedScrollPane);
        consumedScrollPane = showFoodConsumed();
        springLayout.putConstraint(SpringLayout.NORTH, consumedScrollPane, 24, SpringLayout.SOUTH, panel_1);
        springLayout.putConstraint(SpringLayout.WEST, consumedScrollPane, 0, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.SOUTH, consumedScrollPane, -199, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, consumedScrollPane, -64, SpringLayout.EAST, getContentPane());
        getContentPane().add(consumedScrollPane);

        remove(exerciseScrollPanel);
        exerciseScrollPanel = showExercisesLogged();
        springLayout.putConstraint(SpringLayout.EAST, btnAddNewFood, -284, SpringLayout.WEST, exerciseScrollPanel);
        springLayout.putConstraint(SpringLayout.NORTH, exerciseScrollPanel, 23, SpringLayout.SOUTH, consumedScrollPane);
        springLayout.putConstraint(SpringLayout.WEST, exerciseScrollPanel, 0, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.SOUTH, exerciseScrollPanel, -70, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, exerciseScrollPanel, -64, SpringLayout.EAST, getContentPane());
        getContentPane().add(exerciseScrollPanel);

        SwingUtilities.updateComponentTreeUI(this);

        consumedScrollPane.updateUI();
        exerciseScrollPanel.updateUI();

    }

    public void resetExercisePanel() {
        timeTextField.setText("");
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void resetLogPanel() {
        foodQtyTextField.setText("");
    }

    public void resetCaloriePanel() {
        calorieTextField.setText("");
    }

    public void resetWeightPanel() {
        weightTextField.setText("");
    }

    public void updatePanel() {
        foodList = dietController.getFoodListKeys();
        exerciseList = dietController.getExerciseNames();
        removeAll();

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}

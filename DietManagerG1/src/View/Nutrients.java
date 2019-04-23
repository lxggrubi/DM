package view;

import controller.DietController;
import javax.swing.*;

import model.DietLog;

public class Nutrients extends JPanel {

    private DietLog dietLog;
    private DietController dietController;
    private int fat = 150;
    private int carbs = 50;
    private int protein = 100;
    private int[] inputData = {0, 50, 100};

    public Nutrients(DietLog dietLog) {
        this.dietLog = dietLog;

        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);


        JLabel lblFats = new JLabel("Fats:");
        springLayout.putConstraint(SpringLayout.NORTH, lblFats, 25, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, lblFats, 10, SpringLayout.WEST, this);
        add(lblFats);

        JLabel lblCarbs = new JLabel("Carbs:");
        springLayout.putConstraint(SpringLayout.NORTH, lblCarbs, 17, SpringLayout.SOUTH, lblFats);
        springLayout.putConstraint(SpringLayout.WEST, lblCarbs, 0, SpringLayout.WEST, lblFats);
        add(lblCarbs);

        JLabel lblProteinsl = new JLabel("Proteins:");
        springLayout.putConstraint(SpringLayout.NORTH, lblProteinsl, 27, SpringLayout.SOUTH, lblCarbs);
        springLayout.putConstraint(SpringLayout.WEST, lblProteinsl, 10, SpringLayout.WEST, this);
        add(lblProteinsl);

        JLabel fatsPercLbl = new JLabel("New label");
        springLayout.putConstraint(SpringLayout.WEST, fatsPercLbl, 32, SpringLayout.EAST, lblFats);
        springLayout.putConstraint(SpringLayout.SOUTH, fatsPercLbl, 0, SpringLayout.SOUTH, lblFats);
        add(fatsPercLbl);

        JLabel carbsPercLbl = new JLabel("New label");
        springLayout.putConstraint(SpringLayout.NORTH, carbsPercLbl, 0, SpringLayout.NORTH, lblCarbs);
        springLayout.putConstraint(SpringLayout.EAST, carbsPercLbl, 0, SpringLayout.EAST, fatsPercLbl);
        add(carbsPercLbl);

        JLabel proteinsPercLbl = new JLabel("New label");
        springLayout.putConstraint(SpringLayout.NORTH, proteinsPercLbl, 0, SpringLayout.NORTH, lblProteinsl);
        springLayout.putConstraint(SpringLayout.WEST, proteinsPercLbl, 18, SpringLayout.EAST, lblProteinsl);
        add(proteinsPercLbl);

        update(dietLog);

        fatsPercLbl.setText(Integer.toString(fat));
        carbsPercLbl.setText(Integer.toString(carbs));
        proteinsPercLbl.setText(Integer.toString(protein));
    }

    public void update(DietLog dietLog) {
        fat = (int) dietLog.getFatPercentage();
        carbs = (int) dietLog.getCarbsPercentage();
        protein = (int) dietLog.getProteinPercentage();
        updateUI();
    }

}

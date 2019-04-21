package view;

import controller.DietController;

import javax.swing.*;

public class NutrientPanel extends JFrame {

    DietController dietController;
    Nutrients nutrients;

    public NutrientPanel(DietController dietController) {
        setSize(500, 500);
        this.dietController = dietController;
        
        nutrients = new Nutrients(dietController.getDesiredDietLog(dietController.getCurrentDate()));
        add(nutrients);

    }

    public void updatePanel() {
        nutrients.update(dietController.getDesiredDietLog(dietController.getCurrentDate()));
    }
}

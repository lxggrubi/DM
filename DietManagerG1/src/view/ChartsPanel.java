package view;

import controller.DietController;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ChartsPanel extends JPanel {

    DietController dietController;
    //BarGraph barValues;

    public ChartsPanel(DietController dietController) {

        this.dietController = dietController;

        //barValues = new BarGraph(dietController.getDesiredDietLog(dietController.getCurrentDate()));
        //add(barValues);

        TitledBorder border = new TitledBorder("Charts");
        setBorder(border);
    }

    public void updatePanel() {
        //barValues.update(dietController.getDesiredDietLog(dietController.getCurrentDate()));
    }
}

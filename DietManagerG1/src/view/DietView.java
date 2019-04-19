package view;

import controller.DietController;
import model.BasicFood;
import model.DietLog;
import model.Exercise;
import model.Food;

import java.io.Console;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class DietView {

    DietController dc;
    ViewMediator viewMediator;

    public DietView() {
        dc = new DietController();
        viewMediator = new ViewMediator();
        GUIComponent GUI = new GUIComponent(dc, viewMediator);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.*;
import view.DietView;

/**
 *
 * @author leongrubisic
 */
public class RunDietManager {

    public static void main(String[] args) {
        try {
            DietView dm = new DietView();
        } catch (Throwable e) {
            JOptionPane.showInputDialog(null, "Uncaught exception - " + e.getMessage());
            e.printStackTrace();
        }
    }
}

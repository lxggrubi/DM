package view;
import javax.swing.*;

import controller.DietController;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddExerciseForm extends JFrame {
	
	private JTextField exerciseTextField;
	private JTextField calsTextField;
	private DietController dietController;

	public AddExerciseForm(DietController dietController) {
		
		this.dietController = dietController;
		
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblAddNewExercise = new JLabel("Add New Exercise");
		springLayout.putConstraint(SpringLayout.NORTH, lblAddNewExercise, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAddNewExercise, 39, SpringLayout.WEST, getContentPane());
		lblAddNewExercise.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		getContentPane().add(lblAddNewExercise);
		
		JLabel lblExerciseName = new JLabel("Exercise name:");
		springLayout.putConstraint(SpringLayout.EAST, lblExerciseName, -285, SpringLayout.EAST, getContentPane());
		getContentPane().add(lblExerciseName);
		
		JLabel lblCalories = new JLabel("Calories:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCalories, 146, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCalories, -285, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblExerciseName, -16, SpringLayout.NORTH, lblCalories);
		getContentPane().add(lblCalories);
		
		JButton btnAddExercise = new JButton("Add Exercise");
		btnAddExercise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    String exerciseName = exerciseTextField.getText();
                    dietController.addExercise(exerciseName, Double.parseDouble(calsTextField.getText()));
                    resetPanel();
                    JOptionPane.showMessageDialog(null,
                            "Exercise successfully added: " + exerciseName);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Exercise failed to add. Please ensure all values are valid.");
                }
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, btnAddExercise, -160, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnAddExercise);
		
		exerciseTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, exerciseTextField, -5, SpringLayout.NORTH, lblExerciseName);
		springLayout.putConstraint(SpringLayout.WEST, exerciseTextField, 207, SpringLayout.WEST, getContentPane());
		getContentPane().add(exerciseTextField);
		exerciseTextField.setColumns(10);
		
		calsTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, btnAddExercise, 27, SpringLayout.SOUTH, calsTextField);
		springLayout.putConstraint(SpringLayout.NORTH, calsTextField, -5, SpringLayout.NORTH, lblCalories);
		springLayout.putConstraint(SpringLayout.WEST, calsTextField, 0, SpringLayout.WEST, exerciseTextField);
		getContentPane().add(calsTextField);
		calsTextField.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 6, SpringLayout.SOUTH, btnAddExercise);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -178, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnCancel);
	}
	
	public void resetPanel() {
		exerciseTextField.setText("");
		calsTextField.setText("");
    }

}

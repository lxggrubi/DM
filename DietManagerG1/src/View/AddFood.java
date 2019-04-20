package view;
import javax.swing.*;

import controller.DietController;

import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;

public class AddFood extends JFrame {
	
	private DietController dietController;
	private JTextField nameTextField;
	private JTextField calTextField;
	private JTextField fatsTextField;
	private JTextField carbsTextField;
	private JTextField proteinsTextField;
	private Set<String> foodList;
	
	public AddFood(DietController dietController) {
		
		this.dietController = dietController;
        foodList = dietController.getFoodListKeys();
		
		buildGUI();
	}
	
	public void buildGUI() {
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblNameOfFood = new JLabel("Name of food:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNameOfFood, 47, SpringLayout.NORTH, getContentPane());
		getContentPane().add(lblNameOfFood);
		
		JLabel lblCalories = new JLabel("Calories:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCalories, 24, SpringLayout.SOUTH, lblNameOfFood);
		springLayout.putConstraint(SpringLayout.EAST, lblNameOfFood, 0, SpringLayout.EAST, lblCalories);
		springLayout.putConstraint(SpringLayout.WEST, lblCalories, 99, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblCalories);
		
		JLabel lblFats = new JLabel("Fats:");
		springLayout.putConstraint(SpringLayout.EAST, lblFats, 0, SpringLayout.EAST, lblNameOfFood);
		getContentPane().add(lblFats);
		
		JLabel lblCarbs = new JLabel("Carbs:");
		springLayout.putConstraint(SpringLayout.EAST, lblCarbs, 0, SpringLayout.EAST, lblNameOfFood);
		getContentPane().add(lblCarbs);
		
		JLabel lblProteins = new JLabel("Proteins:");
		springLayout.putConstraint(SpringLayout.EAST, lblProteins, 0, SpringLayout.EAST, lblNameOfFood);
		getContentPane().add(lblProteins);
		
		nameTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nameTextField, -5, SpringLayout.NORTH, lblNameOfFood);
		springLayout.putConstraint(SpringLayout.WEST, nameTextField, 6, SpringLayout.EAST, lblNameOfFood);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		calTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, calTextField, -5, SpringLayout.NORTH, lblCalories);
		springLayout.putConstraint(SpringLayout.WEST, calTextField, 6, SpringLayout.EAST, lblCalories);
		getContentPane().add(calTextField);
		calTextField.setColumns(10);
		
		fatsTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, fatsTextField, 16, SpringLayout.SOUTH, calTextField);
		springLayout.putConstraint(SpringLayout.WEST, fatsTextField, 6, SpringLayout.EAST, lblFats);
		springLayout.putConstraint(SpringLayout.NORTH, lblFats, 5, SpringLayout.NORTH, fatsTextField);
		getContentPane().add(fatsTextField);
		fatsTextField.setColumns(10);
		
		carbsTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, carbsTextField, 6, SpringLayout.EAST, lblCarbs);
		springLayout.putConstraint(SpringLayout.NORTH, lblCarbs, 5, SpringLayout.NORTH, carbsTextField);
		springLayout.putConstraint(SpringLayout.NORTH, carbsTextField, 20, SpringLayout.SOUTH, fatsTextField);
		getContentPane().add(carbsTextField);
		carbsTextField.setColumns(10);
		
		proteinsTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, proteinsTextField, 6, SpringLayout.EAST, lblProteins);
		springLayout.putConstraint(SpringLayout.NORTH, lblProteins, 5, SpringLayout.NORTH, proteinsTextField);
		springLayout.putConstraint(SpringLayout.NORTH, proteinsTextField, 19, SpringLayout.SOUTH, carbsTextField);
		getContentPane().add(proteinsTextField);
		proteinsTextField.setColumns(10);
		
		JButton btnAddFood = new JButton("Add Food");
		btnAddFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    String foodName = nameTextField.getText();
                    dietController.addBasicFood(foodName,
                        Integer.parseInt(calTextField.getText()),
                        Double.parseDouble(carbsTextField.getText()),
                        Double.parseDouble(proteinsTextField.getText()),
                        Double.parseDouble(fatsTextField.getText()));
                    resetPanel();
                    JOptionPane.showMessageDialog(null,
                            "Food successfully added: " + foodName);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Food failed to add. Please ensure all values are valid.");
                }
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnAddFood, 30, SpringLayout.SOUTH, proteinsTextField);
		springLayout.putConstraint(SpringLayout.EAST, btnAddFood, -164, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnAddFood);
		
		JButton btnCancel = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancel, 6, SpringLayout.SOUTH, btnAddFood);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -164, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnCancel);
                
                setVisible(true);
                pack();
	}
	
	 public void resetPanel() {
	        foodList = dietController.getFoodListKeys();

	        nameTextField.setText("");
	        calTextField.setText("");
	        fatsTextField.setText("");
	        carbsTextField.setText("");
	        proteinsTextField.setText("");
	        SwingUtilities.updateComponentTreeUI(this);
	 }

}

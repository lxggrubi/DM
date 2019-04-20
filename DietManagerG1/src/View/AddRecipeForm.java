package view;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;

import controller.DietController;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddRecipeForm extends JFrame {
	
	private DietController dietController;
    private Set<String> foodList;
    private JTextField qtyTextField;
    private JTextField recipeNameTextField;
    JPanel ingredientPanel;
    JPanel recipePanel;
    JPanel mainPanel;
	
	public AddRecipeForm(DietController dietController) {
		
		this.dietController = dietController;

        foodList = dietController.getFoodListKeys();
		
		buildGUI();
	}
	
	public JPanel addIngredientPanel() {
        ingredientPanel.setLayout(new FlowLayout());

        JLabel ingredientLabel = new JLabel("Food");
        JComboBox ingredientComboBox = new JComboBox(foodList.toArray());
        JLabel servingLabel = new JLabel("Quantity");
        JTextField servingField = new JTextField();
        servingField.setText("1.0");
        servingField.setPreferredSize(new Dimension(30, 20));
        JButton removeItemButton = new JButton("-");
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recipePanel.remove(ingredientPanel);
                recipePanel.updateUI();
            }
        });

        ingredientPanel.add(ingredientLabel);
        ingredientPanel.add(ingredientComboBox);
        ingredientPanel.add(servingLabel);
        ingredientPanel.add(servingField);
        ingredientPanel.add(removeItemButton);

        recipePanel.add(ingredientPanel);
        recipePanel.updateUI();
        return ingredientPanel;
	}
	
	private void addRecipe() {
        ArrayList<String> recipeList = new ArrayList<String>();
        ArrayList<String> servingList = new ArrayList<String>();
        for (Component panel : recipePanel.getComponents()) {
            JPanel innerPanel = (JPanel)panel;
            recipeList.add(((JComboBox)innerPanel.getComponent(1)).getSelectedItem().toString());
            servingList.add(((JTextField)innerPanel.getComponent(3)).getText());
        }
        dietController.addRecipe(recipeNameTextField.getText(),recipeList,servingList);
        resetPanel();
    }
	
	public void resetPanel() {
        foodList = dietController.getFoodListKeys();

        recipePanel.removeAll();
        recipeNameTextField.setText("");
        addIngredientPanel();
        SwingUtilities.updateComponentTreeUI(this);
    }
	
	public void buildGUI() {
		
		mainPanel = new JPanel();
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblAddNewRecipe = new JLabel("Add New Recipe");
		springLayout.putConstraint(SpringLayout.NORTH, lblAddNewRecipe, 5, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblAddNewRecipe, -72, SpringLayout.EAST, getContentPane());
		lblAddNewRecipe.setFont(new Font("Lucida Grande", Font.BOLD, 35));
		getContentPane().add(lblAddNewRecipe);
		
		JLabel lblRecipeName = new JLabel("Recipe Name:");
		springLayout.putConstraint(SpringLayout.WEST, lblRecipeName, 77, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblRecipeName, -377, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(lblRecipeName);
		
		JButton addMoreButton = new JButton("+");
		springLayout.putConstraint(SpringLayout.WEST, addMoreButton, 187, SpringLayout.WEST, getContentPane());
		addMoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addIngredientPanel();
			}
		});
		getContentPane().add(addMoreButton);
		
		JButton btnAddNewRecipe = new JButton("Add New Recipe");
		springLayout.putConstraint(SpringLayout.SOUTH, addMoreButton, -6, SpringLayout.NORTH, btnAddNewRecipe);
		springLayout.putConstraint(SpringLayout.WEST, btnAddNewRecipe, 148, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddNewRecipe, -10, SpringLayout.SOUTH, getContentPane());
		btnAddNewRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String recipeName = recipeNameTextField.getText();
                try {
                    addRecipe();
                    JOptionPane.showMessageDialog(null,
                            "Recipe successfully added: " + recipeName);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            ex.getMessage());
                }

			}
		});
		getContentPane().add(btnAddNewRecipe);
		
		recipeNameTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, recipeNameTextField, -5, SpringLayout.NORTH, lblRecipeName);
		springLayout.putConstraint(SpringLayout.WEST, recipeNameTextField, 57, SpringLayout.EAST, lblRecipeName);
		springLayout.putConstraint(SpringLayout.EAST, recipeNameTextField, -104, SpringLayout.EAST, getContentPane());
		getContentPane().add(recipeNameTextField);
		recipeNameTextField.setColumns(10);
		
		JScrollPane recipeScrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, recipeScrollPane, 6, SpringLayout.SOUTH, recipeNameTextField);
		springLayout.putConstraint(SpringLayout.WEST, recipeScrollPane, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, recipeScrollPane, -6, SpringLayout.NORTH, addMoreButton);
		springLayout.putConstraint(SpringLayout.EAST, recipeScrollPane, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(recipeScrollPane);
		
		qtyTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, qtyTextField, 99, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, qtyTextField, 428, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, qtyTextField, 0, SpringLayout.EAST, addMoreButton);
		getContentPane().add(qtyTextField);
		qtyTextField.setColumns(2);
		
		recipePanel = new JPanel();
		recipeScrollPane.setViewportView(recipePanel);
		recipePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ingredientPanel = new JPanel();
		recipePanel.add(ingredientPanel);
		ingredientPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                
                this.setPreferredSize(new Dimension(450, 500));
                this.pack();
                this.setVisible(true);
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package recipebookapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author samiul
 */
public class mainController {

    @FXML
    private ListView<Recipe> recipeListView;

    @FXML
    private void addRecipe() {
        // Display a dialog to get recipe details
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Recipe");
        dialog.setHeaderText("Enter recipe details:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            // Create a new recipe and add it to the list
            Recipe recipe = new Recipe(name, "", ""); // Initialize with empty ingredients and instructions
            recipeListView.getItems().add(recipe);
        });
    }

    @FXML
    private void editRecipe() {
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            // Display a dialog to edit recipe details
            TextInputDialog dialog = new TextInputDialog(selectedRecipe.getName());
            dialog.setTitle("Edit Recipe");
            dialog.setHeaderText("Enter updated recipe details:");
            dialog.setContentText("Name:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {
                selectedRecipe.setName(name);
                // Update the ListView display
                recipeListView.refresh();
            });
        } else {
            showAlert("No Recipe Selected", "Please select a recipe to edit.");
        }
    }

    @FXML
    private void deleteRecipe() {
        Recipe selectedRecipe = recipeListView.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            // Display a confirmation dialog before deleting
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Recipe");
            alert.setHeaderText("Are you sure you want to delete this recipe?");
            alert.setContentText(selectedRecipe.getName());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                recipeListView.getItems().remove(selectedRecipe);
            }
        } else {
            showAlert("No Recipe Selected", "Please select a recipe to delete.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

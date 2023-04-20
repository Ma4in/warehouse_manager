package com.warehouse_manager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;



public class mainController implements Initializable{
    
    @FXML TableView<Unit> mainTable;

    @FXML TableColumn<Unit, Integer> idCol;
    @FXML TableColumn<Unit, String> nameCol;
    @FXML TableColumn<Unit, String> departmentCol;
    @FXML TableColumn<Unit, Integer> countCol;

    @FXML TextField IDInput;
    @FXML TextField NameInput;
    @FXML TextField DepartmentInput;
    @FXML TextField CountInput;

    ObservableList<Unit> list = FXCollections.observableArrayList(
        new Unit(1, "Mayo", "Souse", 30),
        new Unit(2, "Soy", "Beans", 100),
        new Unit(3, "AK-47", "Wearpons", 3)
    );

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        idCol.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("unitID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitName"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitDepartment"));
        countCol.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("unitCount"));

        mainTable.setItems(list);
    }


    public void deleteUnit(){
        Unit selecedUnit = mainTable.getSelectionModel().getSelectedItem();
        mainTable.getItems().remove(selecedUnit);
    }

    public void addUnit(){
        try {
            List<Integer> columnData = new ArrayList<>();
            for (Unit item : mainTable.getItems()) {
                columnData.add(idCol.getCellObservableValue(item).getValue());
            }

            Integer newId = Integer.parseInt(IDInput.getText());

            if (columnData.contains(newId)){
                showAlert("Add error", "Set distinct ID");
                clearInput();
                return;
            }

            Integer newCount = Integer.parseInt(CountInput.getText());
            Unit newUnit = new Unit(newId, NameInput.getText(), DepartmentInput.getText(), newCount);
    
            mainTable.getItems().add(newUnit);

            clearInput();
        } catch (Exception e) {
            showAlert("Add error", e.getMessage());
            clearInput();
        }
       
    }

    private void clearInput(){
        IDInput.setText(null);
        NameInput.setText(null);
        DepartmentInput.setText(null);
        CountInput.setText(null);
    }

    private void showAlert(String errorType, String context){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(errorType);
        alert.setContentText(context);
        alert.showAndWait();
    }
}
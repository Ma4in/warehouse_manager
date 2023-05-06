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
import javafx.scene.control.RadioButton;
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
    @FXML RadioButton hardSearch;

    Boolean userPermithion = ConnectPSQL.adminUserType; 

    ObservableList<Unit> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        idCol.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("unitID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitName"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitDepartment"));
        countCol.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("unitCount"));

        try {
            list = ConnectPSQL.getTable("SELECT * FROM units;");

        } catch (Exception e) {
            showAlert("Connection error", e.getMessage());
        }

        mainTable.setItems(list);
    }


    public void deleteUnit(){
        if (userPermithion){
            Unit selecedUnit = mainTable.getSelectionModel().getSelectedItem();
            mainTable.getItems().remove(selecedUnit);
        }
    }

    public void addUnit(){
        if (userPermithion)
        {  try {
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
            
            int [] cap = ConnectPSQL.getСapacity("L");
            if (cap[0] - newCount < 0){
                showAlert("Add error", "Department overfilled. Empty space : " + cap[0]);
                return;
            }
            
         

            mainTable.getItems().add(newUnit);

            clearInput();
        } catch (Exception e) {
            showAlert("Add error", e.getMessage());
            clearInput();
        }
       }
    }

    public void clearTable(){
        ObservableList<Unit> tempEmpty = FXCollections.observableArrayList();
        mainTable.setItems(tempEmpty);
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


    public void showInfo(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("GNOME");
        alert.setHeaderText("Купил мужик шляпу, а она ему как раз.");
        alert.showAndWait();
    }

    public void findUnit() throws Exception{
        String zapros = ("SELECT * FROM units ");

        if (!IDInput.getText().equals("") || !NameInput.getText().equals("") || 
            !DepartmentInput.getText().equals("") || !CountInput.getText().equals("")) {
            zapros += "WHERE";}
            else return;

        List<String> labelsList = new ArrayList<String>();

        String idSelect;
        if (!IDInput.getText().equals("")) {
            idSelect = " unitid = " + IDInput.getText();
            labelsList.add(idSelect);}
        String nameSelect;
        if (!NameInput.getText().equals("")) {
            nameSelect = " unitname = '" + NameInput.getText() + "'";
            labelsList.add(nameSelect);}
        String departmentSelect;
        if (!DepartmentInput.getText().equals("")) {
            departmentSelect = " unitdepartment = '" + DepartmentInput.getText() + "'";
            labelsList.add(departmentSelect);}
        String countSelect;
        if (!CountInput.getText().equals("")) {
            countSelect = " unitcount = " + CountInput.getText();
            labelsList.add(countSelect);}

        String searchType = " OR";
        if (hardSearch.isSelected()) searchType = " AND";
        for (String str : labelsList) {
            zapros += str + searchType;
        }

        mainTable.setItems(ConnectPSQL.getTable(zapros.substring(0, zapros.length()-searchType.length())));
        
    }

    public void restoreTable() throws Exception{
        mainTable.setItems(ConnectPSQL.getTable("SELECT * FROM units ")); 
    }

    public void saveTable() throws Exception{
        if (userPermithion) ConnectPSQL.saveTable(mainTable.getItems());
    }

    public void exitProgram(){
        System.exit(0);
    }

    public void addNewUser() throws Exception{
        
        if (userPermithion) App.setRoot("register");
        
    }

}
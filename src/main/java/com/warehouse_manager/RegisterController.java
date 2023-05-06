package com.warehouse_manager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RegisterController implements Initializable {

    @FXML RadioButton adminRadioButton;
    @FXML TextField loginField;
    @FXML PasswordField passField;
    @FXML PasswordField passConfirmField;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        App.changeSize(292, 400, false);
        
    }

    public void addUser() throws Exception{
        
        Boolean cheakValues = true;

        if (loginField.getText().length() < 6){
            cheakValues = false;
            Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Password is to short");
                alert.showAndWait();
        } 

        if (!passField.getText().equals(passConfirmField.getText()) || passField.getText().length() < 6){
            cheakValues = false;
            Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Password mismatch");
                alert.showAndWait();
        }
        
        if (cheakValues){
            try {
                String password = HSCRP.setHesh(passField.getText(), "k");
                ConnectPSQL.setZapros(String.format("insert into authorize ( userLogin, userPassword, userPermission) values ('%s', '%s', %b);", 
                    loginField.getText(), password, adminRadioButton.isSelected()
                ));

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("User added");
                alert.showAndWait();
                App.setRoot("manager");
                App.changeSize(700, 500, true);

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("User already exists");
                alert.showAndWait();
            }
                
        }
        
    }
}

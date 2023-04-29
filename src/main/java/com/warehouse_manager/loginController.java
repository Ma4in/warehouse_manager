package com.warehouse_manager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class loginController {

    @FXML TextField loginField;
    @FXML PasswordField passField;

    String keyWord = "k";

    @FXML
    private void signIn() throws Exception {
        String login = loginField.getText();
        String pass =  passField.getText();
        if(!login.equals(null) && !pass.equals(null))

            pass = HSCRP.setHesh(pass, keyWord);
            if(ConnectPSQL.getLogin(login, pass))
            {
                App.setRoot("manager");
                App.changeSize(700, 500, true);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Incorrect login or password");
                alert.showAndWait();
            }
    }
}

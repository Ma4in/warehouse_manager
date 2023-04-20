package com.warehouse_manager;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class loginController {

    @FXML TextField loginField;

    @FXML
    private void signIn() throws IOException {
        loginField.setText("HI");
        App.setRoot("manager");
        App.changeSize(700, 500, true);
    }
}

package com.warehouse_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
        stage.setTitle("Warehouse Manager");
        stage.getIcons().add(new Image("wha.png"));
        stage.show();
        stage.setResizable(false);
    }

    static void changeSize(int width, int height, boolean resizable){
      
        stage.setMinHeight(height);
        stage.setMinWidth(width);
        stage.setResizable(resizable);
        stage.setHeight(height);
        stage.setWidth(width);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        URL fxmlLocation = App.class.getResource(fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
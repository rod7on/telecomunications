package com.comunications.telecomunicationsui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;

public class MainPage extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Image image = new Image("file:///C:/Users/ME/Documents/Telecomunicatii/telecomunications-ui/src/main/resources/img/logo.png");


        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene  = new Scene(root);
        //scene.getStylesheets().add("style2.css");
        //stage.setMaximized(true);
        stage.getIcons().add(image);
        /*stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initStyle(StageStyle.UTILITY);
        stage.initStyle(StageStyle.UNDECORATED);*/
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.comunications.telecomunicationsui;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private Button back;

    @FXML
    private VBox contentVBox;

    @FXML
    private Button delete1;

    @FXML
    private Button delete2;

    @FXML
    private Button delete3;

    @FXML
    private Button delete4;

    @FXML
    private VBox deleteSaveButtons;


    @FXML
    private HBox menuHboxItems;

    @FXML
    private Pane personeImage;

    @FXML
    private Pane personeImageWomen;

    @FXML
    private Pane personeProfile1;

    @FXML
    private Pane personeProfile2;

    @FXML
    private Pane personeProfile3;

    @FXML
    private Pane personeProfile4;

    @FXML
    private Button save1;

    @FXML
    private Button save2;

    @FXML
    private Button save3;

    @FXML
    private Button save4;

    @FXML
    private Button slideBar;

    @FXML
    private Button slideBar2;

    @FXML
    private Button slideBar3;

    @FXML
    private Button slideBar4;

    @FXML
    private VBox slider;
    @FXML
    private Button Logout;
    @FXML
    private Button profile;
    @FXML
    private Button profileslideBar;
    //declararea variabilelor generarea indexului random
    Random rand = new Random(2);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        back.setOnMouseClicked(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("login.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
        Logout.setOnMouseClicked( event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });

        //spre profil
        profile.setOnMouseClicked(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("profile.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });

        profileslideBar.setOnMouseClicked(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("profile.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });


        personeImage.setVisible(true);
        slider.setTranslateX(-175);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-175);

            slide.setOnFinished((ActionEvent e) -> {
                personeImageWomen.setVisible(false);
                personeImage.setVisible(false);

                Menu.setVisible(false);
                MenuBack.setVisible(true);
                menuHboxItems.setVisible(false);
            });
        });

        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-175);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                int randomNumber = rand.nextInt(2);
                //alegem poze aleatorii
                if (randomNumber==1) {
                    personeImage.setVisible(true);
                }else{
                    personeImageWomen.setVisible(true);
                }

                Menu.setVisible(true);
                MenuBack.setVisible(false);
                menuHboxItems.setVisible(true);
            });
        });
        //Afisam butoanele de save si delete pentru primul pane
        personeProfile1.setOnMouseEntered(event -> {
            delete4.setVisible(false);
            save4.setVisible(false);
            delete2.setVisible(false);
            save2.setVisible(false);
            delete3.setVisible(false);
            save3.setVisible(false);

            delete1.setVisible(true);
            save1.setVisible(true);
        });
        //Afisam butoanele de save si delete pentru al doilea pane
        personeProfile2.setOnMouseEntered(event -> {
            delete4.setVisible(false);
            save4.setVisible(false);
            delete3.setVisible(false);
            save3.setVisible(false);
            delete1.setVisible(false);
            save1.setVisible(false);

            delete2.setVisible(true);
            save2.setVisible(true);
        });
        personeProfile3.setOnMouseEntered(event -> {
            delete4.setVisible(false);
            save4.setVisible(false);
            delete2.setVisible(false);
            save2.setVisible(false);
            delete1.setVisible(false);
            save1.setVisible(false);

            delete3.setVisible(true);
            save3.setVisible(true);
        });
        personeProfile4.setOnMouseEntered(event -> {
            delete3.setVisible(false);
            save3.setVisible(false);
            delete2.setVisible(false);
            save2.setVisible(false);
            delete1.setVisible(false);
            save1.setVisible(false);

            delete4.setVisible(true);
            save4.setVisible(true);
        });
    }
}



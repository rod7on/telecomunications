package com.comunications.telecomunicationsui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label loginLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordText;

    @FXML
    private Button registerButton;

    @FXML
    private Button registerLoginButton;

    @FXML
    private CheckBox showPasswordCheckbox;

    @FXML
    private TextField username;

    @FXML
    public void initializer() {
        if (passwordField != null) {
            verify(); // Verificăm câmpurile și aplicăm efectul de glow corespunzător
            addListener(passwordField);
            addListener(username);
        }
    }

    public void toLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void toRegister(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void showPassword (ActionEvent event) {
        if (showPasswordCheckbox.isSelected()) {
            passwordText.setText(passwordField.getText());
            passwordText.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordText.getText());
        passwordField.setVisible(true);
        passwordText.setVisible(false);
    }

    @FXML
    void toMenu(ActionEvent event) throws IOException {
        boolean allFieldsFilled = allFieldsFilled(passwordField.getText(), username.getText());
        //Verificam daca campurile sunt completate pentru a ne putea conecta
        if(allFieldsFilled) {
                Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erroare");
            alert.setHeaderText("You not complete all the fields");
            alert.setContentText("Make sure you complete all all fields...");

            // Afișăm alerta și așteptăm până când utilizatorul apasă un buton
            alert.showAndWait();
        }
    }

    private boolean allFieldsFilled(String text, String text1) {
        // Metoda care verifică dacă toate câmpurile sunt completate
        String[] fields= {text, text1};
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @FXML
    public void verify() {
        boolean allFieldsFilled = allFieldsFilled(passwordField.getText(),username.getText());

        if (allFieldsFilled) {
            loginLabel.getStyleClass().removeAll("red-glow");
            loginLabel.getStyleClass().add("green-glow"); // Aplicăm clasa pentru efectul de glow verde
        } else {
            loginLabel.getStyleClass().removeAll("green-glow");
            loginLabel.getStyleClass().add("red-glow"); // Aplicăm clasa pentru efectul de glow roșu
        }
    }

    private void addListener(TextField textField) {
        // Adăugăm un listener pentru câmpurile de text pentru a detecta modificările și a apela metoda verify
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            verify(); // Apelăm metoda verify când se detectează o modificare în câmpurile de text
        });
}}
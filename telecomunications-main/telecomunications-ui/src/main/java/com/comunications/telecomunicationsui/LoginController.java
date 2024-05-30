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
import java.sql.*;


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
    Boolean loginValidate = false;

    @FXML
    public void initialize() {
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

        // Apelarea metodei care verifica utilizatorul in baza de date
        int userId = verifyUser(username.getText(), passwordField.getText());

        // Verificam daca campurile sunt completate pentru a ne putea conecta
        if (allFieldsFilled) {
            if (loginValidate) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent root = loader.load();
                //injectarea userului in controller
                Menu menuController = loader.getController();
                menuController.setUserId(userId);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Eroare");
                alert.setHeaderText("Username sau parola incorecta");
                alert.setContentText("Asigură-te că username-ul și parola sunt corecte.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Eroare");
            alert.setHeaderText("Nu ai completat toate câmpurile");
            alert.setContentText("Asigură-te că ai completat toate câmpurile.");
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
    }

    private int verifyUser(String usernameUser, String passwordUser) {
        int userId = -1;
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM Utilizatori WHERE Username = ? AND PasswordUser = ?")) {
            statement.setString(1, usernameUser);
            statement.setString(2, passwordUser);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    loginValidate = true;
                    userId = resultSet.getInt("id");
                } else {
                    loginValidate = false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
package com.comunications.telecomunicationsui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.sql.*;

public class RegisterController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int userId;

    @FXML
    private TextField number;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField passwordText1;

    @FXML
    private Button registerButton;

    @FXML
    private Label registerLabel;

    @FXML
    private Button registerLoginButton;

    @FXML
    private CheckBox showPasswordCheckbox;

    @FXML
    private TextField username;

    @FXML
    public void initialize() {
        verify(); // Verificăm câmpurile și aplicăm efectul de glow corespunzător

        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), null, c -> {
            if (c.getControlNewText().matches("\\d*")) {
                return c;
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Only numeric digits allowed");
            alert.setContentText("Make sure that you entered only numeric digits");

            // Afișăm alerta și așteptăm până când utilizatorul apasă un buton
            alert.showAndWait();
            return null;
        });

        // Setează TextFormatter-ul pe TextField
        number.setTextFormatter(textFormatter);

        addListener(passwordField);
        addListener(passwordField1);
        addListener(username);
        addListener(number);
    }


    @FXML
    void showPassword(ActionEvent event) {
        if (showPasswordCheckbox.isSelected()) {
            passwordText.setText(passwordField.getText());
            passwordText.setVisible(true);
            passwordField.setVisible(false);

            passwordText1.setText(passwordField1.getText());
            passwordText1.setVisible(true);
            passwordField1.setVisible(false);
            return;
        }
        passwordField.setText(passwordText.getText());
        passwordField.setVisible(true);
        passwordText.setVisible(false);

        passwordField1.setText(passwordText1.getText());
        passwordField1.setVisible(true);
        passwordText1.setVisible(false);
    }

    @FXML
    public void toLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void toMenu(ActionEvent event) throws IOException {
        boolean allFieldsFilled = allFieldsFilled(passwordField.getText(), passwordField1.getText(), username.getText(), number.getText());

        // Verificăm dacă campurile sunt completate pentru a ne putea conecta
        if(allFieldsFilled) {
            if(passwordField.getText().equals(passwordField1.getText())){
                // Apelarea metodei de creare a utilizatorului
                createUser();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent root = loader.load();

                // Injectarea utilizatorului în controller
                Menu menuController = loader.getController();
                menuController.setUserId(userId);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erroare");
                alert.setHeaderText("The passwords that you give are not the same");
                alert.setContentText("Make sure you the passwords are the same...");

                // Afisam alerta
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erroare");
            alert.setHeaderText("You not complete all the fields");
            alert.setContentText("Make sure you complete all all fields...");

            // Afișăm alerta și așteptăm până când utilizatorul apasă un buton
            alert.showAndWait();
        }
    }

    // Metodă pentru crearea utilizatorului în baza de date
    private void createUser() {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Utilizatori (Username, TelephoneNumber, PasswordUser) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username.getText());
            statement.setString(2, number.getText());
            statement.setString(3, passwordField.getText());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        userId = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void verify() {
        boolean allFieldsFilled = allFieldsFilled(passwordField.getText(), passwordField1.getText(), username.getText(), number.getText());

        if (allFieldsFilled) {
            registerLabel.getStyleClass().removeAll("red-glow");
            registerLabel.getStyleClass().add("green-glow"); // Aplicăm clasa pentru efectul de glow verde
        } else {
            registerLabel.getStyleClass().removeAll("green-glow");
            registerLabel.getStyleClass().add("red-glow"); // Aplicăm clasa pentru efectul de glow roșu
        }
    }

    private boolean allFieldsFilled(String... fields) {
        // Metoda care verifică dacă toate câmpurile sunt completate
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void addListener(TextField textField) {
        // Adăugăm un listener pentru câmpurile de text pentru a detecta modificările și a apela metoda verify
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            verify(); // Apelăm metoda verify când se detectează o modificare în câmpurile de text
        });
    }
}

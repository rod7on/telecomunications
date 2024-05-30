package com.comunications.telecomunicationsui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ProfileController implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private TextField Name;

    @FXML
    private Button back;

    @FXML
    private Button backToDashboard;

    @FXML
    private Button changePhotoButton;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField email;

    @FXML
    private Button logout;

    @FXML
    private TextField number;

    @FXML
    private Button privacy;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Button saveAll;

    @FXML
    private Button slideBar4;

    @FXML
    private VBox slider;

    @FXML
    private TextField username;

    private int userId;

    public ProfileController(int userId) {
        this.userId = userId;
    }

    public ProfileController() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        changePhotoButton.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp")
            );
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                try {
                    Image image = new Image(new FileInputStream(selectedFile));
                    profilePicture.setImage(image);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    public void toMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void privacyAndPolicy(ActionEvent event) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Privacy and Policy");

        Label iconLabel = new Label("\uD83D\uDCDD");
        Label textLabel = new Label(dataFile());

        VBox vbox = new VBox(iconLabel, textLabel);

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        dialog.getDialogPane().setContent(scrollPane);

        dialog.getDialogPane().setPrefSize(400, 300);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.showAndWait();
    }

    public String dataFile() {
        String content = "";
        try {
            File file = new File("C:/Users/jonym/OneDrive/Documents/telecomunications-main/telecomunications-ui/src/main/java/com/comunications/telecomunicationsui/privasyAndPolicy.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                content += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return content;
    }

    @FXML
    void backToDasboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        //injectarea userului in controller
        Menu menuController = loader.getController();
        menuController.setUserId(userId);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void saveAllData(ActionEvent event) {
        int userId = getUserId();
        String name = Name.getText();
        String usernameValue = username.getText();
        String dob = (dateOfBirth.getValue() != null) ? dateOfBirth.getValue().toString() : null;
        String numberValue = number.getText();
        String emailValue = email.getText();

        String updateQuery = "UPDATE Utilizatori SET Nume = ?, Username = ?, DateOfBirth = ?, TelephoneNumber = ?, Email = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setString(1, name);
            pstmt.setString(2, usernameValue);
            pstmt.setString(3, dob);
            pstmt.setString(4, numberValue);
            pstmt.setString(5, emailValue);
            pstmt.setInt(6, userId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User information updated successfully.");
            } else {
                System.out.println("User not found or no change in data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserId(int userId) {
        this.userId = userId;
        loadUserData();  // Încarcă datele utilizatorului după setarea ID-ului
    }

    public int getUserId() {
        return userId;
    }

    //incarcarea datelor utilizatorului
    private void loadUserData() {
        String query = "SELECT Nume, Username, DateOfBirth, TelephoneNumber, Email FROM Utilizatori WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Name.setText(rs.getString("Nume"));
                username.setText(rs.getString("Username"));
                dateOfBirth.setValue(rs.getDate("DateOfBirth") != null ? rs.getDate("DateOfBirth").toLocalDate() : null);
                number.setText(rs.getString("TelephoneNumber"));
                email.setText(rs.getString("Email"));
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

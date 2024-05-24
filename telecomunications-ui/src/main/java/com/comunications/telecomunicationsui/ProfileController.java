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
import java.util.ResourceBundle;
import java.util.Scanner;


public class ProfileController implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Button back;

    @FXML
    private Button changePhotoButton;

    @FXML
    private Button logout;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Button slideBar;

    @FXML
    private Button slideBar3;

    @FXML
    private Button slideBar4;

    @FXML
    private VBox slider;
    @FXML
    private Button privacy;
    @FXML
    private Button backToDashboard;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        back.setOnMouseClicked(event -> {
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
        // Afișăm dialogul cu informațiile necesare
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Privact and Policy");

        // Adăugăm o pictogramă simplă
        Label iconLabel = new Label("\uD83D\uDCDD"); // (Notepad icon)
        Label textLabel = new Label(dataFile());

        VBox vbox = new VBox(iconLabel, textLabel);

        // Creăm un ScrollPane și adăugăm VBox în el
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        dialog.getDialogPane().setContent(scrollPane);

        // Setăm dimensiunea dialogului
        dialog.getDialogPane().setPrefSize(400, 300);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.showAndWait();
    }

    public String dataFile() {

        //citim datele dinn fisier
        String content = "";
        try {
            File file = new File("C:/Users/ME/Documents/Telecomunicatii/telecomunications-ui/src/main/java/com/comunications/telecomunicationsui/privasyAndPolicy.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                content += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String string = content.toString();
        return string;
    }

    //back to dashborad
    @FXML
    void backToDasboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

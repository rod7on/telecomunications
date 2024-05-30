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
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button Logout;

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
    private TextField namePers1;

    @FXML
    private TextField namePers2;

    @FXML
    private TextField namePers3;

    @FXML
    private TextField namePers4;

    @FXML
    private TextField numberPers1;

    @FXML
    private TextField numberPers2;

    @FXML
    private TextField numberPers3;

    @FXML
    private TextField numberPers4;

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
    private Button profile;

    @FXML
    private Button profileslideBar;

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
    private Button slideBar4;

    @FXML
    private VBox slider;
    private int userId;

    // declararea variabilelor pentru ID-urile contactelor
    private String contactId1;
    private String contactId2;
    private String contactId3;
    private String contactId4;

    // declararea variabilelor pentru generarea unui index aleatoriu
    Random rand = new Random(2);

    public Menu(int userId) {
        this.userId = userId;
    }

    public Menu() {}

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

        Logout.setOnMouseClicked(event -> {
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

        // spre profil
        profile.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
                Parent root = loader.load();
                //injectam dependetele inn contrller
                ProfileController profileController = loader.getController();
                profileController.setUserId(userId);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
                // alegem poze aleatorii
                if (randomNumber == 1) {
                    personeImage.setVisible(true);
                } else {
                    personeImageWomen.setVisible(true);
                }

                Menu.setVisible(true);
                MenuBack.setVisible(false);
                menuHboxItems.setVisible(true);
            });
        });

        // Afisam butoanele de save si delete pentru primul pane
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

        // Afisam butoanele de save si delete pentru al doilea pane
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

    // Metode pentru butoanele de salvare si stergere
    @FXML
    void deleteFirstContact(ActionEvent event) {
        deleteContact(1);
    }

    @FXML
    void deleteSecondContact(ActionEvent event) {
        deleteContact(2);
    }

    @FXML
    void deleteThirdContact(ActionEvent event) {
        deleteContact(3);
    }

    @FXML
    void deleteFourthContact(ActionEvent event) {
        deleteContact(4);
    }

    @FXML
    void saveFirstContact(ActionEvent event) {
        saveContact(1, namePers1.getText(), numberPers1.getText());
    }

    @FXML
    void saveSecondContact(ActionEvent event) {
        saveContact(2, namePers2.getText(), numberPers2.getText());
    }

    @FXML
    void saveThirdContact(ActionEvent event) {
        saveContact(3, namePers3.getText(), numberPers3.getText());
    }

    @FXML
    void saveFourthContact(ActionEvent event) {
        saveContact(4, namePers4.getText(), numberPers4.getText());
    }

    private void deleteContact(int contactIndex) {
        try {
            String contactId = getContactIdByIndex(contactIndex);
            if (contactId != null) {
                // Șterge contactul din baza de date
                String deleteQuery = "DELETE FROM Contacte WHERE user_id = ? AND contact_id = ?";
                try (Connection connection = DatabaseManager.getConnection();
                     PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                    deleteStatement.setInt(1, userId);
                    deleteStatement.setInt(2, Integer.parseInt(contactId));
                    deleteStatement.executeUpdate();
                }
            }
            // Golește câmpurile de text
            clearContactFields(contactIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearContactFields(int contactIndex) {
        switch (contactIndex) {
            case 1:
                namePers1.clear();
                numberPers1.clear();
                break;
            case 2:
                namePers2.clear();
                numberPers2.clear();
                break;
            case 3:
                namePers3.clear();
                numberPers3.clear();
                break;
            case 4:
                namePers4.clear();
                numberPers4.clear();
                break;
        }
    }

    private String getContactIdByIndex(int contactIndex) {
        switch (contactIndex) {
            case 1:
                return contactId1;
            case 2:
                return contactId2;
            case 3:
                return contactId3;
            case 4:
                return contactId4;
            default:
                return null;
        }
    }

    private void saveContact(int contactIndex, String name, String number) {
        try {
            String contactId = getContactIdByIndex(contactIndex);
            if (contactId != null) {
                // Updatează contactul în baza de date
                String updateQuery = "UPDATE Utilizatori SET Nume = ?, TelephoneNumber = ? WHERE id = ?";
                try (Connection connection = DatabaseManager.getConnection();
                     PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setString(1, name);
                    updateStatement.setString(2, number);
                    updateStatement.setInt(3, Integer.parseInt(contactId));
                    updateStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Afisarea contactelor
    private void loadContacts() {
        List<Contact> contacts = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT u.id, u.Nume, u.TelephoneNumber " +
                             "FROM Utilizatori u " +
                             "JOIN Contacte c ON u.id = c.contact_id " +
                             "WHERE c.user_id = ?")) {
            statement.setInt(1, getUserId());

            try (ResultSet resultSet = statement.executeQuery()) {
                int index = 1;
                while (resultSet.next() && index <= 4) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("Nume");
                    String phone = resultSet.getString("TelephoneNumber");

                    //in dependeta de index umplem controalele cu date din Contct
                    switch (index) {
                        case 1:
                            contactId1 = String.valueOf(id);
                            namePers1.setText(name);
                            numberPers1.setText(phone);
                            break;
                        case 2:
                            contactId2 = String.valueOf(id);
                            namePers2.setText(name);
                            numberPers2.setText(phone);
                            break;
                        case 3:
                            contactId3 = String.valueOf(id);
                            namePers3.setText(name);
                            numberPers3.setText(phone);
                            break;
                        case 4:
                            contactId4 = String.valueOf(id);
                            namePers4.setText(name);
                            numberPers4.setText(phone);
                            break;
                    }
                    index++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserId(int userId) {
        this.userId = userId;
        loadContacts(); // Load the contacts after setting the userId
    }

    public int getUserId() {
        return userId;
    }


    @FXML
    void toWeather(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Weather.fxml"));
        Parent root = loader.load();
        //injectarea userului in controller
        WeatherController weatherController = loader.getController();
        weatherController.setUserId(userId);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

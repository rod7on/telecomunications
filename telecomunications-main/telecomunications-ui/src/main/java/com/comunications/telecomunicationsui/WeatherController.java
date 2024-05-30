package com.comunications.telecomunicationsui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public class WeatherController {

    private Stage stage;
    private Scene scene;
    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;

    @FXML
    private ImageView weatherConditionImage;

    @FXML
    private Label temperatureText;

    @FXML
    private Label weatherConditionDesc;

    @FXML
    private ImageView humidityImage;

    @FXML
    private Label humidityText;

    @FXML
    private ImageView windspeedImage;

    @FXML
    private Label windspeedText;
    private int userId;

    // Constructor cu parametrul userId pentru a seta identificatorul utilizatorului
    public WeatherController(int userId) {
        this.userId = userId;
    }

    // Constructor implicit
    public WeatherController() {}

    // Metodă de inițializare pentru configurarea elementelor UI
    @FXML
    private void initialize() {
        // Setează imaginile inițiale și alte elemente
        weatherConditionImage.setImage(loadImage("file:///C:/Users/jonym/OneDrive/Documents/telecomunications-main/telecomunications-ui/src/main/resources/img/cloudy.png"));
        humidityImage.setImage(loadImage("file:///C:/Users/jonym/OneDrive/Documents/telecomunications-main/telecomunications-ui/src/main/resources/img/humidity.png"));
        windspeedImage.setImage(loadImage("file:///C:/Users/jonym/OneDrive/Documents/telecomunications-main/telecomunications-ui/src/main/resources/img/windspeed.png"));

        // Setează imaginea pentru butonul de căutare
        searchButton.setGraphic(new ImageView(loadImage("file:///C:/Users/jonym/OneDrive/Documents/telecomunications-main/telecomunications-ui/src/main/resources/img/search.png")));

        // Adaugă un listener pentru butonul de căutare pentru a iniția căutarea vremii
        searchButton.setOnAction(event -> searchWeather());
    }

    // Metodă pentru căutarea informațiilor meteo
    private void searchWeather() {
        String userInput = searchTextField.getText();

        // Verifică dacă input-ul utilizatorului nu este gol
        if (userInput.trim().isEmpty()) {
            return;
        }

        // Obține datele meteo folosind metoda din clasa WeatherApp
        JSONObject weatherData = WeatherApp.getWeatherData(userInput);

        // Actualizează datele meteo pe UI dacă datele sunt disponibile
        if (weatherData != null) {
            updateWeatherData(weatherData);
        }
    }

    // Metodă pentru actualizarea datelor meteo pe UI
    private void updateWeatherData(JSONObject weatherData) {
        String weatherCondition = (String) weatherData.get("weather_condition");

        // Actualizează imaginea condiției meteo în funcție de condiția actuală
        switch (weatherCondition) {
            case "Clear":
                weatherConditionImage.setImage(loadImage("file:///C:/Users/jonym/OneDrive/Documents/telecomunications-main/telecomunications-ui/src/main/resources/img/clear.png"));
                break;
            case "Cloudy":
                weatherConditionImage.setImage(loadImage("file:///C:/Users/jonym/OneDrive/Documents/telecomunications-main/telecomunications-ui/src/main/resources/img/cloudy.png"));
                break;
            case "Rain":
                weatherConditionImage.setImage(loadImage("file:///C:/Users/jonym/OneDrive/Documents/telecomunications-main/telecomunications-ui/src/main/resources/img/rain.png"));
                break;
            case "Snow":
                weatherConditionImage.setImage(loadImage("file:///C:/Users/jonym/OneDrive/Documents/telecomunications-main/telecomunications-ui/src/main/resources/img/snow.png"));
                break;
        }

        // Actualizează temperatura
        double temperature = (double) weatherData.get("temperature");
        temperatureText.setText(String.format("%.1f C", temperature));

        // Actualizează descrierea condiției meteo
        weatherConditionDesc.setText(weatherCondition);

        // Actualizează umiditatea
        long humidity = (long) weatherData.get("humidity");
        humidityText.setText(String.format("Humidity %d%%", humidity));

        // Actualizează viteza vântului
        double windspeed = (double) weatherData.get("windspeed");
        windspeedText.setText(String.format("Windspeed %.1f km/h", windspeed));
    }

    // Metodă pentru încărcarea imaginilor
    private Image loadImage(String resourcePath) {
        try {
            return new Image(new File(resourcePath).toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Could not find resource");
        return null;
    }

    // Setează ID-ul utilizatorului
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Obține ID-ul utilizatorului
    public int getUserId() {
        return userId;
    }

    // Metodă pentru a reveni la meniul principal
    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        // Încarcă layout-ul meniului principal din fișierul FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();

        // Injectează ID-ul utilizatorului în controllerul meniului
        Menu menuController = loader.getController();
        menuController.setUserId(userId);

        // Schimbă scena curentă cu scena meniului principal
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

package mvc.autocar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/Agencies.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/assets/style1.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    ChoiceBox<String> heureDepart = new ChoiceBox<>();


    public static void main(String[] args) {
        launch();
    }
}

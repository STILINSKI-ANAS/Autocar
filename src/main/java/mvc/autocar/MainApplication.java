package mvc.autocar;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import mvc.autocar.Model.Repository.TicketRepository;
import mvc.autocar.Model.Ticket;

import java.io.IOException;
import java.util.ArrayList;

public class MainApplication extends Application {
    public static final String CURRENCY = "MAD";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/View/Menu1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/assets/style1.css").toExternalForm());
        stage.setTitle("Filter");
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}
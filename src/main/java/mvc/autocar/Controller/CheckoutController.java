package mvc.autocar.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static mvc.autocar.Controller.ResultsController.*;

public class CheckoutController implements Initializable {

    @FXML
    private ImageView QrcodeHolder;

    @FXML
    private Button end;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("C:\\Users\\Yassine\\eclipse-workspace\\Autocar\\src\\main\\resources\\assets\\ticket"+ qrCodeData +".png");
        Image image = new Image(file.toURI().toString());
        QrcodeHolder.setImage(image);
    }


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToend(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/Menu1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Checkout");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}

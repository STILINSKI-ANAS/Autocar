package mvc.autocar.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mvc.autocar.Model.Repository.TicketRepository;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        TicketRepository t=new TicketRepository();
        welcomeText.setText("Welcome to JavaFX Application! " + t.load().size());
    }
}
package mvc.autocar.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import mvc.autocar.MainApplication;
import mvc.autocar.Model.Repository.TicketRepository;
import mvc.autocar.Model.Ticket;
import mvc.autocar.MyListener;
import mvc.autocar.Controller.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {

    @FXML
    private Button ConfirmerBtn;

    @FXML
    private Label FraisLabel;

    @FXML
    private Label PrixUnitaireLabel;

    @FXML
    private Label SubTotalLabel;

    @FXML
    private Label TotalLabel;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    private List<Ticket> Tickets = new ArrayList<>();

    private MyListener myListener;

    private TicketRepository ticketRepository=new TicketRepository();

    private void setChosenTicket(Ticket ticket) {
        PrixUnitaireLabel.setText(ticket.getPrix() + "0 "+ MainApplication.CURRENCY);
        SubTotalLabel.setText( ticket.getPrix()+"0 "+MainApplication.CURRENCY);
        FraisLabel.setText(10+"0 "+MainApplication.CURRENCY );
        TotalLabel.setText( (ticket.getPrix() + 10) +"0 "+ MainApplication.CURRENCY  );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tickets.addAll(ticketRepository.load());
        if (Tickets.size() > 0) {
            setChosenTicket(Tickets.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Ticket ticket) {
                    setChosenTicket(ticket);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < Tickets.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/ticket.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                TicketController ticketController = fxmlLoader.getController();
                ticketController.setData(Tickets.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
//                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void Back(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/View/Menu1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Filter");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}

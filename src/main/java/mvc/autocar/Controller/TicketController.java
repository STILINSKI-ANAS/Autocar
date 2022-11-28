package mvc.autocar.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mvc.autocar.MainApplication;
import mvc.autocar.Model.Ticket;
import mvc.autocar.MyListener;
import mvc.autocar.Controller.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TicketController {

    @FXML
    private Label AgencieLabel;

    @FXML
    private ImageView AgencieLogo;

    @FXML
    private Label DateDepartLabel;

    @FXML
    private Label PriceLabel;

    @FXML
    private Label TimeArriveLabel;

    @FXML
    private Label TimeDepartLabel;

    @FXML
    private Label VilleArriveLabel;

    @FXML
    private Label VilleDepartLabel;

    @FXML
    private Label DureeLabel;
    @FXML
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(ticket);
    }

    private Ticket ticket;
    private MyListener myListener;



    //setting data in a ticket, takes data from ticket object
    public void setData(Ticket ticket, MyListener myListener) {
        this.ticket = ticket;
        this.myListener = myListener;
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        long TimeDepart = ticket.getDateDepart().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long TimeArrive = ticket.getdateArrive().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        var duree = TimeArrive - TimeDepart;
        String dureef = String.format("%dh %d", TimeUnit.MILLISECONDS.toHours(duree), TimeUnit.MILLISECONDS.toMinutes(duree) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duree)));
        String formatTimeDepart = ticket.getDateDepart().format(formatTime);
        String formatTimeArrive = ticket.getdateArrive().format(formatTime);
        String formatdateDapart = ticket.getdateArrive().format(formatDate);
        AgencieLabel.setText(String.valueOf(ticket.getidAgence()));
        DateDepartLabel.setText(formatdateDapart);
        PriceLabel.setText(String.valueOf(ticket.getPrix()) + MainApplication.CURRENCY);
        TimeArriveLabel.setText(formatTimeArrive);
        VilleArriveLabel.setText(ticket.getDestination());
        VilleDepartLabel.setText(ticket.getDepart());
        TimeDepartLabel.setText(formatTimeDepart);
        DureeLabel.setText("" + dureef);
        Image image = new Image(getClass().getResourceAsStream("/assets/AgencieLogo.png"));
        AgencieLogo.setImage(image);
    }

}

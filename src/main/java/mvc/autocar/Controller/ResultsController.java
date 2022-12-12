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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import mvc.autocar.DTO.TicketSearchDTO;
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
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ResultsController implements Initializable {

    @FXML
    private Button ConfirmerBtn;

    @FXML
    private Label FraisLabel;

    @FXML
    private Label NombreTicketsLabel;

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
    private TicketSearchDTO ticketSearchDTO=new TicketSearchDTO();

    private void setChosenTicket(Ticket ticket) {
        PrixUnitaireLabel.setText(ticket.getPrix() + "0 "+ MainApplication.CURRENCY);
        SubTotalLabel.setText( ticket.getPrix()+"0 "+MainApplication.CURRENCY);
        FraisLabel.setText(10+"0 "+MainApplication.CURRENCY );
        NombreTicketsLabel.setText(ticketSearchDTO.getNombreDeVoyageurs() + "" );
        TotalLabel.setText( ( (ticket.getPrix() * ticketSearchDTO.getNombreDeVoyageurs()) + 10 ) +"0 "+ MainApplication.CURRENCY  );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    Ticket toCheckoutTicket;
    public static int Idticket;

    public void initiliazeList(TicketSearchDTO ticketSearchDTO){
        this.ticketSearchDTO= ticketSearchDTO;
        var tickets=ticketRepository.getTickets(ticketSearchDTO);
        Tickets.addAll(tickets);
        if (Tickets.size() > 0) {
            setChosenTicket(Tickets.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Ticket ticket) {
                    toCheckoutTicket = ticket;
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
    public void CreateQrCode(){
        Idticket = toCheckoutTicket.getIdTicket();
        try {
            String qrCodeData = "" + Idticket;
            String filePath = "C:\\Users\\anasf\\Desktop\\tp oop\\project\\3rd\\src\\main\\resources\\assets\\ticket"+ Idticket +".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 300, 300, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }



    public void switchToCheckout(ActionEvent event) throws IOException{
        
        CreateQrCode();
        root = FXMLLoader.load(getClass().getResource("/View/checkout.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Checkout");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}


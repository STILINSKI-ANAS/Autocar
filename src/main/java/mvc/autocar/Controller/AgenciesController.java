package mvc.autocar.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mvc.autocar.Model.Agencies;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AgenciesController  implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Agencies, String> adresse;

    @FXML
    private TableColumn<Agencies, Integer> idAgence;

    @FXML
    private TableColumn<Agencies, String> nom;

    @FXML
    private TableColumn<Agencies, String> status;

    @FXML
    private TableView<Agencies> tableAgencies;

    @FXML
    private TableColumn<Agencies, Integer> tel;

    @FXML
    private TableColumn<Agencies, Integer> ticketsVendu;

    @FXML
    private TableColumn<Agencies, Integer> ticketsDisponible;

    ObservableList<Agencies> listM;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public void add_agencies () {
//        conn = mysqlconnect.Con
//
//                string sql = "insert into agencies ("
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idAgence.setCellValueFactory(new PropertyValueFactory<Agencies,Integer>("Id"));
        nom.setCellValueFactory(new PropertyValueFactory<Agencies,String>("Nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<Agencies,String>("Adresse"));
        status.setCellValueFactory(new PropertyValueFactory<Agencies,String>("Status"));
        tel.setCellValueFactory(new PropertyValueFactory<Agencies,Integer>("Tél"));
        ticketsVendu.setCellValueFactory(new PropertyValueFactory<Agencies,Integer>("Tickets Vendu"));
        ticketsDisponible.setCellValueFactory(new PropertyValueFactory<Agencies,Integer>("Tickets Disponible"));


        Agencies mysqlconnect = null;

        listM = mysqlconnect.getDataAgencies;

        tableAgencies.setItems(listM);



    }
}

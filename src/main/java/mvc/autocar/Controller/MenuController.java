
package mvc.autocar.Controller;

        import java.net.URL;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;
        import java.util.ResourceBundle;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.TableView;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.text.Text;
        import mvc.autocar.Model.Repository.TicketRepository;
        import mvc.autocar.Model.Repository.connectivity.ConnectionClass;
        import mvc.autocar.Model.Ticket;


public class MenuController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDecrement;

    @FXML
    private Button btnIncrement;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnTypeConfort;

    @FXML
    private Button btnTypeStandard;

    @FXML
    private Button btnTypePermuim;

    @FXML
    private DatePicker dateDepart;

    @FXML
    private Text nbrPersonnes;

    @FXML
    private ComboBox<String> heureDepart ;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heureDepart.getItems().addAll("Matinee", "Nuit", "Apres Midi", "Soire");
    }


    String typeDeComfort = "standard";

    // function to get the button clicked by the user
    public  void typeDeComfort(ActionEvent event){
        Button sourceButton = (Button) event.getSource();
        if(sourceButton.equals(btnTypePermuim)){
            typeDeComfort = btnTypePermuim.getText();
        }else if(sourceButton.equals(btnTypeStandard)){
            typeDeComfort = btnTypeStandard.getText();
        }else if(sourceButton.equals(btnTypeConfort)){
            typeDeComfort = btnTypeConfort.getText();
        }
    }


    // function to get the information of ticket and search for suitable trips in the db
    public void search(ActionEvent event){

        //get selected L'heure de depart
        String selected = heureDepart.getSelectionModel().getSelectedItem();

        // get selected date
        LocalDate myDate = dateDepart.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));


        //show data in the console
        System.out.println("now: "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")));
        System.out.println(myFormattedDate);
        System.out.println(typeDeComfort);
        System.out.println(selected);
    }



}

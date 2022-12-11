
package mvc.autocar.Controller;

        import java.io.IOException;
        import java.net.URL;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;
        import java.util.ResourceBundle;

        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;
        import mvc.autocar.DTO.TicketSearchDTO;


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
    private Button btnTypePremuim;

    @FXML
    private Button btnTypeConfort;

    @FXML
    private Button btnTypeStandard;

    @FXML
    private DatePicker dateDepart=new DatePicker(LocalDate.now());

    @FXML
    private Label nbrPersonnes;

    @FXML
    private ComboBox<String> heureDepart ;

    @FXML
    private TextField gareDarrive;

    @FXML
    private TextField gareDepart;

    private Stage stage;
    private Scene scene;



    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            heureDepart.getItems().addAll("Matinee", "Nuit", "Apres Midi", "Soire");
            dateDepart.setValue(LocalDate.now());
    }

    int nbPersonne = 1;
    //function that incrrement the number of personnes
    public void increment(){
        nbPersonne++;
        nbrPersonnes.setText(String.valueOf(nbPersonne));
    }

    //function that decrement the number of personnes
    public void decrement(){
        if(!(nbPersonne == 1)){
            nbPersonne--;
            nbrPersonnes.setText(String.valueOf(nbPersonne));
        }
    }



    String typeDeComfort = "Standard";

    // function to get the button clicked by the user
    public void typeDeComfort(ActionEvent event){
        Button sourceButton = (Button) event.getSource();

        if(sourceButton.equals(btnTypePremuim)){
            typeDeComfort = btnTypePremuim.getText();
            btnTypePremuim.getStyleClass().removeAll("button2","button2clicked");
            btnTypeStandard.getStyleClass().removeAll("button3clicked");
            btnTypeConfort.getStyleClass().removeAll("button4clicked");
            btnTypePremuim.getStyleClass().add("button2clicked");
            btnTypeStandard.getStyleClass().add("button3");
            btnTypeConfort.getStyleClass().add("button4");

        }else if(sourceButton.equals(btnTypeStandard)){
            typeDeComfort = btnTypeStandard.getText();
            btnTypePremuim.getStyleClass().removeAll("button2clicked");
            btnTypeStandard.getStyleClass().removeAll("button3");
            btnTypeConfort.getStyleClass().removeAll("button4clicked");
            btnTypeStandard.getStyleClass().add("button3clicked");
            btnTypePremuim.getStyleClass().add("button2");
            btnTypeConfort.getStyleClass().add("button4");
        }else if(sourceButton.equals(btnTypeConfort)){
            typeDeComfort = btnTypeConfort.getText();
            btnTypePremuim.getStyleClass().removeAll("button2clicked");
            btnTypeStandard.getStyleClass().removeAll("button3clicked");
            btnTypeConfort.getStyleClass().removeAll("button4");
            btnTypeConfort.getStyleClass().add("button4clicked");
            btnTypePremuim.getStyleClass().add("button2");
            btnTypeStandard.getStyleClass().add("button3");
        }
    }


    // function to get the information of ticket and search for suitable trips in the db
    public void search(ActionEvent event) throws IOException{
        TicketSearchDTO ticketSearchDTO=new TicketSearchDTO();
        ticketSearchDTO.setlHeurDeDepart(heureDepart.getSelectionModel().getSelectedItem());
        ticketSearchDTO.setDateDepart(dateDepart.getValue());
//       String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
        ticketSearchDTO.setNombreDeVoyageurs(nbPersonne);
        ticketSearchDTO.setTypeDeConfort(typeDeComfort);
        ticketSearchDTO.setGareDepart(gareDepart.getText());
        ticketSearchDTO.setGareDarrive(gareDarrive.getText());
        //this function takes us to the next scene
        if(ticketSearchDTO.getGareDepart()!= "" && ticketSearchDTO.getGareDarrive()!= "")
            switchToResults(event, ticketSearchDTO);
    }
    public void switchToResults(ActionEvent event, TicketSearchDTO ticketSearchDTO) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/results.fxml"));
        Parent root = loader.load();
        ResultsController resultsController= loader.getController();
        resultsController.initiliazeList(ticketSearchDTO);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("result");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}



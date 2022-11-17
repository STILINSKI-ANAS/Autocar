
package mvc.autocar.Controller;

        import java.net.URL;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;
        import java.util.ResourceBundle;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.*;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.text.Text;


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
    private DatePicker dateDepart;

    @FXML
    private Label nbrPersonnes;

    @FXML
    private ComboBox<String> heureDepart ;

    @FXML
    private TextField gareDarrive;

    @FXML
    private TextField gareDepart;



    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            heureDepart.getItems().addAll("Matinee", "Nuit", "Apres Midi", "Soire");
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



    String typeDeComfort = "standard";

    // function to get the button clicked by the user
    public void typeDeComfort(ActionEvent event){
        Button sourceButton = (Button) event.getSource();
        if(sourceButton.equals(btnTypePremuim)){
            typeDeComfort = btnTypePremuim.getText();
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
//        LocalDate myDate = dateDepart.getValue();
//        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));


        //get depar and arrived gare
        String departGare = gareDepart.getText();
        String arriveGare = gareDarrive.getText();


        //show data in the console
        System.out.println("now: "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")));
//      System.out.println(myFormattedDate);
        System.out.println(typeDeComfort);
        System.out.println(selected);
        System.out.println(departGare);
        System.out.println(arriveGare);
        System.out.println(nbPersonne);
    }

}

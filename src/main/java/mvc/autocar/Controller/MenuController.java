
package mvc.autocar.Controller;

        import java.net.URL;
        import java.util.ResourceBundle;

        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.control.Label;


public class MenuController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnTypePermuim;

    @FXML
    private Button btnDecrement;

    @FXML
    private Button btnIncrement;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnTypeConfort;

    @FXML
    private Button btnTypeConfortStandard;

    @FXML
    private DatePicker dateDepart;

    @FXML
    private ComboBox<String> heureDepart ;

    @FXML
    private int nbrPersonne;

    @FXML
    private Label nbrPersonnes;

    @FXML
    public void increment() {

        nbrPersonne+=1;
        nbrPersonnes.setText(String.valueOf(nbrPersonne));

    }

    @FXML
    public void decrement() {

        if(nbrPersonne>1) {
            nbrPersonne-=1;
        } else if (nbrPersonne<=1) {
            nbrPersonne =1;
        }

        nbrPersonnes.setText(String.valueOf(nbrPersonne));


    }



    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            heureDepart.getItems().addAll("Matinee", "Nuit", "Apres Midi", "Soire");


    }}

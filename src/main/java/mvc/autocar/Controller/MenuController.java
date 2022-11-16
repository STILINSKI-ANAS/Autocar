
package mvc.autocar.Controller;

        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.DatePicker;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.text.Text;


public class MenuController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BtnTypePermuim;

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
    private Text nbrPersonnes;

    @FXML
    private ComboBox<String> heureDepart ;



    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            heureDepart.getItems().addAll("Matinee", "Nuit", "Apres Midi", "Soire");


    }

    }

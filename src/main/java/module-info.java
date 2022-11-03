module mvc.autocar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens mvc.autocar to javafx.fxml;
    exports mvc.autocar;
    exports mvc.autocar.Controller;
    opens mvc.autocar.Controller to javafx.fxml;
}
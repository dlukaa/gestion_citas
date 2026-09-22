module com.example.gestioncitas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.gestioncitas to javafx.fxml;
    opens com.example.gestioncitas.Controller to javafx.fxml;
    opens com.example.gestioncitas.Model to javafx.base;

    exports com.example.gestioncitas;
    exports com.example.gestioncitas.Controller;
    exports com.example.gestioncitas.Model;
}
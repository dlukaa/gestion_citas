package com.example.gestioncitas.Controller;

import com.example.gestioncitas.Model.Cita;
import com.example.gestioncitas.Model.Paciente;
import com.example.gestioncitas.util.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CitasController {

    private static final String CODIGO_VALIDO = "CLINICA2026";

    @FXML private TextField txtNombre;
    @FXML private TextField txtDni;
    @FXML private PasswordField txtCodigo;
    @FXML private ComboBox<String> cbEspecialidad;
    @FXML private DatePicker dpFecha;
    @FXML private ToggleGroup grupoTipo;
    @FXML private CheckBox chkSms;
    @FXML private CheckBox chkEmail;
    @FXML private CheckBox chkInterprete;

    @FXML private ListView<Cita> listViewCitas;
    @FXML private TextArea txtAreaInfo;

    private final ObservableList<Cita> listaCitas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Cargar especialidades
        cbEspecialidad.getItems().addAll(
                "Medicina general",
                "Pediatría",
                "Traumatología",
                "Dermatología",
                "Cardiología"
        );

        // Enlazar ListView con ObservableList
        listViewCitas.setItems(listaCitas);

        // Listener para mostrar la cita seleccionada en el TextArea
        listViewCitas.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionado) -> {
            if (seleccionado != null) {
                txtAreaInfo.setText(seleccionado.getResumenDetalle());
            } else {
                txtAreaInfo.clear();
            }
        });
    }

    @FXML
    void onRegistrarCitaClick(ActionEvent event) {
        String nombre = txtNombre.getText() != null ? txtNombre.getText().trim() : "";
        String dni = txtDni.getText() != null ? txtDni.getText().trim() : "";
        String codigo = txtCodigo.getText() != null ? txtCodigo.getText().trim() : "";
        String especialidad = cbEspecialidad.getValue();
        LocalDate fecha = dpFecha.getValue();
        RadioButton seleccionado = (RadioButton) grupoTipo.getSelectedToggle();

        // 1. Validaciones obligatorias (Nombre, dni, especialidad, fecha)
        if (nombre.isEmpty() || dni.isEmpty() || especialidad == null || fecha == null || seleccionado == null) {
            AlertUtils.mostrarError("Por favor, rellene todos los campos obligatorios para registrar la cita.");
            return;
        }

        // 2. Validar código de empleado (si no se pone bien el código, nos dará un error para citar)
        if (!CODIGO_VALIDO.equals(codigo)) {
            AlertUtils.mostrarError("El código de empleado no es válido. No se puede registrar la cita.");
            return;
        }

        // 3. Obtener servicios seleccionados (Seleccionables para ver si quieren SMS, email o interprete)
        List<String> servicios = new ArrayList<>();
        if (chkSms.isSelected()) servicios.add("SMS");
        if (chkEmail.isSelected()) servicios.add("Email");
        if (chkInterprete.isSelected()) servicios.add("Interprete");

        // 4. Crear paciente y cita (Creación de la cita con el nombre y dni del paciente + especialidad, fecha y servicios)
        Paciente paciente = new Paciente(nombre, dni);
        Cita nuevaCita = new Cita(paciente, especialidad, seleccionado.getText(), fecha, servicios);

        listaCitas.add(nuevaCita); // Añadir nueva cita

        // Limpiar controles tras registrar
        onLimpiarClick(null);
    }

    @FXML
    void onEliminarCitaClick(ActionEvent event) {
        Cita seleccionada = listViewCitas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            AlertUtils.mostrarAviso("No has seleccionado ninguna cita para eliminar."); //Si no se selecciona una cita, nos mostrará un cuadro de diálogo en el que salga "No has seleccionado ninguna cita para eliminar."
            return;
        }
        listaCitas.remove(seleccionada);
        txtAreaInfo.clear(); // Borrar datos del paciente de la cita
    }

    @FXML
    void onLimpiarClick(ActionEvent event) { // Todo este bloque sirve para limpiar todos los datos de la cita
        txtNombre.clear(); // Todo este bloque sirve para limpiar todos los datos de la cita
        txtDni.clear(); // Todo este bloque sirve para limpiar todos los datos de la cita
        txtCodigo.clear(); // Todo este bloque sirve para limpiar todos los datos de la cita
        cbEspecialidad.setValue(null); // Todo este bloque sirve para limpiar todos los datos de la cita
        dpFecha.setValue(null); // Todo este bloque sirve para limpiar todos los datos de la cita
        if (grupoTipo.getSelectedToggle() != null) { // Todo este bloque sirve para limpiar todos los datos de la cita
            grupoTipo.getSelectedToggle().setSelected(false); // Todo este bloque sirve para limpiar todos los datos de la cita
        }
        chkSms.setSelected(false); // Todo este bloque sirve para limpiar todos los datos de la cita
        chkEmail.setSelected(false); // Todo este bloque sirve para limpiar todos los datos de la cita
        chkInterprete.setSelected(false); // Todo este bloque sirve para limpiar todos los datos de la cita
    }
}
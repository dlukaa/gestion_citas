package com.example.gestioncitas.Controller;

import com.example.gestioncitas.Model.Cita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CitasCtrller {

    private static final String CODIGO_EMPLEADO_VALIDO = "CLINICA2026";

    @FXML private TextField txtNombre;
    @FXML private TextField txtDni;
    @FXML private PasswordField txtCodigo;
    @FXML private ComboBox<String> cbEspecialidad;
    @FXML private DatePicker dpFecha;

    @FXML private ToggleGroup grupoTipo;
    @FXML private RadioButton rbPresencial;
    @FXML private RadioButton rbVideollamada;
    @FXML private RadioButton rbTelefonica;

    @FXML private CheckBox chkSms;
    @FXML private CheckBox chkEmail;
    @FXML private CheckBox chkInterprete;

    @FXML private ListView<Cita> lvCitas;
    @FXML private TextArea txtAreaInfo;

    private final ObservableList<Cita> listaCitas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Cargar especialidades del PDF
        cbEspecialidad.getItems().addAll(
                "Medicina general",
                "Pediatría",
                "Traumatología",
                "Dermatología",
                "Cardiología"
        );

        // Enlazar la ObservableList con el ListView
        lvCitas.setItems(listaCitas);

        // Listener: cuando se seleccione una cita en la lista, mostrar su detalle
        lvCitas.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionada) -> {
            if (seleccionada != null) {
                txtAreaInfo.setText(seleccionada.getResumenCita());
            } else {
                txtAreaInfo.clear();
            }
        });
    }

    @FXML
    void onRegistrarCitaClick(ActionEvent event) {
        String nombre = txtNombre.getText().trim();
        String dni = txtDni.getText().trim();
        String codigo = txtCodigo.getText().trim();
        String especialidad = cbEspecialidad.getValue();
        LocalDate fecha = dpFecha.getValue();
        RadioButton seleccionado = (RadioButton) grupoTipo.getSelectedToggle();

        // 1. Validaciones obligatorias
        if (nombre.isEmpty() || dni.isEmpty() || codigo.isEmpty() || especialidad == null || fecha == null || seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos obligatorios", "Por favor, completa todos los campos requeridos para registrar la cita.");
            return;
        }

        // 2. Validación de código de empleado
        if (!CODIGO_EMPLEADO_VALIDO.equals(codigo)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Código incorrecto", "El código de empleado no es válido. No se puede registrar la cita.");
            return;
        }

        // 3. Obtener servicios seleccionados
        List<String> servicios = new ArrayList<>();
        if (chkSms.isSelected()) servicios.add("SMS");
        if (chkEmail.isSelected()) servicios.add("Email");
        if (chkInterprete.isSelected()) servicios.add("Intérprete");

        // 4. Crear la cita y añadirla a la lista
        Cita nuevaCita = new Cita(nombre, dni, especialidad, seleccionado.getText(), fecha, servicios);
        listaCitas.add(nuevaCita);

        onLimpiarClick(null);
    }

    @FXML
    void onEliminarCitaClick(ActionEvent event) {
        Cita seleccionada = lvCitas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Sin selección", "Debes seleccionar una cita de la lista para poder eliminarla.");
        } else {
            listaCitas.remove(seleccionada);
        }
    }

    @FXML
    void onLimpiarClick(ActionEvent event) {
        txtNombre.clear();
        txtDni.clear();
        txtCodigo.clear();
        cbEspecialidad.getSelectionModel().clearSelection();
        dpFecha.setValue(null);
        rbPresencial.setSelected(true);
        chkSms.setSelected(false);
        chkEmail.setSelected(false);
        chkInterprete.setSelected(false);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
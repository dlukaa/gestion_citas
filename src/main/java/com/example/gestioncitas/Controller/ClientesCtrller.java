package com.example.gestioncitas.Controller;

import com.example.gestioncitas.Model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClientesCtrller {

    private static final String CODIGO_EMPLEADO_VALIDO = "CLINICA2026";

    @FXML private TextField txtNombre;
    @FXML private TextField txtDni;
    @FXML private PasswordField txtCodigo;
    @FXML private ComboBox<String> cbEspecialidad;
    @FXML private ToggleGroup grupoTipo;
    @FXML private DatePicker dpFecha;
    @FXML private CheckBox chkSms;
    @FXML private CheckBox chkEmail;
    @FXML private CheckBox chkInterprete;

    @FXML private ListView<Cliente> lvCitas;
    @FXML private TextArea txtAreaInfo;

    private final ObservableList<Cliente> citasObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbEspecialidad.getItems().addAll("Medicina general", "Pediatría", "Traumatología", "Dermatología", "Cardiología");
        lvCitas.setItems(citasObservable);

        lvCitas.getSelectionModel().selectedItemProperty().addListener((obs, anterior, seleccionado) -> {
            if (seleccionado != null) {
                txtAreaInfo.setText(seleccionado.getResumenCita());
            } else {
                txtAreaInfo.clear();
            }
        });
    }

    @FXML
    private void onRegistrarCita() {
        String nombre = txtNombre.getText().trim();
        String dni = txtDni.getText().trim();
        String codigo = txtCodigo.getText();
        String especialidad = cbEspecialidad.getValue();
        RadioButton tipoSeleccionado = (RadioButton) grupoTipo.getSelectedToggle();

        if (nombre.isEmpty()) {
            mostrarAlerta("Validación", "El campo 'Nombre' es obligatorio.");
            return;
        }
        if (dni.isEmpty()) {
            mostrarAlerta("Validación", "El campo 'DNI' es obligatorio.");
            return;
        }
        if (codigo.isEmpty() || !CODIGO_EMPLEADO_VALIDO.equals(codigo)) {
            mostrarAlerta("Acceso denegado", "El código de empleado no es correcto.");
            return;
        }
        if (especialidad == null) {
            mostrarAlerta("Validación", "Debes seleccionar una especialidad.");
            return;
        }
        if (tipoSeleccionado == null) {
            mostrarAlerta("Validación", "Debes seleccionar un tipo de cita.");
            return;
        }
        if (dpFecha.getValue() == null) {
            mostrarAlerta("Validación", "Debes seleccionar una fecha.");
            return;
        }

        Cliente nuevaCita = new Cliente(
                nombre,
                dni,
                especialidad,
                tipoSeleccionado.getText(),
                dpFecha.getValue(),
                chkSms.isSelected(),
                chkEmail.isSelected(),
                chkInterprete.isSelected()
        );

        citasObservable.add(nuevaCita);
        onLimpiarFormulario();
    }

    @FXML
    private void onEliminarCita() {
        Cliente seleccionado = lvCitas.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "No hay ninguna cita seleccionada en la lista.");
            return;
        }
        citasObservable.remove(seleccionado);
        txtAreaInfo.clear();
    }

    @FXML
    private void onLimpiarFormulario() {
        txtNombre.clear();
        txtDni.clear();
        txtCodigo.clear();
        cbEspecialidad.setValue(null);
        grupoTipo.selectToggle(null);
        dpFecha.setValue(null);
        chkSms.setSelected(false);
        chkEmail.setSelected(false);
        chkInterprete.setSelected(false);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
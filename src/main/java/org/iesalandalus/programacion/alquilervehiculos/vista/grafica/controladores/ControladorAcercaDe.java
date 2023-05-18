package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControladorAcercaDe extends Controlador{

	@FXML
	private Button btInformacion;
	
	@FXML
	private ImageView ivKhe;
	
	@FXML
	private static final Image KHE = new Image(LocalizadorRecursos.class.getResourceAsStream("imagenes/khe.jpg"), 100, 100, true, true);
		
	@FXML
	private void mostrarInformacion() {
		
		Alert dialogo = new Alert(AlertType.INFORMATION);
		dialogo.setTitle("Información del autor");
		dialogo.setHeaderText("Me llamo Pablo Ramos Blánquez, tengo 20 años y estoy haciendo una interfaz gráfica para el módulo de Programación");
		
		dialogo.showAndWait();
	}
}

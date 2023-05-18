package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class BorrarCliente extends Controlador{

	@FXML
	private Label lbObjetivo;
	@FXML
	private Label dni;
	@FXML
	private Label tfDni;
	@FXML
	private Button aceptar;
	@FXML
	private Button cancelar;
	
	@FXML
	void cerrarVentana() {
		
		getEscenario().close();
	}
}

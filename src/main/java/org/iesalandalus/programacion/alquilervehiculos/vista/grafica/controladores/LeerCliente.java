package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LeerCliente extends Controlador
{
	@FXML
	private Label nombre;
	@FXML
	private Label dni;
	@FXML
	private Label telefono;
	@FXML
	private TextField tfNombre;
	@FXML
	private TextField tfDNI;
	@FXML
	private TextField tfTelefono;
	@FXML
	private Button cancelar;
	@FXML
	private Button aceptar;
	
	@FXML
	void cerrarVentana() {
		
		getEscenario().close();
	}
}

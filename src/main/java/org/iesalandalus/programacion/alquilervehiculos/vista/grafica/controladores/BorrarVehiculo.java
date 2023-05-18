package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BorrarVehiculo extends Controlador{

		@FXML
		private Label lbObjetivo;
		@FXML
		private Label matricula;
		@FXML
		private TextField tfMatricula;
		@FXML
		private Button btCancelar2;
		@FXML
		private Button btAceptar2;
		
		@FXML
		void cerrarVentana() {
			
			getEscenario().close();
		}
}

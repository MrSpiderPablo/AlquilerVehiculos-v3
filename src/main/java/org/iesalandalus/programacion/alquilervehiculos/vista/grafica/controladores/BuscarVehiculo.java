package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BuscarVehiculo extends Controlador{

		@FXML
		private Label lbObjetivo;
		@FXML
		private Label matricula;
		@FXML
		private TextField tfMatricula;
		@FXML
		private Button btCancelar;
		@FXML
		private Button btAceptar;
		
		@FXML
		void cerrarVentana() {
			
			getEscenario().close();
		}
}

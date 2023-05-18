package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MostrarVehiculo extends Controlador{

		@FXML
		private Label lbObjetivo;
		@FXML
		private TableView<?> tbVehiculo;
		@FXML
		private TableColumn<?,?> tcMatricula;
		@FXML
		private TableColumn<?,?> tcMarca;
		@FXML
		private TableColumn<?,?> tcModelo;
		@FXML
		private TableColumn<?,?> tcPlazas;
		@FXML
		private TableColumn<?,?> tcPma;
		@FXML
		private TableColumn<?,?> tcTipo;
}

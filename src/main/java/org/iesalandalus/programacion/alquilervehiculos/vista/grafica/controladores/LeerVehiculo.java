package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LeerVehiculo extends Controlador{
	
	ObservableList<TipoVehiculo> tipoVehiculo = FXCollections.observableArrayList(TipoVehiculo.values());
	
	@FXML
	private Label matricula;
	@FXML
	private Label marca;
	@FXML
	private Label modelo;
	@FXML
	private Label tipo;
	@FXML
	private ComboBox listaVehiculo;
	@FXML
	private TextField tfModelo;
	
	
//	private ComboBox mostrarLista() {
//		
//		listaVehiculo.setVisibleRowCount(2);
//		listaVehiculo.setItems(FXCollections.observableArrayList("Autobús", "Furgoneta", "Turismo"));
//		
//		return listaVehiculo;
//	}
	
	@FXML
	private void initialize() {
		
		listaVehiculo.setItems(tipoVehiculo);
	}
	
	
		
}

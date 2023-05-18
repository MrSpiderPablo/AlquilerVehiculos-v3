package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.io.IOException;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class AlquilerVehiculos extends Controlador{


	@FXML
	private Button borrarCliente, buscarCliente, insertarCliente, modificarCliente, mostrarCliente;
	@FXML
	private Button borrarVehiculo, buscarVehiculo, insertarVehiculo, mostrarVehiculo;
	@FXML
	private Button borrarAlquiler, buscarAlquiler, insertarAlquiler;
	@FXML
	private Menu fichero;
	@FXML
	private MenuItem salir;
	@FXML
	private MenuItem acercaDe;
	

	
	@FXML
	void salir() {
		
		System.exit(0);
	}
	
//	@FXML
//	private void abrirInformacion() throws IOException{
//		
//		crearInformacion();
//		escenarioInformacion.showAndWait();
//	}
	
	@FXML
	void acercaDe() {
		
		ControladorAcercaDe controladorAcercaDe = (ControladorAcercaDe) Controladores.get("vistas/AcercaDe.fxml", "Información del autor", getEscenario());
		controladorAcercaDe.getEscenario().showAndWait();
	}
	
	@FXML
	void insertarCliente() {
		
		LeerCliente leerCliente = (LeerCliente) Controladores.get("vistas/LeerCliente.fxml", "Insertar Cliente", getEscenario());
		leerCliente.getEscenario().showAndWait();
	}
	
	@FXML
	void borrarCliente() {
		
		BorrarCliente borrarCliente = (BorrarCliente) Controladores.get("vistas/BorrarCliente.fxml", "Borrar Cliente", getEscenario());
		borrarCliente.getEscenario().showAndWait();
	}
	
	@FXML
	void buscarCliente() {
		
		BuscarCliente buscarCliente = (BuscarCliente) Controladores.get("vistas/BuscarCliente.fxml", "Buscar Cliente", getEscenario());
		buscarCliente.getEscenario().showAndWait();
	}
	
	@FXML
	void mostrarCliente() {
		
		MostrarCliente mostrarCliente = (MostrarCliente) Controladores.get("vistas/MostrarCliente.fxml", "Mostrar Cliente", getEscenario());
		mostrarCliente.getEscenario().showAndWait();
	}
	
	@FXML
	void insertarVehiculo() {
		
		LeerVehiculo leerVehiculo = (LeerVehiculo) Controladores.get("vistas/LeerVehiculo.fxml", "Insertar Vehículo", getEscenario());
		leerVehiculo.getEscenario().showAndWait();
	}
	
	@FXML
	void borrarVehiculo() {
		
		BorrarVehiculo borrarVehiculo = (BorrarVehiculo) Controladores.get("vistas/BorrarVehiculo.fxml", "Borrar Vehiculo", getEscenario());
		borrarVehiculo.getEscenario().showAndWait();
	}
	
	@FXML
	void buscarVehiculo() {
		
		BuscarVehiculo buscarVehiculo = (BuscarVehiculo) Controladores.get("vistas/BuscarVehiculo.fxml", "Buscar Vehiculo", getEscenario());
		buscarVehiculo.getEscenario().showAndWait();
	}
	
	@FXML
	void mostrarVehiculo() {
		
		MostrarVehiculo mostrarVehiculo = (MostrarVehiculo) Controladores.get("vistas/MostrarVehiculo.fxml", "MostrarVehiculo", getEscenario());
		mostrarVehiculo.getEscenario().showAndWait();
	}
	
	
	
}

package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MostrarCliente extends Controlador{

		@FXML
		private Label lbObjetivo;
		@FXML
		private TableView<Cliente> tvCliente;
		@FXML
		private TableColumn<Cliente,String> tbNombre;
		@FXML
		private TableColumn<Cliente,String> tbDni;
		@FXML
		private TableColumn<Cliente,String> tbTelefono;
		
		
}

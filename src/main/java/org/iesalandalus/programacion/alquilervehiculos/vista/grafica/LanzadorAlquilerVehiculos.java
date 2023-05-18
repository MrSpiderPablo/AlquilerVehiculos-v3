package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;

import javafx.application.Application;
import javafx.stage.Stage;

public class LanzadorAlquilerVehiculos extends Application{

		private static final String TITULO = "Menú Alquiler de vehículos";
		
	@Override
	public void start(Stage escenarioPrincipal) throws Exception {
		
		try {
			
			Controlador alquilerVehiculos = Controladores.get("vistas/AlquilerVehiculos.fxml", TITULO, null);
			
			alquilerVehiculos.getEscenario().show();
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void comenzar() {
		
		launch(LanzadorAlquilerVehiculos.class);
	}

}

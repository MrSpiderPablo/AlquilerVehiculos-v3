package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import java.time.LocalDate;
import java.util.List;

import javax.naming.OperationNotSupportedException;


import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaGrafica extends Vista{

	private static final VistaGrafica instancia = new VistaGrafica();
	
	private VistaGrafica() {
		
	}
	
	public static VistaGrafica getInstancia() {
		
		
		return instancia;
	}
	@Override
	public void comenzar() {
		
//		LanzadorVentanaPrincipal.comenzar();
		LanzadorAlquilerVehiculos.comenzar();
	}

	@Override
	public void terminar() {
		
		System.out.println("¡Hasta nunca!");
	}
	
	

	

}

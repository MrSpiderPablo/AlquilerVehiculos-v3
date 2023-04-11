package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public enum TipoVehiculo {
	TURISMO ("Turismo"),
	AUTOBUS ("Autobús"),
	FURGONETA ("Furgoneta");
	
	private String nombre;
	
	private TipoVehiculo(String nombre) {
		this.nombre = nombre;
	}
	
	private static boolean esOrdinalValido(int ordinal) {
		return (ordinal < TipoVehiculo.values().length || ordinal > TipoVehiculo.values().length);
		
	}
	
	public static TipoVehiculo get(int ordinal) {
		
		
		return TipoVehiculo.values()[ordinal];
		
	}
	
	public static TipoVehiculo get(Vehiculo vehiculo) {
		int indice = -1;
		if (vehiculo instanceof Turismo) {
			indice = 0;
		}
		if (vehiculo instanceof Autobus) {
			indice = 1;
		}
		if (vehiculo instanceof Furgoneta) {
			indice = 2;
		}
		return get(indice);
		
	}
	
	@Override
	public String toString() {
		int ordinal = 0;
		
		return String.format("%s (%d)", nombre, ordinal);
		
	}
}

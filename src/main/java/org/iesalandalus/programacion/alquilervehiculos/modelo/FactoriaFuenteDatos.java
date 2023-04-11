package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.FuenteDatosFicheros;

public enum FactoriaFuenteDatos {
	
	FICHEROS
	{
		 IFuenteDatos crear() 
		{
			return new FuenteDatosFicheros();
		}
	};
	abstract IFuenteDatos crear();
}

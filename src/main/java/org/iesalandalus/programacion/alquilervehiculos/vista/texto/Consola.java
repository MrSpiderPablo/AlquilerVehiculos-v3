package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final String PATRON_MES = "MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);
	
	private Consola() {
		
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.print(mensaje);
		String cabecera = "-";

		for (int i = 0; i < mensaje.length(); i++) {
			System.out.printf("%s//", cabecera);
		}
	}
	
	public static void mostrarMenuAcciones() {
		System.out.println("Este menú sirve para el manejo de alquiler de diferentes tipos de coches."
				+" A continuación, te mostramos las siguientes opciones:"
	
					+ " 0-. Salir "
					+ " 1-. Insertar cliente"
					+ " 2-. Insertar vehiculo"
					+ " 3-. Insertar alquiler"
					+ " 4-. Buscar cliente"
					+ " 5-. Buscar vehiculo"
					+ " 6-. Buscar alquiler"
					+ " 7-. Modificar cliente"
					+ " 8-. Devolver alquiler cliente"
					+ " 9-. Devolver alquiler vehiculo"
					+ " 10-. Borrar cliente"
					+  "11-. Borrar vehiculo"
					+ " 12-. Borrar alquiler"
					+ " 13-. Listar clientes"
					+ " 14-. Listar vehiculos"
					+ " 15-. Listar alquileres"
					+ " 16-. Listar alquileres de cliente"
					+ " 17-. Listar alquileres de vehiculo"
					+ " 18-. Mostrar estadísticas mensuales");
	}
	
	
	
	
	public static Accion elegirAccion() {
		int accion;
		do {
			leerEntero("Elige una accion: ");
			accion = Entrada.entero();
		}while (accion < 0 || accion > 18);
		return Accion.values()[accion];
	}
	
	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		
		String cadena = "";
		
		cadena = Entrada.cadena();
		
		return cadena;
		
	}
	
	private static int leerEntero(String mensaje) {
		System.out.print(mensaje);
		int numero = 0;
		numero = Entrada.entero();
		return numero;
	}
	
	private static LocalDate leerFecha(String mensaje, String patron) {
		System.out.print(mensaje);

		String fecha = "";
		
		LocalDate fechaDef = null;
		
		if (patron.equals(PATRON_MES)) {
			fecha = "01/" + fecha;
		}
		
		try {
			fecha = Entrada.cadena();

			fechaDef = LocalDate.parse(fecha, FORMATO_FECHA);
		}catch (DateTimeParseException e) {
			System.out.println("La fecha es nula.");
		}
		return fechaDef;
		
	}
	
	public static Cliente leerCliente() {
		leerCadena("Introduce los datos del cliente: ");
//		leerNombre();
//		leerTelefono();
		leerClienteDni();
		
//		String nombre;
//		
//		nombre = Entrada.cadena();
//		
//		String telefono;
//		
//		telefono = Entrada.cadena();
		
		String dni;
		
		dni = Entrada.cadena();
		
		Cliente cliente = null;
		
		cliente = new Cliente(leerNombre(), dni, leerTelefono());
		
		return cliente;
		
	}
	
	public static Cliente leerClienteDni() {
		String dni = "";
		leerCadena("Introduce el dni del cliente: ");
		dni = Entrada.cadena();
		return Cliente.getClienteConDni(dni);
		
	}
	
	public static String leerNombre() {
		return leerCadena("Introduce el nombre del cliente: ");
		
	}
	
	public static String leerTelefono() {
		return leerCadena("Introduce el teléfono del cliente: ");
	}
	
	public static Vehiculo leerVehiculo() {
		leerCadena("Introduce los datos del vehículo: ");
		leerVehiculoMatricula();
		
		String marca;
		marca = Entrada.cadena();
		
		String modelo;
		modelo = Entrada.cadena();
		
		String matricula;
		matricula = Entrada.cadena();
		
		Vehiculo vehiculo = null;
		
		if(vehiculo instanceof Turismo turismo) {
			int cilindrada = 0;
			cilindrada = Entrada.entero();
			vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
		}
		
		if (vehiculo instanceof Autobus autobus) {
			int plazas = 0;
			plazas = Entrada.entero();
			vehiculo = new Autobus(marca, modelo, plazas, matricula);
		}
		
		if (vehiculo instanceof Furgoneta furgoneta) {
			int pma = 0;
			pma = Entrada.entero();
			int plazas = 0;
			plazas = Entrada.entero();
			vehiculo = new Furgoneta(marca, modelo, pma, plazas, matricula);
		}
		
		
		return vehiculo;
		
	}
	
	private static void mostrarMenuTiposVehiculos() {
		System.out.println("Este menú sirve para la elección del tipo de coche."
				+"A continuación, te mostramos los modelos disponibles:"
	
					+ "0-. Turismo"
					+ "1-. Autobús"
					+ "2-. Furgoneta");
	}
	
	private static TipoVehiculo elegirTipoVehiculo() {
		int modelo;
		do {
			leerEntero("Elige el tipo de coche que prefieres: ");
			modelo = Entrada.entero();
		}while(modelo < 0 || modelo > 2);
		return TipoVehiculo.values()[modelo];
	}
	
	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		elegirTipoVehiculo();
		leerVehiculo();
		
		return null;
		
	}
	
	public static Vehiculo leerVehiculoMatricula() {
		String matricula = "";
		leerCadena("Introduce la matrícula del vehículo: ");
		matricula = Entrada.cadena();
		return Vehiculo.getVehiculoConMatricula(matricula);
		
	}
	
	public static Alquiler leerAlquiler() {
		String patron = PATRON_FECHA;
		
		Alquiler alquiler = null;
		
		alquiler = new Alquiler(leerCliente(), leerVehiculo(), leerFecha("Introduce la fecha de alquiler: ", patron));
		
		
		
		return alquiler;
		
	}
	
	public static LocalDate leerFechaDevolucion() {
		String patron = PATRON_FECHA;
		return leerFecha("Introduce la fecha de devolucion: " , patron);
		
		
	}
	
	public static LocalDate leerMes() {
		String patron = PATRON_MES;
		return leerFecha("Introduce el mes: ", patron);
		
	}
}

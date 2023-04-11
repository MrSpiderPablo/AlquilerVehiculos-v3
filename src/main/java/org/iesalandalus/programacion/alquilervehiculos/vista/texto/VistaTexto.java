package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista{
	
	public VistaTexto() {
		super();
	}
	@Override
	public void comenzar() {
		Consola.mostrarMenuAcciones();
	}

	@Override
	public void terminar() {
		
		System.out.println("Servicio cumplido. Hasta la vista, babe");
		
	}
	
	public void insertarCliente() throws OperationNotSupportedException {
		
		System.out.print("Nos encontramos en la opción número uno.");
		
		Consola.leerCliente();
		
		Cliente cliente = null;
		getControlador().insertar(cliente);
	}
	
	public void insertarVehiculo() throws OperationNotSupportedException {
		System.out.print("Nos encontramos en la opción número dos.");
		
		Consola.leerVehiculo();
		
		Vehiculo vehiculo = null;
		getControlador().insertar(vehiculo);
	}
	
	public void insertarAlquiler() throws OperationNotSupportedException{
		System.out.print("Nos encontramos en la opción número tres.");
		
		Consola.leerAlquiler();
		
		Alquiler alquiler = null;
		getControlador().insertar(alquiler);
	}
	
	public void buscarCliente() {
		System.out.print("Nos encontramos en la opción número cuatro.");
		
		Consola.leerClienteDni();
		
		Cliente cliente = null;
		getControlador().buscar(cliente);
	}
	
	public void buscarVehiculo() {
		System.out.print("Nos encontramos en la opción número cinco.");
		
		Consola.leerVehiculoMatricula();
		
		Vehiculo vehiculo = null;
		getControlador().buscar(vehiculo);
	}
	
	public void buscarAlquiler() {
		System.out.print("Nos encontramos en la opción número seis.");
		
		Consola.leerAlquiler();
		
		
		Alquiler alquiler = null;
		getControlador().buscar(alquiler);
		
	}
	
	public void modificarCliente() throws OperationNotSupportedException {
		System.out.print("Nos encontramos en la opción número siete.");
		
		Consola.leerCliente();
		
		Cliente cliente = null;
		String nombre = "";
		String telefono = "";
		getControlador().modificar(cliente, nombre, telefono);
	}
	
	public void devolverAlquilerCliente() {
		System.out.print("Nos encontramos en la opción número ocho.");
		
		Consola.leerClienteDni();
	}
	
	public void devolverAlquilerVehiculo() {
		System.out.print("Nos encontramos en la opción número nueve.");
		
		Consola.leerVehiculoMatricula();
		
		
	}
	
	public void borrarCliente() throws OperationNotSupportedException {
		System.out.print("Nos encontramos en la opción número diez.");
		
		Consola.leerClienteDni();
		
		Cliente cliente = null;
		getControlador().borrarCliente(cliente);
	}
	
	public void borrarVehiculo() throws OperationNotSupportedException{
		System.out.print("Nos encontramos en la opción número once.");
		
		Consola.leerVehiculoMatricula();
		
		Vehiculo vehiculo = null;
		getControlador().borrarVehiculo(vehiculo);
	}
	
	public void listarClientes() {
		List<Cliente>clientes = getControlador().getClientes();
		
		clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
		if (!clientes.isEmpty()) {
			for (Cliente cliente : clientes) {
				System.out.print(cliente);
			}
		}else {
			System.out.println("No hay clientes que mostrar.");
		}
		
	}
	
	public void listarVehiculos() {
		List<Vehiculo>vehiculos = getControlador().getVehiculos();
		vehiculos.sort(Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo).thenComparing(Vehiculo::getMatricula));
		if (!vehiculos.isEmpty()) {
			for (Vehiculo vehiculo : vehiculos) {
				System.out.print(vehiculo);
			}
		}else {
			System.out.println("No hay vehículos que mostrar.");
		}
	}
	
	public void listarAlquileres() {
		List<Alquiler>alquileres = getControlador().getAlquileres();
		Comparator<Cliente>comparadorCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
		alquileres.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente, comparadorCliente));
		if (!alquileres.isEmpty()) {
			for (Alquiler alquiler : alquileres) {
				System.out.print(alquiler);
			}
		}else {
			System.out.println("No hay alquileres que mostrar.");
		}
	}
	
	public void listarAlquileresCliente() {
		List<Alquiler>alquileresClientes = getControlador().getAlquileres();
		Comparator<Cliente>comparadorAlquiler = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
		alquileresClientes.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getCliente, comparadorAlquiler));
		if (!alquileresClientes.isEmpty()) {
			for (Alquiler alquilerCliente : alquileresClientes) {
				System.out.print(alquilerCliente);
			}
		}else {
			System.out.println("No hay alquileres de clientes que mostrar.");
		}
	}
	
	public void listarAlquileresVehiculo() {
		List<Alquiler>alquileresVehiculos = getControlador().getAlquileres();
		Comparator<Vehiculo>comparadorAlquilerVehiculo = Comparator.comparing(Vehiculo::getMarca).thenComparing(Vehiculo::getModelo).thenComparing(Vehiculo::getMatricula);
		alquileresVehiculos.sort(Comparator.comparing(Alquiler::getFechaAlquiler).thenComparing(Alquiler::getVehiculo, comparadorAlquilerVehiculo));
		if (!alquileresVehiculos.isEmpty()) {
			for (Alquiler alquilerVehiculo : alquileresVehiculos) {
				System.out.print(alquilerVehiculo);
			}
		}else {
			System.out.println("No hay alquileres de vehículos que mostrar.");
		}
	}
	
	public void mostrarEstadisticasMensualesTipoVehiculo() {
		inicializarEstadisticas();
	}
	
	private Map<TipoVehiculo, Integer> inicializarEstadisticas(){
		Map<TipoVehiculo, Integer> estadisticas = new EnumMap<>(TipoVehiculo.class);
		estadisticas.put(TipoVehiculo.TURISMO, 0);
		estadisticas.put(TipoVehiculo.AUTOBUS, 1);
		estadisticas.put(TipoVehiculo.FURGONETA, 2);
		
		return estadisticas;
		
	}
	
	

}

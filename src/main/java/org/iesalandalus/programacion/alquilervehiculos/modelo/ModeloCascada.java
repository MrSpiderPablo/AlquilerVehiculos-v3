package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;

public class ModeloCascada extends Modelo{

	public ModeloCascada(FactoriaFuenteDatos factoriaFuenteDatos) {
		super(factoriaFuenteDatos);
	}
	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		
		getClientes().insertar(new Cliente(cliente));
		
		
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un turismo nulo.");
		}
		
		getVehiculos().insertar(Vehiculo.copiar(vehiculo));
		
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		
		buscar(alquiler.getCliente());
		buscar(alquiler.getVehiculo());
		
		getAlquileres().insertar(new Alquiler(alquiler));
		
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		
		Cliente clienteBuscado = null;
		
		clienteBuscado = getClientes().buscar(cliente);
		
		return clienteBuscado;
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar ");
		}
//		return getVehiculos().buscar(Vehiculo.copiar(vehiculo));
		Vehiculo vehiculoBuscado = null;
		
		vehiculoBuscado = getVehiculos().buscar(vehiculo);
		
		return vehiculoBuscado;
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
//		return getAlquileres().buscar(new Alquiler(alquiler));
		
		Alquiler alquilerBuscado = null;
		
		alquilerBuscado = getAlquileres().buscar(alquiler);
		
		return alquilerBuscado;
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		
		getClientes().modificar(cliente, nombre, telefono);
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un cliente nulo.");
		}
		
		getAlquileres().devolver(cliente, fechaDevolucion);
	}
	
	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException{
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un vehículo nulo.");
		}
		
		getAlquileres().devolver(vehiculo, fechaDevolucion);
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		
		List<Alquiler> alquileresBorrar = getAlquileres().get(cliente);
		
		for (Alquiler alquiler : alquileresBorrar) {
			
			getAlquileres().borrar(alquiler);
		}
		
		getClientes().borrar(cliente);
		
		
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		
		List<Alquiler> alquileresBorrar2 = getAlquileres().get(vehiculo);
		
		for (Alquiler alquiler : alquileresBorrar2) {
			
			getAlquileres().borrar(alquiler);
		}
		
		getVehiculos().borrar(vehiculo);
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		
		getAlquileres().borrar(alquiler);
	}

	@Override
	public List<Cliente> getListaClientes() {
		List<Cliente> coleccionClientes2 = new ArrayList<>();
		for (Cliente cliente : getClientes().get()) {
			
			coleccionClientes2.add(new Cliente(cliente));
		}
		return  coleccionClientes2;
	}

	@Override
	public List<Vehiculo> getListaVehiculos() {
		
		List<Vehiculo> coleccionVehiculos2 = new ArrayList<>();
		for (Vehiculo vehiculo : getVehiculos().get()) {
			
//			coleccionVehiculos2.add(new Vehiculo(vehiculo));
			
			if (vehiculo instanceof Turismo turismo) {
				
				coleccionVehiculos2.add(new Turismo(turismo));
			}
			
			if (vehiculo instanceof Autobus autobus) {
				
				coleccionVehiculos2.add(new Autobus(autobus));
			}
			
			if (vehiculo instanceof Furgoneta furgoneta) {
				
				coleccionVehiculos2.add(new Furgoneta(furgoneta));
			}
		}
		return  coleccionVehiculos2;
	}

	@Override
	public List<Alquiler> getListaAlquileres() {
		
		List<Alquiler> coleccionAlquileres2 = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get()) {
			
			coleccionAlquileres2.add(new Alquiler(alquiler));
		}
		return  coleccionAlquileres2;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Cliente cliente) {
		
		List<Alquiler> clientes2 = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(cliente)) {
			
			clientes2.add(new Alquiler(alquiler));
		}
		return  clientes2;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Vehiculo vehiculo) {
		
		List<Alquiler> vehiculos2 = new ArrayList<>();
		for (Alquiler alquiler : getAlquileres().get(vehiculo)) {
			
			vehiculos2.add(new Alquiler(alquiler));
		}
		return  vehiculos2;
	}

}

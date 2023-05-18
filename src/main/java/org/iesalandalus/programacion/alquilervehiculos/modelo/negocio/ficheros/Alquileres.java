package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
//import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Alquileres implements IAlquileres {
	
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String RAIZ = "alquileres";
	private static final String ALQUILER = "alquiler";
	private static final String CLIENTE = "cliente";
	private static final String VEHICULO = "vehiculo";
	private static final String FECHA_ALQUILER = "fechaAlquiler";
	private static final String FECHA_DEVOLUCION = "fechaDevolucion";
	private static final File FICHERO_ALQUILERES = new File("datos" + File.separator + "alquileres.xml");
	private static Alquileres instancia;
	private List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}
	
	static Alquileres getInstancia() {
		if (instancia == null) {
			instancia = new Alquileres();
		}
		
		return instancia;
		
	}
	
	public void comenzar() {
		
//		Document documento = UtilidadesXml.leerXmlDeFichero(FICHERO_ALQUILERES);
//		if (documento == null) {
//			throw new NullPointerException("ERROR: El documento no puede ser nulo. ");
//		}
//		leerDom(documento);
	}
	
	private void leerDom(Document documentoXml) {
		
		NodeList alquileres = documentoXml.getElementsByTagName(ALQUILER);
		for (int i = 0; i < alquileres.getLength(); i++) {
			Node nAlquileres = alquileres.item(i);
			if (nAlquileres.getNodeType() == Node.ELEMENT_NODE) {
				
				try {
					insertar(getAlquiler((Element)nAlquileres));
				}catch (NullPointerException e) {
					System.out.println("El alquiler no puede ser nulo.");
				}catch (OperationNotSupportedException e) {
					System.out.println("Error al insertar el alquiler.");
				}
			}
		}
	}
	
	private Alquiler getAlquiler(Element elemento) {
		Alquiler alquiler = null;
		String clienteDNI = elemento.getAttribute(CLIENTE);
	    Cliente.getClienteConDni(clienteDNI);
		String vehiculoMatricula = elemento.getAttribute(VEHICULO);
		Vehiculo.getVehiculoConMatricula(vehiculoMatricula);
		String fechaAlquiler = elemento.getAttribute(FECHA_ALQUILER);
		
		
		
		return alquiler;
		
	}
	
	public void terminar() {
		UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_ALQUILERES);
	}
	
	private Document crearDom() {
		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXML();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement(RAIZ));
		}
		return documentoXml;
	}
	
	private Element getElemento(Document documentoXml, Alquiler alquiler) {
		
		Element elementoAlquiler = documentoXml.createElement(RAIZ);
		elementoAlquiler.setAttribute(CLIENTE, String.format("%s", alquiler.getCliente()));
		elementoAlquiler.setAttribute(VEHICULO, String.format("%s", alquiler.getVehiculo()));
		elementoAlquiler.setAttribute(FECHA_ALQUILER, String.format("%s", alquiler.getFechaAlquiler().format(FORMATO_FECHA)));
		elementoAlquiler.setAttribute(FECHA_DEVOLUCION, String.format("%s", alquiler.getFechaDevolucion().format(FORMATO_FECHA)));
		return elementoAlquiler;
	}

	@Override
	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	@Override
	public List<Alquiler> get(Cliente cliente) {
		List<Alquiler> clientes = new ArrayList<>();

		for (Alquiler coleccionAlquiler : coleccionAlquileres) {
			if (coleccionAlquiler.getCliente().equals(cliente)) {
				clientes.add(coleccionAlquiler);
			}
		}
		
		return clientes;
	}

	@Override
	public List<Alquiler> get(Vehiculo vehiculo) {
		List<Alquiler> vehiculos = new ArrayList<>();

		for (Alquiler coleccionAlquiler : coleccionAlquileres) {
			if (coleccionAlquiler.getVehiculo().equals(vehiculo)) {
				vehiculos.add(coleccionAlquiler);
			}
		}

//		return new ArrayList<>(turismos);
		
		return vehiculos;
	}


	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)throws OperationNotSupportedException {
		for (Alquiler coleccionAlquiler : coleccionAlquileres) {
			if (coleccionAlquiler.getFechaDevolucion() == null) {
				if (coleccionAlquiler.getCliente().equals(cliente)) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
				}

				if (coleccionAlquiler.getVehiculo().equals(vehiculo)) {
					throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
				}
			} else {
				if (fechaAlquiler.isBefore(coleccionAlquiler.getFechaDevolucion())
						|| fechaAlquiler.isEqual(coleccionAlquiler.getFechaDevolucion())) {
					if (coleccionAlquiler.getCliente().equals(cliente)) {
						throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
					}

					if (coleccionAlquiler.getVehiculo().equals(vehiculo)) {
						throw new OperationNotSupportedException("ERROR: El vehículo tiene un alquiler posterior.");
					}
				}
			}
		}
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}

		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());

		coleccionAlquileres.add(alquiler);

	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}
		
		getAlquilerAbierto(cliente).devolver(fechaDevolucion);
		

	}
	
	private Alquiler getAlquilerAbierto(Cliente cliente) throws OperationNotSupportedException{
		Alquiler abierto = null;

		
		for(Iterator<Alquiler> iterador = coleccionAlquileres.iterator(); iterador.hasNext();) {
			
			Alquiler coleccionAlquiler = iterador.next();
			
			if (coleccionAlquiler.getFechaDevolucion() == null) {
				if (coleccionAlquiler.getCliente().equals(cliente)) {
					abierto = coleccionAlquiler;
				}
			}
			
			}
		
		if (abierto == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese cliente.");
		}
		
		
		
		return abierto;
		
	}
	
	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException{
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}
		
		getAlquilerAbierto(vehiculo).devolver(fechaDevolucion);
	}
	
	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) throws OperationNotSupportedException{
		Alquiler abierto2 = null;
		for (Iterator<Alquiler> iterador2 = coleccionAlquileres.iterator(); iterador2.hasNext();) {
			Alquiler coleccionAlquiler = iterador2.next();
			
			if (coleccionAlquiler.getFechaDevolucion() == null) {
				if (coleccionAlquiler.getVehiculo().equals(vehiculo)){
					abierto2 = coleccionAlquiler;
				}
			}
		}
		
		if (abierto2 == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese vehículo.");
		}
		
		return abierto2;
		
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}

		if (coleccionAlquileres.contains(alquiler)) {
			coleccionAlquileres.remove(alquiler);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}

		
		if (coleccionAlquileres.indexOf(alquiler) > -1) {
			coleccionAlquileres.get(0);
		}else {
			alquiler = null;
		}
		
		return alquiler;
	}
}

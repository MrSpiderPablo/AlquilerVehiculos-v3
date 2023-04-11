package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Clientes implements IClientes {
	private static final String RAIZ = "clientes";
	private static final String CLIENTE = "cliente";
	private static final String NOMBRE = "nombre";
	private static final String DNI = "dni";
	private static final String TELEFONO = "telefono";
	private static final File FICHERO_CLIENTES = new File("datos" + File.separator + "clientes.xml");
	private static Clientes instancia;
	private List<Cliente> coleccionClientes;

	public Clientes() {
		coleccionClientes = new ArrayList<>();
	}

	static Clientes getInstancia() {
		if (instancia == null) {
			instancia = new Clientes();
		}
		return instancia;

	}

	public void comenzar() {
		
		Document documento = UtilidadesXml.leerXmlDeFichero(FICHERO_CLIENTES);
		if (documento == null) {
			throw new NullPointerException("ERROR: El documento no puede ser nulo. ");
		}
		leerDom(documento);
	}

	private void leerDom(Document documentoXml) {

		NodeList clientes = documentoXml.getElementsByTagName(CLIENTE);
			for (int i = 0; i < clientes.getLength(); i++) {
				Node nCliente = clientes.item(i);
				if (nCliente.getNodeType() == Node.ELEMENT_NODE) {
					
					try {
						insertar(getCliente((Element)nCliente));
					}catch (OperationNotSupportedException e) {
						System.out.println("Error al insertar el cliente.");
					}catch (NullPointerException e) {
						System.out.println("El cliente no puede ser nulo.");
					}
					
				}
		}
}

	private Cliente getCliente(Element elemento) {
		String nombre = elemento.getAttribute(NOMBRE);
		String dni = elemento.getAttribute(DNI);
		String telefono = elemento.getAttribute(TELEFONO);
		
		Cliente cliente;
		
		cliente = new Cliente(nombre, dni, telefono);
		
		return cliente;

	}

	public void terminar() {
		UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_CLIENTES);
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

	private Element getElemento(Document documentoXml, Cliente cliente) {
		Element elementoCliente = documentoXml.createElement(RAIZ);
		elementoCliente.setAttribute(NOMBRE, cliente.getNombre());
		elementoCliente.setAttribute(DNI, cliente.getDni());
		elementoCliente.setAttribute(TELEFONO, cliente.getTelefono());
		return elementoCliente;

	}

	@Override
	public List<Cliente> get() {
		return new ArrayList<>(coleccionClientes);
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}

		if (!coleccionClientes.contains(cliente)) {
			coleccionClientes.add(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}

//		if (!coleccionClientes.contains(cliente)) {
//			return null;
//		}
//		
//		
//		return cliente;

		if (coleccionClientes.indexOf(cliente) > -1) {
			coleccionClientes.get(0);
		} else {
			cliente = null;
		}

		return cliente;
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}

		if (coleccionClientes.contains(cliente)) {
			coleccionClientes.remove(cliente);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {

		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}

		Cliente clienteEncontrado = buscar(cliente);

		if (clienteEncontrado == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}

		if (telefono != null && !(telefono.isBlank())) {
			clienteEncontrado.setTelefono(telefono);
		}

		if (nombre != null && !(nombre.isBlank())) {
			clienteEncontrado.setNombre(nombre);
		}

	}

}

package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Vehiculos  implements IVehiculos  {
	
	private static final String RAIZ = "vehiculos";
	private static final String VEHICULO = "vehiculo";
	private static final String MARCA = "marca";
	private static final String MODELO = "modelo";
	private static final String MATRICULA = "matricula";
	private static final String CILINDRADA = "cilindrada";
	private static final String PLAZAS = "plazas";
	private static final String PMA = "pma";
	private static final String TIPO = "tipo";
	private static final String TURISMO = "turismo";
	private static final String AUTOBUS = "autobus";
	private static final String FURGONETA = "furgoneta";
	private static final File FICHERO_VEHICULOS = new File("datos" + File.separator + "vehiculos.xml");
	private static Vehiculos instancia;
	private List<Vehiculo> coleccionVehiculos;
	
	public Vehiculos() {
		coleccionVehiculos = new ArrayList<>();
		
	}
	
	static Vehiculos getInstancia() {
		if (instancia == null) {
			instancia = new Vehiculos();
		}
		
		return instancia;
		
	}
	
	public void comenzar() {
		
//		Document documento = UtilidadesXml.leerXmlDeFichero(FICHERO_VEHICULOS);
//		if (documento == null) {
//			throw new NullPointerException("ERROR: El documento no puede ser nulo. ");
//		}
//		leerDom(documento);
	}
	
	private void leerDom(Document documentoXml) {
		
		NodeList vehiculos = documentoXml.getElementsByTagName(VEHICULO);
		for (int i = 0; i < vehiculos.getLength(); i++) {
			Node nVehiculos = vehiculos.item(i);
			if (nVehiculos.getNodeType() == Node.ELEMENT_NODE) {
				
				try {
					insertar(getVehiculo((Element)nVehiculos));
				}catch (OperationNotSupportedException e) {
					System.out.println("Error al insertar el vehiculo.");
				}catch (NullPointerException e) {
					System.out.println("El vehiculo no puede ser nulo.");
				}
			}
		}
	}
	
	private Vehiculo getVehiculo(Element elemento) {
		Vehiculo vehiculo = null;
		String marca = elemento.getAttribute(MARCA);
		String modelo = elemento.getAttribute(MODELO);
		String matricula = elemento.getAttribute(MATRICULA);
		String tipo = elemento.getAttribute(TIPO);
		if (tipo.equals(TURISMO)) {
			int cilindrada = Integer.parseInt(elemento.getAttribute(CILINDRADA));
			 vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
			
		}
		if (tipo.equals(AUTOBUS)) {
			int plazas = Integer.parseInt(elemento.getAttribute(PLAZAS));
			 vehiculo = new Autobus(marca, modelo, plazas, matricula);
		}
		if (tipo.equals(FURGONETA)) {
			int pma = Integer.parseInt(elemento.getAttribute(PMA));
			int plazas = Integer.parseInt(elemento.getAttribute(PLAZAS));
			 vehiculo = new Furgoneta(marca, modelo, pma, plazas, matricula);
		}
		
		return vehiculo;
		
	}
	
	public void terminar() {
		UtilidadesXml.escribirXmlAFichero(crearDom(), FICHERO_VEHICULOS);
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
	
	private Element getElemento(Document documentoXml, Vehiculo vehiculo) {
		Element elementoVehiculo = documentoXml.createElement(RAIZ);
		elementoVehiculo.setAttribute(MARCA, vehiculo.getMarca());
		elementoVehiculo.setAttribute(MODELO, vehiculo.getModelo());
		elementoVehiculo.setAttribute(MATRICULA, vehiculo.getMatricula());
		if (vehiculo instanceof Turismo turismo) {
			elementoVehiculo.setAttribute(TIPO, TURISMO);
			elementoVehiculo.setAttribute(CILINDRADA, Integer.toString(turismo.getCilindrada()));
		}
		if (vehiculo instanceof Autobus autobus) {
			elementoVehiculo.setAttribute(TIPO, AUTOBUS);
			elementoVehiculo.setAttribute(PLAZAS, Integer.toString(autobus.getPlazas()));
		}
		if (vehiculo instanceof Furgoneta furgoneta) {
			elementoVehiculo.setAttribute(TIPO, FURGONETA);
			elementoVehiculo.setAttribute(PMA, Integer.toString(furgoneta.getPma()));
			elementoVehiculo.setAttribute(PLAZAS, Integer.toString(furgoneta.getPlazas()));
		}
		return elementoVehiculo;
		
	}
	
	@Override
	public List<Vehiculo> get(){
		return new ArrayList<>(coleccionVehiculos);
	}


	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}
	
		if (!coleccionVehiculos.contains(vehiculo)) {
			coleccionVehiculos.add(vehiculo);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
	
//		if (!coleccionVehiculos.contains(vehiculo)) {
//			return null;
//		}
//	
//		return vehiculo;
		
		if (coleccionVehiculos.indexOf(vehiculo) > -1) {
			coleccionVehiculos.get(0);
		}else {
			vehiculo = null;
		}
		
		return vehiculo;
	
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}
	
		if (coleccionVehiculos.contains(vehiculo)) {
			coleccionVehiculos.remove(vehiculo);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
		}
	}
	
	
}

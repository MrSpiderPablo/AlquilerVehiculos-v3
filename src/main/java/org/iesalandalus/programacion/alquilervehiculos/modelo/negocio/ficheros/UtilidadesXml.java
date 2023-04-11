package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class UtilidadesXml {
	
	private UtilidadesXml() {
		
	}
	
	public static DocumentBuilder crearConstructorDocumentoXML() {
		DocumentBuilder constructor = null;
		
		try {
			DocumentBuilderFactory taller = DocumentBuilderFactory.newInstance();
			taller.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			constructor = taller.newDocumentBuilder();
			
		}catch (ParserConfigurationException e){
			System.out.println("Error al crear el constructor.");
		}
		
		return constructor;
		
	}
	
	public static void escribirXmlAFichero(Document documento, File salida) {
		try {
			TransformerFactory ejercicio = TransformerFactory.newInstance();
			ejercicio.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			ejercicio.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
			Transformer conversor = ejercicio.newTransformer();
			conversor.setOutputProperty(OutputKeys.INDENT, "yes");
			conversor.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			conversor.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			StreamResult destino = new StreamResult(salida);
			DOMSource fuente = new DOMSource(documento);
			conversor.transform(fuente, destino);
			System.out.printf("Fichero %s escrito correctamente.%n", salida.getName());
		}catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			System.out.println("Imposible crear el conversor.");
		}catch (TransformerException e) {
			System.out.println("Error irrecuperable en la conversion.");
		}
	}
	
	public static Document leerXmlDeFichero(File ficheroXml) {
		Document documentoXml = null;
		try {
			DocumentBuilder constructor = crearConstructorDocumentoXML();
			if (constructor != null) {
				documentoXml = constructor.parse(ficheroXml);
				documentoXml.getDocumentElement().normalize();
			}
		}catch (SAXException e){
			System.out.println("Documento XML mal formado.");
		}catch (IOException e) {
			//Aquí no hacemos nada
		}
		
		return documentoXml;
		
	}
}

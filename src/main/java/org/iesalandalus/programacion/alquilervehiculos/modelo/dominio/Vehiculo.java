package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public abstract class Vehiculo {

	private static final String ER_MARCA = "[A-Z][a-z]+([- ]?[A-Z][a-z]+)*|[A-Z]+";
	private static final String ER_MATRICULA = "\\d{4}[BCDFGHJKLMNÑPQRSTVWXYZ]{3}";
	protected String marca;
	protected String modelo;
	protected String matricula;
	
	protected Vehiculo(String marca, String modelo, String matricula) {
		setMarca(marca);
		setModelo(modelo);
		setMatricula(matricula);
	}
	
	protected Vehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un vehículo nulo.");
			
		}
		
//		setMarca(vehiculo.getMarca());
//		setModelo(vehiculo.getModelo());
//		setMatricula(vehiculo.getMatricula());
		
		marca = vehiculo.getMarca();
		modelo = vehiculo.getModelo();
		matricula = vehiculo.getMatricula();
	}
	
	public static Vehiculo copiar(Vehiculo vehiculo) {
		
		Vehiculo vehiculoCopiado = null;
		if (vehiculo instanceof Turismo turismo) {
			vehiculoCopiado = new Turismo(turismo);
		}
		if (vehiculo instanceof Autobus autobus) {
			vehiculoCopiado = new Autobus(autobus);
		}
		if (vehiculo instanceof Furgoneta furgoneta) {
			vehiculoCopiado = new Furgoneta(furgoneta);
		}
		
		return vehiculoCopiado;
		
	}
	
	public abstract int getFactorPrecio();

	public static Vehiculo getVehiculoConMatricula(String matricula) {
		Vehiculo vehiculo = null;
	
//		if (matricula == null) {
//			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
//		}
	
		
//			vehiculo = new Vehiculo("Rolls-Royce", "1111BBB", 2000,  matricula);
//		
//		if (vehiculo instanceof Turismo turismo) {
//			vehiculo = new Turismo("Rolls-Royce", "1111BBB", 2000, matricula);
//			// Rolls-Royce, 1111BBB, 2000, matricula
//		}
//		if (vehiculo instanceof Autobus autobus) {
//			vehiculo = new Autobus("Bus Iberia", "S 517 HD", 20, matricula);
//		}
//		if (vehiculo instanceof Furgoneta furgoneta) {
//			vehiculo = new Furgoneta("Mercedes Benz", "camper", 350, 7, matricula);
//		}
		
		vehiculo = new Turismo("Rolls-Royce", "1111BBB", 2000, matricula);
	
		return vehiculo;
	}

	public String getMarca() {
		return marca;
	}

	private void setMarca(String marca) {
		if (marca == null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		}
		if (!marca.matches(ER_MARCA)) {
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
		}
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	private void setModelo(String modelo) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		if (modelo.isBlank()) {
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		}
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	private void setMatricula(String matricula) {
		if (matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}
		if (!matricula.matches(ER_MATRICULA)) {
			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
		}
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vehiculo))
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}


//	@Override
//	public int hashCode() {
//		return Objects.hash(marca, modelo, matricula);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (!(obj instanceof Vehiculo))
//			return false;
//		Vehiculo other = (Vehiculo) obj;
//		return Objects.equals(marca, other.marca) && Objects.equals(modelo, other.modelo)
//				&& Objects.equals(matricula, other.matricula);
//	}
	
	
	
	
	
	

}
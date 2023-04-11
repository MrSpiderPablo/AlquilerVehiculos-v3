package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {
	static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static final int PRECIO_DIA = 20;

	private LocalDate fechaAlquiler;

	private LocalDate fechaDevolucion;

	private Cliente cliente;

	private Vehiculo vehiculo;

	public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler) {

		setCliente(cliente);
		setVehiculo(vehiculo);
		setFechaAlquiler(fechaAlquiler);

	}

	public Alquiler(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}

//		setFechaAlquiler(alquiler.getFechaAlquiler());
//		setFechaDevolucion(alquiler.getFechaDevolucion());
//		setCliente(new Cliente(alquiler.getCliente()));
//		setTurismo(new Turismo(alquiler.getTurismo()));
		
		fechaAlquiler = alquiler.getFechaAlquiler();
		fechaDevolucion = alquiler.getFechaDevolucion();
		cliente = new Cliente(alquiler.getCliente());
		vehiculo = Vehiculo.copiar(alquiler.getVehiculo());
	}

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	private void setFechaAlquiler(LocalDate fechaAlquiler) {
		if (fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		}

//		if (fechaAlquiler.isBefore(LocalDate.now())) 
//		{
//			throw new IllegalArgumentException("ERROR: La fecha de alquiler debe ser actual.");
//		}

		if (fechaAlquiler.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}

	public Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	private void setVehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: El vehículo no puede ser nulo.");
		}
		this.vehiculo = vehiculo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {

		if (fechaDevolucion == null) {
			throw new NullPointerException("La fecha de devolución no puede ser nula.");
		}
		if (fechaDevolucion.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		this.fechaDevolucion = fechaDevolucion;
	}

	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {

		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}

		if (fechaDevolucion.isBefore(fechaAlquiler) || (fechaDevolucion.equals(fechaAlquiler))) {
			throw new IllegalArgumentException(
					"ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}

		if (this.fechaDevolucion != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}

		setFechaDevolucion(fechaDevolucion);

	}

	public int getPrecio() {
		
		int precio = 0;
		
		int numDias = 0;
		
		if (this.fechaDevolucion == null) {
			
			precio = 0;
		} else 
		{
			 numDias = (int) ChronoUnit.DAYS.between(fechaAlquiler, fechaDevolucion);
			precio = (PRECIO_DIA + vehiculo.getFactorPrecio()) * numDias;
		}
		
		
		
		return precio;
//		if (this.fechaDevolucion == null) {
//
//			return 0;
//		}
//		return 0;

		
//		int numDias = (int) ChronoUnit.DAYS.between(fechaAlquiler, fechaDevolucion);
//
//		int factorCilindrada = turismo.getCilindrada() / 10;
//
//		return ((PRECIO_DIA + factorCilindrada) * numDias);

	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, fechaDevolucion, vehiculo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Alquiler))
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(fechaDevolucion, other.fechaDevolucion) && Objects.equals(vehiculo, other.vehiculo);
	}

	@Override
	public String toString() {
		
		String cadena;
		
		if (fechaDevolucion == null) {
			cadena = String.format("%s <---> %s, %s - Aún no devuelto (0€)", cliente, vehiculo, fechaAlquiler.format(FORMATO_FECHA));
		} else 
		{
			cadena = String.format("%s <---> %s, %s - %s (29€)", cliente, vehiculo, fechaAlquiler.format(FORMATO_FECHA), fechaDevolucion.format(FORMATO_FECHA));
		}
		
		return cadena;
	
	}
//	@Override
//	public String toString() {
//		if (fechaDevolucion == null) {
//			return String.format("%s <---> %s, %s - Aún no devuelto (0€)", cliente, turismo,
//					fechaAlquiler.format(FORMATO_FECHA));
//		}
//		return String.format("%s <---> %s, %s - %s (29€)", cliente, turismo, fechaAlquiler.format(FORMATO_FECHA),
//				fechaDevolucion.format(FORMATO_FECHA));
//	}
	
	

}

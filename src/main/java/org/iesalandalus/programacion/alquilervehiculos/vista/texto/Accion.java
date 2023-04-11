package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import javax.naming.OperationNotSupportedException;

public enum Accion {
	SALIR("Salir") {
		@Override
		public void ejecutar() {
		vista.terminar();
			
		}
	},
	INSERTAR_CLIENTE("Insertar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
		vista.insertarCliente();	
			
		}
	},
	INSERTAR_VEHICULO("Insertar vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
		vista.insertarVehiculo();	
			
		}
	},
	INSERTAR_ALQUILER("Insertar alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
		vista.insertarAlquiler();	
			
		}
	},
	BUSCAR_CLIENTE("Buscar cliente") {
		@Override
		public void ejecutar() {
		vista.buscarCliente();	
			
		}
	},
	BUSCAR_VEHICULO("Buscar vehiculo") {
		@Override
		public void ejecutar() {
		vista.buscarVehiculo();
			
		}
	},
	BUSCAR_ALQUILER("Buscar alquiler") {
		@Override
		public void ejecutar() {
			
			
		}
	},
	MODIFICAR_CLIENTE("Modificar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
		vista.modificarCliente();	
			
		}
	},
	DEVOLVER_ALQUILER_CLIENTE("Devolver alquiler cliente") {
		@Override
		public void ejecutar() {
			
			
		}
	},
	DEVOLVER_ALQUILER_VEHICULO("Devolver alquiler vehiculo") {
		@Override
		public void ejecutar() {
			
			
		}
	},
	BORRAR_CLIENTE("Borrar cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
		vista.borrarCliente();	
			
		}
	},
	BORRAR_VEHICULO("Borrar vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
		vista.borrarVehiculo();	
		
			
		}
	},
	BORRAR_ALQUILER("Borrar alquiler") {
		@Override
		public void ejecutar() {
			
			
		}
	},
	LISTAR_CLIENTES("Listar clientes") {
		@Override
		public void ejecutar() {
		vista.listarClientes();	
			
		}
	},
	LISTAR_VEHICULOS("Listar vehiculos") {
		@Override
		public void ejecutar() {
		vista.listarVehiculos();	
			
		}
	},
	LISTAR_ALQUILERES("Listar alquileres") {
		@Override
		public void ejecutar() {
		vista.listarAlquileres();	
			
		}
	},
	LISTAR_ALQUILERES_CLIENTE("Listar alquileres cliente") {
		@Override
		public void ejecutar() {
		vista.listarAlquileresCliente();	
			
		}
	},
	LISTAR_ALQUILERES_VEHICULO("Listar alquileres vehiculo") {
		@Override
		public void ejecutar() {
		vista.listarAlquileresVehiculo();
			
		}
	},
	MOSTRAR_ESTADISTICAS_MENSUALES("Mostrar estadísticas mensuales") {
		@Override
		public void ejecutar() {
		vista.mostrarEstadisticasMensualesTipoVehiculo();	
			
		}
	};
	
	private static VistaTexto vista;
	private String texto;
	
	
	private Accion(String texto) {
		this.texto = texto;
	}
	
	protected static void setVista(VistaTexto vistaTexto) {
		vista = vistaTexto;
			
	}
	
	public abstract void ejecutar() throws OperationNotSupportedException;
	
	private static boolean esOrdinalValido(int ordinal) {
		return (ordinal < Accion.values().length || ordinal > Accion.values().length);
		
	}
	
	public static Accion get(int ordinal) {
		return Accion.values()[ordinal];
		
	}
	
	@Override
	public String toString() {
		int ordinal = 0;
		
		return String.format("%s (%d)", texto, ordinal);
		
	}
	
}

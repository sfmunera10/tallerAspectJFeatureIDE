package taller.cajero;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Clase que representa el Banco. Contiene una colección de cuentas
 */
public class Banco {

	// mapa con las cuentas. la llave es el número de la cuenta
	private List<Cuenta> cuentas = new ArrayList<>();
	
	/**
	 * Constructor sin parámetros
	 */
	public Banco() { }
	
	
	/**
	 * Retorna un listado con las cuentas
	 * @return listado con las cuentas del Banco-
	 */
	public Collection<Cuenta> getCuentas() {
		return cuentas;
	}
	
	/**
	 * Busca una cuenta por el número.  
	 * Retorna una cuenta, si la encuentra, o null, si no la encuentra 
	 * @param numero número de la cuenta a buscar
	 * @return	instancia de cuenta con ese número, null si no existe
	 */
	public Cuenta buscarCuenta(String numero) {
		Cuenta c = null;
		for(Cuenta cuenta:cuentas) {
			if(cuenta.getNumero().equals(numero)) {
				c = cuenta;
				break;
			}
		}
		return c;
	}
	
	/**
	 * Busca una cuenta por el número de cédula.  
	 * Retorna una cuenta, si la encuentra, o null, si no la encuentra 
	 * @param numero número de la cuenta a buscar
	 * @return	instancia de cuenta con ese número de cédula, null si no existe
	 */
	public Cuenta buscarCuentaPorCedula(String numeroCedula) {
		Cuenta c = null;
		for(Cuenta cuenta:cuentas) {
			if(cuenta.getCedula().equals(numeroCedula)) {
				c = cuenta;
				break;
			}
		}
		return c;
	}
	
	/**
	 * agrega una cuenta al banco
	 * @param cuenta Cuenta a agregar al banco
	 */
	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}
}
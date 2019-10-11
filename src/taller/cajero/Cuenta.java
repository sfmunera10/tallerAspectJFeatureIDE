package taller.cajero;

public class Cuenta {

	private String cedula;
	private String numero;
	private String clave;
	private long saldo;
	
	// constructor que recibe la clave y el saldo de la cuenta
	public Cuenta(String numero, String cedula, String clave, long saldo) {
		this.cedula = cedula;
		this.numero = numero;
		this.clave  = clave;
		this.saldo  = saldo;
	}
	
	//obtiene la cédula asosiada con la cuenta
	public String getCedula() {
		return cedula;
	}
	
	// obtiene el número de la cuenta
	public String getNumero() {
		return numero;
	}
	
	// obtiene el número de la cuenta
	public String getClave() {
		return clave;
	}
	
	// obtiene el valor del saldo
	public long getSaldo() {
		return saldo;
	}
	
	// cambia la clave de la cuenta
	public void cambiarClave(String claveAnterior, String claveNueva) throws Exception {
		
		// cambia la clave si la clave anterior es correcta
		if (this.clave.equals(claveAnterior)) {
			this.clave = claveNueva;
		}
	}
	
	// hace una consignación
	public void consignar(long valor) throws Exception {
		
		// solo se hace la consignación si el valor es mayor que cero
		if (valor < 0) {
			throw new Exception("No se puede consignar un valor negativo");
		}
		this.saldo += valor;
	}
	
	// hace un retiro
	public void retirar(long valor) throws Exception {
		
		// solo se hace el retiro si el valor es mayor que cero
		// y el valor es mayor que el saldo actual
		if (valor < 0) {
			throw new Exception("No se puede retirar un valor negativo");
		}
		//Opción saldo reducido
		if((this.saldo > 200000 && this.saldo - valor < 200000) || this.saldo <= 200000) {
			throw new Exception("La cuenta no puede quedar con un saldo menor a $ 200000 COP");
		}
		if (valor > this.saldo) {
			throw new Exception("No se puede retirar un valor mayor al saldo");
		}
		this.saldo -= valor;
	}
}

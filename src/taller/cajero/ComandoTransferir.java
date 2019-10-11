package taller.cajero;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Comando usado para transferir dinero entre cuentas
 */
public class ComandoTransferir implements Comando {

	@Override
	public String getNombre() {
		return "Transferir dinero";
	}

	@SuppressWarnings("resource")
	@Override
	public StringBuilder ejecutar(Banco contexto, String numero) throws Exception {
		System.out.println();
		System.out.println("Transferencia de Dinero");
		System.out.println("-----------------------");
		System.out.println();
		
		// la clase Console no funciona bien en Eclipse
		Scanner console = new Scanner(System.in);			
		Cuenta cuentaOrigen = contexto.buscarCuenta(numero);
		if (cuentaOrigen == null) {
			throw new Exception("No existe cuenta con el número " + numero);
		}
		System.out.println("Número de cuenta origen: "+numero);
		System.out.println("Ingrese el número de cuenta destino");
		String numeroCuentaDestino = console.nextLine();
		
		Cuenta cuentaDestino = contexto.buscarCuenta(numeroCuentaDestino);
		if (cuentaDestino == null) {
			throw new Exception("No existe cuenta con el número " + numeroCuentaDestino);
		}
		
		System.out.println("Ingrese el valor a transferir");
		String valor = console.nextLine();
	
		try {
			// se retira primero y luego se consigna
			// si no se puede retirar, no se hace la consignación
			long valorNumerico = Long.parseLong(valor);
			cuentaOrigen.retirar(valorNumerico);
			cuentaDestino.consignar(valorNumerico);
			System.out.println("Su saldo actual es de: $ " + cuentaOrigen.getSaldo() + " COP");
		} catch (NumberFormatException e) {
			throw new Exception("Valor a transferir no válido : " + valor);
		}
		StringBuilder ans = new StringBuilder();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		ans.append("Transferencia de [$"+ valor +" COP], realizado por la cuenta [" + cuentaOrigen.getNumero() + "] con cédula asociada [" + cuentaOrigen.getCedula()+"]"
		+" a la cuenta [" + cuentaDestino.getNumero() + "] con cédula asociada [" + cuentaDestino.getCedula()+"].");
		ans.append("\nFecha de realización: "+formatter.format(date));
		
		return ans;
	}
}
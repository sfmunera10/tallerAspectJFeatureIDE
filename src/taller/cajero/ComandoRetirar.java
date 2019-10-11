package taller.cajero;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Comando usado para retirar dinero
 */
public class ComandoRetirar implements Comando {

	@Override
	public String getNombre() {
		return "Retirar dinero";
	}

	@SuppressWarnings("resource")
	@Override
	public StringBuilder ejecutar(Banco contexto, String numero) throws Exception {
		System.out.println();
		System.out.println("Retiro de Dinero");
		System.out.println("----------------");
		System.out.println();
		
		// la clase Console no funciona bien en Eclipse
		Scanner console = new Scanner(System.in);			
		
		Cuenta cuenta = contexto.buscarCuenta(numero);
		if (cuenta == null) {
			throw new Exception("No existe cuenta con el número " + numero);
		}
		System.out.println("Número de cuenta: "+numero);
		System.out.println("Ingrese el valor a retirar");
		String valor = console.nextLine();
	
		try {
			long valorNumerico = Long.parseLong(valor);
			cuenta.retirar(valorNumerico);
			System.out.println("Su saldo actual es de: $ " + cuenta.getSaldo() + " COP");
		
		} catch (NumberFormatException e) {
			throw new Exception("Valor a retirar no válido : " + valor);
		}
		StringBuilder ans = new StringBuilder();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		ans.append("Retiro de [$"+ valor +" COP], realizado por la cuenta [" + cuenta.getNumero() + "] con cédula asociada [" + cuenta.getCedula()+"].");
		ans.append("\nFecha de realización: "+formatter.format(date));
		
		return ans;
	}
}

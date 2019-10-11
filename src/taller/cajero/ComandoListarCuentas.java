package taller.cajero;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Comando usado para listar las cuentas 
 */
public class ComandoListarCuentas implements Comando {

	@Override
	public String getNombre() {
		return "Listar Cuentas";
	}

	@Override
	public StringBuilder ejecutar(Banco contexto, String numero) throws Exception {
		System.out.println("\nListar Cuentas");
		System.out.println("---------------------");
		System.out.println();
		for (Cuenta cuenta : contexto.getCuentas()) {
			System.out.println("\tCuenta "+cuenta.getNumero() + " : $ " + cuenta.getSaldo() + "COP");
		}
		StringBuilder ans = new StringBuilder();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		ans.append("Listar cuentas, realizada por la cuenta de administrador.");
		ans.append("\nFecha de realización: "+formatter.format(date));
		listarCuentas();
		return ans;
	}

	public StringBuilder listarCuentas() {
		return new StringBuilder("Listado de Cuentas");
	}
}
package taller.cajero;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ComandoConsultarSaldo implements Comando{

	@Override
	public String getNombre() {
		return "Consultar Saldo";
	}

	@Override
	public StringBuilder ejecutar(Banco contexto, String numero) throws Exception {
		System.out.println();
		System.out.println("Consultar Saldo");
		System.out.println("---------------");
		System.out.println();
		Cuenta cuenta = contexto.buscarCuenta(numero);
		System.out.println("Su saldo actual es de: $ " + cuenta.getSaldo() + " COP");
		StringBuilder ans = new StringBuilder();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		ans.append("Consulta de saldo realizada por la cuenta [" + cuenta.getNumero() + "] con cédula asociada [" + cuenta.getCedula()+"].");
		ans.append("\nFecha de realización: "+formatter.format(date));
		
		return ans;
	}
}
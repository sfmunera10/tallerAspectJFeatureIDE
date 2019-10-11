package taller.cajero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simulador de un Cajero de Banco
 */
public class Cajero {
	
	/**
	 * Programa principal
	 * @param args parámetros de línea de comandos. Son ignorados por el programa.
	 */
	public static void main(String[] args) {
		
		// crea el banco
		Banco banco = new Banco();
		Cuenta sesion = null;
		
		// crea unas cuentas, para la prueba
		banco.agregarCuenta(new Cuenta("1","1234567890","clave",1000000));
		banco.agregarCuenta(new Cuenta("2","0123456789","clave",2000000));
		banco.agregarCuenta(new Cuenta("3","9876543210","clave",3000000));
		
		// crea los comandos que se van a usar en la aplicación
		List<Comando> comandos = cargaComandos();
		
		// Ciclo del Programa
		// ==================
		System.out.println();
		System.out.println("===========================");
		System.out.println(" Cajero Automático ATMSoft ");
		System.out.println("===========================");
		
		Scanner console = new Scanner(System.in);	
		
		while(true){
			System.out.println();
			System.out.println("Por favor ingrese el número de cédula asociado a su cuenta:");
			System.out.println("O ingrese X para salir");
			System.out.println("----------------------");
			System.out.println();
					
			String valorIngresado1 = console.nextLine();
			Cuenta cuentaEncontrada = banco.buscarCuentaPorCedula(valorIngresado1);
			if(valorIngresado1.equalsIgnoreCase("X")){
				System.out.println("Gracias por usar ATMSoft. ¡Vuelva pronto!");
				console.close();
				System.exit(0);
			}else if(cuentaEncontrada != null) {
				sesion = cuentaEncontrada;
				break;
			}else {
				System.err.println("No existe una cuenta con la información ingresada. Por favor inténtelo de nuevo");
				System.out.println();
			}
		}
		System.out.println();
		while(true){
			System.out.println("Por favor ingrese la clave asociada a su cuenta:");
			System.out.println("O ingrese X para salir");
			System.out.println("----------------------");
			System.out.println();
			String clave = console.nextLine();
			if(clave.equalsIgnoreCase("X")){
				System.out.println("Gracias por usar ATMSoft. ¡Vuelva pronto!");
				console.close();
				System.exit(0);
			}else if(sesion.getClave().equals(clave)) {
				break;
			}else {
				System.err.println("Su clave proporcionada no coincide con la cuenta. Por favor inténtelo de nuevo");
				System.out.println();
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("===========================");
		System.out.println(" Cajero Automático ATMSoft ");
		System.out.println("===========================");
		System.out.println();
		ingresoAlCajero("Ingreso al cajero de la cuenta [" + sesion.getNumero() + "] con la cédula asociada ["+ sesion.getCedula()+"].");
		while(true){
			System.out.println("Bienvenido. Su número de cuenta es: " + sesion.getNumero());
			// muestra los nombres de los comandos
			muestraMenuConComandos(comandos);
			System.out.println("X.- Salir");
			
			// la clase Console no funciona bien en Eclipse			
			String valorIngresado = console.nextLine();
			
			// obtiene el comando a ejecutar
			Comando comandoSeleccionado = retornaComandoSeleccionado(comandos, valorIngresado);
			if (comandoSeleccionado != null) {
				// intenta ejecutar el comando
				try {
					comandoSeleccionado.ejecutar(banco,sesion.getNumero());
				} catch (Exception e) {
					// si hay una excepción, muestra el mensaje
					System.err.println(e.getMessage());
				}
			} 
			// si no se obtuvo un comando, puede ser la opción de salir
			else if (valorIngresado.equalsIgnoreCase("X")) {
				break;
			}
			System.out.println();
		}
		System.out.println("Gracias por usar ATMSoft. ¡Vuelva pronto!");
		console.close();
	}
	
	public static String ingresoAlCajero(String datos) {
		return datos;
	}
	
	// Manejo de los comandos de la aplicación
	// =======================================
	
	// carga los comandos usados en el programa
	private static List<Comando> cargaComandos() {
		// crea los comandos que se van a usar en la aplicación
		List<Comando> comandos = new ArrayList<>();
		
		comandos.add(new ComandoConsultarSaldo());
		comandos.add(new ComandoRetirar());
		comandos.add(new ComandoConsignar());
		comandos.add(new ComandoTransferir());
		comandos.add(new ComandoListarCuentas());

		return comandos;
	}
	
	// Muestra el listado de comandos, para mostrar un menú al usuario
	// muestra el índice en el arreglo de comandos y el nombre del comando
	private static void muestraMenuConComandos(List<Comando> comandos) {
		
		// muestra los nombres de los comandos 
		for (int i=0; i < comandos.size(); i++) {
			System.out.println(i + ".-" + comandos.get(i).getNombre());
		}
	}
	
	// dado el texto ingresado por el usuario, retorna el comando correspondiente
	// retorna null si el texto no es un número o no existe ningún comando con ese índice
	private static Comando retornaComandoSeleccionado(List<Comando> comandos, String valorIngresado) {
		Comando comandoSeleccionado = null;
		
		// el valorIngresado es un número ?
		if (valorIngresado.matches("[0-9]")) {			
			int valorSeleccionado = Integer.valueOf(valorIngresado);
			// es un índice válido para la lista de comandos
			if (valorSeleccionado < comandos.size()) {
				comandoSeleccionado = comandos.get(valorSeleccionado);
			}
		}	
		return comandoSeleccionado;
	}
}
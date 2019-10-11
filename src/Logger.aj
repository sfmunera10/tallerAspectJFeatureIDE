import taller.cajero.Banco;

public aspect Logger {
	
	private StringBuilder registroAud = new StringBuilder("\nREGISTRO DE AUDITORÍA\n");
	
	/*
	 * Aspecto de registro de auditoría ///////////////////////////////////
	*/
	// - método main
	pointcut metodoMain() : call(String ingresoAlCajero(String));
	
	//método listarCuentas
	pointcut metodoListarCuentas(): call(StringBuilder listarCuentas());
	
	// - métodos de las operaciones en el cajero
	pointcut metodoEjecutarComando() : call(StringBuilder ejecutar(Banco, String));
	  
	// ejecución al retornar el método
	after() returning(StringBuilder resultado): metodoEjecutarComando() {
		registroAud.append("Acción realizada (AspectJ): "+ resultado + "\n");
	}
	// ejecución al retornar el método
	after() :metodoListarCuentas() {
		System.out.println(registroAud + "\n");
	}
	after() returning(String resultado): metodoMain() {
		registroAud.append("Retornando Registro de auditoría de inicio de sesión (AspectJ)\n");
		registroAud.append("Acción realizada (AspectJ): "+ resultado + "\n");
	}
}
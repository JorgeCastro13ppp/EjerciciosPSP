package ejemplos;

class Buzon {
	private int mensaje;
	private boolean disponible = false;

	public synchronized void enviar(int valor) {
// TODO: esperar si ya hay un mensaje disponible
		mensaje = valor;
		disponible = true;
// TODO: avisar
	}

	public synchronized int recibir() {
// TODO: esperar si NO hay mensaje
		disponible = false;
// TODO: avisar
		return mensaje;
	}
}

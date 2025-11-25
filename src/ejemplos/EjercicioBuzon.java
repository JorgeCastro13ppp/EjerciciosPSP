package ejemplos;

public class EjercicioBuzon {
	public static void main(String[] args) {
		Buzon b = new Buzon();
		Thread productor = new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				b.enviar(i);
				System.out.println("Enviado: " + i);
			}
		});
		Thread consumidor = new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				int recib = b.recibir();
				System.out.println("Recibido: " + recib);
			}
		});
		productor.start();
		consumidor.start();
	}
}

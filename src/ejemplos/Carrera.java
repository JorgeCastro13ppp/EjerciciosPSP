package ejemplos;

public class Carrera {
	public static void main(String[] args) throws InterruptedException {
		Contador c = new Contador();
		Runnable tarea = () -> {
		for (int i = 0; i < 10000; i++) c.incrementar();
		};
		Thread t1 = new Thread(tarea);
		Thread t2 = new Thread(tarea);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Resultado final: " + c.getValor());
	}
}

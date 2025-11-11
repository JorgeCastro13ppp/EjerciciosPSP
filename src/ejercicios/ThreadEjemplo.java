package ejercicios;

public class ThreadEjemplo extends Thread{
	
	public ThreadEjemplo(String nombre) {
		super(nombre); //Asigna nombre al hilo
	}
	
	// Método que puede ser utilizado para mostrar el inicio de cada hilo
	public void mostrarInicio() {
		System.out.println("Hilo: "+getName()+" ha comenzado.");
	}
	
	//Método para mopdificar el run solo si lo necesitamos, lo sobreescribe
	@Override
	public void run() {
		System.out.println("Hilo base: "+getName()+" ejecutando run ()");
	}
}

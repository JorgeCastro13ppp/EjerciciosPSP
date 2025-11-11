package ejercicios;


public class MainThreadEjemplo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Creando hilos...");
		
		HijaThreadEjemplo hilo1 = new HijaThreadEjemplo("Hilo 1");
		HijaThreadEjemplo hilo2 = new HijaThreadEjemplo("Hilo 2");
		HijaThreadEjemplo hilo3 = new HijaThreadEjemplo("Hilo 3");
		
		// Las prioridades van de 1 (MIN_PRIORITY) a 10 (MAX_PRIORITY).
        // No garantizan el orden de ejecución, pero influyen en cómo
        // el planificador de hilos del sistema operativo los atiende.
        hilo3.setPriority(Thread.MAX_PRIORITY); // prioridad más alta
        hilo2.setPriority(Thread.MIN_PRIORITY); // prioridad más baja
        
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		
		System.out.println("🐵 Hilo principal MAIN ejecutándose...");
		
	 
		
	}

}

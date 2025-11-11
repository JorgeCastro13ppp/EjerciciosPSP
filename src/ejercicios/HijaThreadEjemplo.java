package ejercicios;

public class HijaThreadEjemplo extends ThreadEjemplo{
	
	public HijaThreadEjemplo (String nombre) {
		super(nombre);
	}

	@Override
	public void run() {
		//Llamamos al método de la clase base
		mostrarInicio();
		
		//Comportamiento del hilo hijo
		for(int i=0;i<=5;i++) {
			System.out.println("Hilo "+ getName()+"-> iteración: "+i);
			try {
				Thread.sleep(500); // pausa para visualizar mejor la concurrencia
			}catch(InterruptedException e) {
				System.out.println(getName()+" interrumpido.");
			}
		}
		
		System.out.println("Hilo -> "+getName()+" ha terminado");
	}

}

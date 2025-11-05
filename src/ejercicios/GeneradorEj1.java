package ejercicios;

import java.util.Random;

/**
 * GeneradorEj1
 * - Genera números aleatorios entre 1 y 100.
 * - Imprime únicamente el número por línea (esto facilita que el detector lo parsee).
 * - Hace una pausa entre números (Thread.sleep) para no saturar la salida.
 */

public class GeneradorEj1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		
		try {
			while(true) {
				int numeroG = r.nextInt(100) +1; // rango 1-100
				System.out.println(numeroG); // SOLO imprimimos el número (una línea)
				Thread.sleep(250); 			//Espera 0,25s entre números
			}
		}catch(InterruptedException e) {
			// Si se interrumpe el hilo (por ejemplo al llamar a destroy()), capturamos y terminamos. 
			e.printStackTrace();
		}
	}

}

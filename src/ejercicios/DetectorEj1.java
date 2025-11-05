package ejercicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * DetectorEj1
 * - Recibe como argumento el número objetivo (args[0]).
 * - Lee líneas desde la entrada estándar (System.in), espera números por línea.
 * - Si detecta que un número leído coincide con el objetivo, imprime mensaje y termina con código 0.
 */

public class DetectorEj1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Comprobación de argumentos, necesitamos un argumento con el número objetivo
		if( args.length==0) {
			//Mensaje de error a stdrr, señalamos error por argumentos
			System.err.println("Falta el número objetivo.");
			//Salimos con código de error 1 (error por uso incorrecto)
			System.exit(1);
		}
		
		//Convertimos el argumento 0 a entero; si el argumento no es un número , lanzará NumberFormatException
		int objetivo = Integer.parseInt(args[0]);
		String linea; //Variable para almacenar cada lñinea leída
		
        // Usamos try-with-resources para garantizar el cierre del BufferedReader al finalizar
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			
			//Leemos línea a línea hasta EOF (null) o hasta que se haga System.exit(0)
			while((linea = br.readLine())!=null) {
				
				try {
					//Intenamos parsear la línea a entero (ignorando espacios)
					int numeroB = Integer.parseInt(linea.trim());
					//Mostramos por consola el número que estamos procesando
					System.out.println("Leyenndo número: "+numeroB);
					
					//Si coincide con el objetivo, imprimimos y salimos con código 0, éxito
					if(numeroB == objetivo) {
						System.out.println("NÚMERO DETECTADO: "+ numeroB);
						System.exit(0); //Termina el proceso detector
					}
				}catch(NumberFormatException u) {
					//Si la línea no es un entero válido. aquí lo capturamos.
					//Actualmente imprimimos la traza (stack tarce). En entornos reales
					//podríamos ignorarla o escribir un log mejor y más amigable
					u.printStackTrace();
				}
				
			}
			
		}catch( Exception e) {
			//Capturamos errores de IO y cualquier excepción (por seguridad)
			e.printStackTrace();
		}
	}

}

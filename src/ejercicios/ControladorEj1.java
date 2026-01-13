package ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * ControladorEj1
 * --------------------------------------------------------------
 * Clase principal que actúa como "controlador" o proceso padre.
 *
 * - Solicita un número objetivo al usuario.
 * - Lanza dos procesos hijos:
 *    1. GeneradorEj1 → produce números aleatorios (1–100).
 *    2. DetectorEj1  → recibe los números y busca el número objetivo.
 * - Conecta la salida del Generador con la entrada del Detector.
 * - Cuando el Detector detecta el número (sale con código 0),
 *   el Controlador detiene el Generador y termina.
 * --------------------------------------------------------------
 */

public class ControladorEj1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Crear un Scanner para leer la entrada del usuario
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce un número del 1-100:");
		int numO = sc.nextInt(); //Número objetivo a detectar
		sc.nextLine(); //Limpiar el buffer
		sc.close(); //Cerramos el Scanner
		
		try {
			// ----------------------------------------------------------
            //Obtener la ruta del ejecutable 'java' del sistema actual.
            //Esto garantiza que el subproceso use la misma versión
            //de Java que ejecuta este programa (por ejemplo, Java 11).
            // ----------------------------------------------------------
			//Debemos cambiar compilador java al 11 | propierties -> java compiler
			
			String javaPath = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
			
			//PASO 1: Configurar y lanzar el proceso generador
			
			ProcessBuilder pbGenerador = new ProcessBuilder("java","ejercicios.GeneradorEj1");
			
			pbGenerador.redirectErrorStream(true); //Unificar salida y error
			pbGenerador.directory(new File("bin")); //Directorio donde están las clases compiladas
			Process generador = pbGenerador.start(); //Iniciar proceso generador
			
			//PASO 2: Configurar y lanzar el proceso detector
			//Pasamos el número objetivo como argumento del proceso.
			ProcessBuilder pbDetector = new ProcessBuilder("java","ejercicios.DetectorEj1",String.valueOf(numO));
						
			pbDetector.redirectErrorStream(true);
			pbDetector.directory(new File("bin"));
			Process detector = pbDetector.start(); // Iniciar proceso detector
			
			//PASO 3:  CREAR UN HILO (THREAD) PARA ENLAZAR GENERADOR → DETECTOR
			
			Thread pipe = new Thread(()->{
                // Lector de la salida del Generador				
				try(BufferedReader salidaGenerador = new BufferedReader(new InputStreamReader(generador.getInputStream()));
	                // Escritor hacia la entrada del Detector
					BufferedWriter entradaDetector = new BufferedWriter(new OutputStreamWriter(detector.getOutputStream()))	) {
					
					String linea;
                    // Leer cada línea generada y reenviarla al detector
					while((linea=salidaGenerador.readLine())!=null) {
						System.out.println("[Generador] Número generado: "+linea);
						entradaDetector.write(linea);
						entradaDetector.newLine(); // salto de línea obligatorio
						entradaDetector.flush();	//enviamos la línea al instante
					}
					
				}catch(IOException i) {
					i.printStackTrace();
				}
				
			});
			pipe.start(); // Lanzamos el hilo de conexión
			
			// PASO 4: HILO PARA LEER Y MOSTRAR LA SALIDA DEL DETECTOR
			
			Thread lecturaDetector = new Thread(() -> {
                try (BufferedReader lector = new BufferedReader(new InputStreamReader(detector.getInputStream()))) {
                    String linea;
                    while ((linea = lector.readLine()) != null) {
                        System.out.println("[Detector] " + linea);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            lecturaDetector.start(); // Lanzamos el hilo de lectura

			// PASO 5: ESPERAR A QUE EL DETECTOR TERMINE
            
			int codigoSalida = detector.waitFor(); // bloquea hasta que el detector finaliza
			System.out.println("Detector finalizado: "+codigoSalida);
			
			
			//PASO 6:  DETENER EL GENERADOR SI SIGUE VIVO
			if(generador.isAlive()) {
				generador.destroy(); // Terminar proceso generador
				System.out.println("Generador detenido.");
			}
			System.out.println("Procesos terminados correctamente.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
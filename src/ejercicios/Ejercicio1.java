package ejercicios;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Usamos la clase Runtime para ejecutar un comando del sistema operativo
			//En windows deberíamos usar cmd o dir
            Process proceso = Runtime.getRuntime().exec("ls");
            //Después capturamos la salida estándar del proceso, lo que usualmente veríamos en consola
            java.io.BufferedReader lector = new java.io.BufferedReader(//BufferedReader permite leer la línea
            		//InputStreamReader convierte los bytes en texto 
                    new java.io.InputStreamReader(proceso.getInputStream()));
            //Aquí utilizamos un bucle de lectura
            //Va leyendo y mostrando cada línea que el proceso imprime
            //Esto muestra el listado de archivos del directorio actual
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            lector.close();
            //Esta línea pausa el hilo actual hasta que el proceso finaliza
            //así evitamos terminar el programa antes de que el proceso acabe
            proceso.waitFor();
            //Esto devuelve el código de salida, si es 0 todo correcto, otro número algo salió mal
            System.out.println("Código de salida: " + proceso.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

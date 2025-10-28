package ejercicios;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Ejercicio3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Aquí intentamos ejecutar el archivo fuente .java 
            Process proceso = Runtime.getRuntime().exec("java src/ejercicios/Ejercicio1.java");
            java.io.BufferedReader lector = new java.io.BufferedReader( //Esto sirve para capturar la salida del proceso, lo que aparecería en consola normalmente
                    new java.io.InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = lector.readLine()) != null) { //Lee y muestra cada línea por pantalla
                System.out.println(linea);
            }
            lector.close();
            //Esto bloquea la ejecución del programa hasta que el proceso hijo (la ejecución de Ejercicio1.java)
            proceso.waitFor();
            System.out.println("Código de salida: " + proceso.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

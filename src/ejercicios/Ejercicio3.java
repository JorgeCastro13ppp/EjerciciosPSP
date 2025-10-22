package ejercicios;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Ejercicio3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            Process proceso = Runtime.getRuntime().exec("java src/Ejercicios/Ej_1.java");
            java.io.BufferedReader lector = new java.io.BufferedReader(
                    new java.io.InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            lector.close();
            proceso.waitFor();
            System.out.println("Código de salida: " + proceso.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

package ejercicios;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Ejercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  try {
	            Process proceso = Runtime.getRuntime().exec("java --version");
	            java.io.BufferedReader lector = new java.io.BufferedReader(
	                    new java.io.InputStreamReader(proceso.getInputStream()));
	            String linea;
	            BufferedWriter bw=new BufferedWriter(new FileWriter("salida.txt"));
	            while ((linea = lector.readLine()) != null) {
	                bw.write(linea);
	                bw.newLine();
	                System.out.println(linea);
	            }
	            bw.close();
	            lector.close();
	            proceso.waitFor();
	            System.out.println("Código de salida: " + proceso.exitValue());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}

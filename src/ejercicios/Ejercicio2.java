package ejercicios;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Ejercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  try {
			  //Lanzamos un proceso que ejecuta el comando especificado
	
	            Process proceso = Runtime.getRuntime().exec("java --version");
	            java.io.BufferedReader lector = new java.io.BufferedReader( //BR Permite leer las líneas
	            		//ISR permite convertir bytes a caracteres legibles
	                    new java.io.InputStreamReader(proceso.getInputStream()));
	            String linea;
	            //Creamos o sobreescribimos el archivo en el directorio del proyecto
	            //Usamos BW para escribir texto de manera eficiente
	            BufferedWriter bw=new BufferedWriter(new FileWriter("salida.txt"));
	            //Por cada línea que el proceso imprime se escribe en el txt y se muestra en consola
	            while ((linea = lector.readLine()) != null) {
	                bw.write(linea);
	                bw.newLine();
	                System.out.println(linea);
	            }
	            bw.close();
	            lector.close();
	            //Cerramos los flujos para liberar recursos
	            //Usamos waitFor para detener el programa hasta que el proceso termina
	            proceso.waitFor();//Si sale 0 todo ok, sino mal
	            System.out.println("Código de salida: " + proceso.exitValue());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}

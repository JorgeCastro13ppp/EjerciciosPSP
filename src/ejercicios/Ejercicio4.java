package ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ejercicio4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Definimos una lista de comandos 
		//Cada comando es a su vez una lista de cadenas (nombre del comando + argumentos)
		List<List<String>> comandos = Arrays.asList(Arrays.asList("ping", "-c", "3", "www.google.com"),
                Arrays.asList("java", "--version"), Arrays.asList("ls"));
		//Se crea una lista donde se guardarán los objetos en ejecución
        List<Process> procesos = new ArrayList<>();
        try {
        	//Se recorre cada comando
            for (List<String> comando : comandos) {
            	//Se crea un processBuilder para abrir el proceso
                ProcessBuilder pb = new ProcessBuilder(comando);
                pb.redirectErrorStream(true); //Mezcla la salida de error con la salida estándar
                //Así no tenemos que leer dos streams diferentes
                procesos.add(pb.start());//pb.start lanza el proceso y devuelve un objeto Process, que se guarda en la lista
            }
            //Cada proceso tiene su propia salida InputStream
            //Si intentaramos leerlas todas en el mismo hilo el programa se bloquearía esperando a que uno termine
            for (Process proceso : procesos) { 
            	//Por eso se lanza un hilo por proceso
            	//Cada hilo lee y muestra la salida de su proceso en paralelo, sin bloquear a los demás 
                new Thread(() -> {
                    try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                        String linea;
                        while ((linea = lector.readLine()) != null) {
                            System.out.println(linea);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

            }
            //Este bucle espera a que todos los procesos terminen antes de continuar
            //Es como un "join" para procesos
            for (Process proceso : procesos) {
                proceso.waitFor();
            }
            System.out.println("Todos los procesos han terminado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

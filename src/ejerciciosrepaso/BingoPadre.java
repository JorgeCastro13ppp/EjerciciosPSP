package ejerciciosrepaso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Proceso padre del bingo.
 * Se encarga de lanzar los procesos hijo (jugadores),
 * generar los números del bingo y finalizar la simulación
 * cuando uno de los hijos gana.
 */
public class BingoPadre {

    public static void main(String[] args) throws Exception {

        // Número de procesos hijo (jugadores)
        int N = 3;

        // Lista para almacenar los procesos hijo
        List<Process> jugadores = new ArrayList<>();

        // Lista de flujos de salida para enviar números a cada hijo
        List<PrintWriter> escritores = new ArrayList<>();

        // Se obtiene el classpath actual para poder lanzar procesos Java correctamente
        String classpath = System.getProperty("java.class.path");

        System.out.println("COMIENZA EL BINGO (PROCESOS)");

        // Creación y lanzamiento de los procesos hijo
        for (int i = 1; i <= N; i++) {

            // ProcessBuilder para lanzar un proceso Java independiente
            ProcessBuilder pb = new ProcessBuilder(
                    "java", "-cp", classpath,
                    "src/ejerciciosrepaso/JugadorProceso.java",
                    String.valueOf(i) // identificador del jugador
            );

            // Se inicia el proceso hijo
            Process p = pb.start();

            // Se guarda el proceso para poder comprobar su estado más adelante
            jugadores.add(p);

            // Se crea un PrintWriter asociado a la entrada estándar del hijo
            // para enviarle los números del bingo
            escritores.add(new PrintWriter(p.getOutputStream(), true));

            // BufferedReader para leer todo lo que el hijo escriba por consola
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream())
            );

            // Hilo independiente que se encarga de mostrar por pantalla
            // la salida de cada proceso hijo
            new Thread(() -> {
                try {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println(linea);
                    }
                } catch (IOException ignored) {
                    // No se hace nada si ocurre una excepción de lectura
                }
            }).start();
        }

        // Generador de números aleatorios
        Random r = new Random();

        // Conjunto para almacenar los números ya sacados y evitar repeticiones
        Set<Integer> sacados = new HashSet<>();

        // Variable de control para saber cuándo hay un ganador
        boolean hayGanador = false;

        // Bucle principal del bingo
        while (!hayGanador) {

            int numero;

            // Se genera un número aleatorio entre 1 y 99 que no haya salido antes
            do {
                numero = r.nextInt(99) + 1;
            } while (sacados.contains(numero));

            // Se marca el número como ya sacado
            sacados.add(numero);

            // Se muestra el número sacado por el padre
            System.out.println("Padre saca: " + numero);

            // Se envía el número a todos los procesos hijo
            for (PrintWriter pw : escritores) {
                pw.println(numero);
            }

            // Pausa para que la salida sea visible y ordenada
            Thread.sleep(1200);

            // Se comprueba si algún proceso hijo ha terminado (ha ganado)
            for (Process p : jugadores) {
                if (!p.isAlive()) {
                    hayGanador = true;
                    break;
                }
            }
        }

        // Se finalizan todos los procesos hijo que sigan activos
        for (Process p : jugadores) {
            p.destroy();
        }

        System.out.println("BINGO FINALIZADO");
    }
}

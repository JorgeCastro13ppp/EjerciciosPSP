package ejerciciosrepaso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Proceso hijo que representa a un jugador del bingo.
 * Cada jugador tiene su propio cartón y recibe los números
 * enviados por el proceso padre.
 */
public class JugadorProceso {

    public static void main(String[] args) throws Exception {

        // Identificador del jugador recibido como argumento
        int id = Integer.parseInt(args[0]);

        // Flujo de entrada para recibir números desde el proceso padre
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in)
        );

        // Flujo de salida para enviar mensajes al proceso padre
        // Se activa el auto-flush para que los mensajes se envíen inmediatamente
        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(System.out), true
        );

        // Generación del cartón del jugador
        Set<Integer> carton = generarCarton();

        // Se muestra el cartón inicial del jugador
        out.println("Jugador " + id + " cartón: " + carton);

        String linea;

        // Bucle que espera números enviados por el proceso padre
        while ((linea = in.readLine()) != null) {

            // Conversión del número recibido a entero
            int numero = Integer.parseInt(linea);

            // Si el número está en el cartón, se elimina
            if (carton.remove(numero)) {

                // Se informa de que el jugador tiene ese número
                out.println("Jugador " + id + " tiene el " + numero);

                // Pausa para mejorar la visualización de la salida
                Thread.sleep(300);

                // Si el cartón queda vacío, el jugador ha ganado
                if (carton.isEmpty()) {

                    // Se notifica al proceso padre que este jugador es el ganador
                    out.println("GANADOR: Jugador " + id);

                    // Pequeña pausa para que el mensaje se muestre correctamente
                    Thread.sleep(1500);

                    // Finalización del proceso hijo
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Genera un cartón de bingo con 15 números aleatorios
     * entre 1 y 99 sin repetir.
     */
    private static Set<Integer> generarCarton() {

        Random r = new Random();
        Set<Integer> s = new HashSet<>();

        // Se generan números hasta completar el cartón
        while (s.size() < 15) {
            s.add(r.nextInt(99) + 1);
        }

        return s;
    }
}

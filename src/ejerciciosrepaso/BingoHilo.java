package ejerciciosrepaso;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Clase principal que actúa como bombo del bingo.
 * Genera los números y notifica a los jugadores.
 */
public class BingoHilo {

    public static void main(String[] args) throws Exception {

        int N = 3;

        JugadorHilo[] jugadores = new JugadorHilo[N];

        // Creación y arranque de los hilos jugadores
        for (int i = 0; i < N; i++) {
            jugadores[i] = new JugadorHilo(i + 1);
            jugadores[i].start();
        }

        Random r = new Random();
        Set<Integer> sacados = new HashSet<>();

        System.out.println("COMIENZA EL BINGO (HILOS)");

        // Bucle principal del bingo
        while (!JugadorHilo.hayGanador()) {

            int numero;

            // Generar número sin repetir
            do {
                numero = r.nextInt(99) + 1;
            } while (sacados.contains(numero));

            sacados.add(numero);

            synchronized (JugadorHilo.getLock()) {
                System.out.println("Bombo saca: " + numero);
                JugadorHilo.setNumero(numero);

                // Notificar a todos los jugadores
                JugadorHilo.getLock().notifyAll();
            }

            Thread.sleep(1000);
        }

        System.out.println("BINGO FINALIZADO");
    }
}

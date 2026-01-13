package ejerciciosrepaso;

/**
 * Hilo que representa a un jugador del bingo.
 */
public class JugadorHilo extends Thread {

    private int id;
    private CartonHilo carton;

    // Variables compartidas
    private static int numeroActual;
    private static boolean hayGanador = false;
    private static final Object lock = new Object();

    public JugadorHilo(int id) {
        this.id = id;
        this.carton = new CartonHilo();
        System.out.println("Jugador " + id + " cartón: " + carton);
    }

    @Override
    public void run() {

        while (true) {

            synchronized (lock) {
                try {
                    // El jugador espera a que el bingo saque un número
                    lock.wait();
                } catch (InterruptedException e) {
                    return;
                }

                // Si ya hay ganador, el hilo termina
                if (hayGanador) {
                    return;
                }

                // Comprueba si tiene el número
                if (carton.marcarNumero(numeroActual)) {
                    System.out.println("Jugador " + id + " tiene el " + numeroActual);

                    // Si el cartón queda vacío, gana
                    if (carton.estaVacio()) {
                        System.out.println("GANADOR: Jugador " + id);
                        hayGanador = true;
                        lock.notifyAll();
                        return;
                    }
                }
            }
        }
    }

    /**
     * Establece el número que todos los jugadores deben comprobar.
     */
    public static void setNumero(int numero) {
        numeroActual = numero;
    }

    /**
     * Devuelve si ya hay un ganador.
     */
    public static boolean hayGanador() {
        return hayGanador;
    }

    /**
     * Devuelve el objeto de sincronización.
     */
    public static Object getLock() {
        return lock;
    }
}

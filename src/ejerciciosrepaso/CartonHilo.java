package ejerciciosrepaso;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Clase que representa un cartón de bingo.
 * Contiene una lista de números pendientes.
 */
public class CartonHilo {

    private Set<Integer> numeros;

    public CartonHilo() {
        numeros = new HashSet<>();
        generarCarton();
    }

    /**
     * Genera 15 números aleatorios entre 1 y 99 sin repetir.
     */
    private void generarCarton() {
        Random r = new Random();
        while (numeros.size() < 15) {
            numeros.add(r.nextInt(99) + 1);
        }
    }

    /**
     * Comprueba si el número está en el cartón.
     * Si está, lo elimina.
     */
    public boolean marcarNumero(int numero) {
        return numeros.remove(numero);
    }

    /**
     * Indica si el cartón está completo.
     */
    public boolean estaVacio() {
        return numeros.isEmpty();
    }

    @Override
    public String toString() {
        return numeros.toString();
    }
}

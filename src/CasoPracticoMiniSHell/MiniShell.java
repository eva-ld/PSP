package CasoPracticoMiniSHell;

import java.io.IOException;
import java.util.Scanner;

public class MiniShell {
     static void main(String[] args) {
        // Leemos los programas que ha introducido por pantalla el usuario (Paso 1)
        Scanner teclado = new Scanner(System.in);

        System.out.println("Primer programa: ");
        String programa1 = teclado.nextLine();

        System.out.println("Segundo programa: ");
        String programa2 = teclado.nextLine();

        teclado.close();

        try {

            // Creamos los ProcessBuilder para cada programa (Paso 2)
            ProcessBuilder pb1 = new ProcessBuilder(programa1);
            ProcessBuilder pb2 = new ProcessBuilder(programa2);

            // Iniciamos los procesos a la vez (sin usar waitFor entre ellos para que se inicialicen a la vez) (Paso 3)
            Process proceso1 = pb1.start();
            Process proceso2 = pb2.start();

            System.out.println("Los procesos han sido iniciados con éxito");

            // Obtenemos el ProcessHandle identificativo de cada proceso y mostramos su PID y estado (Paso 4)
            ProcessHandle handle1 = proceso1.toHandle();
            ProcessHandle handle2 = proceso2.toHandle();

            System.out.println("PID 1: " + handle1.pid());
            System.out.println("Activo 1: " + handle1.isAlive());

            System.out.println("PID 2: " + handle2.pid());
            System.out.println("Activo 2: " + handle2.isAlive());

            // Esperarmos a que finalicen los dos procesos (Paso 5)
            int codigo1 = proceso1.waitFor();
            int codigo2 = proceso2.waitFor();

            //Mostramos por pantalla el código de salida obtenido por cada proceso (Paso 6)
            System.out.println("\n--- Resultados finales ---");
            System.out.println("Código de salida 1: " + codigo1);
            System.out.println("Código de salida 2: " + codigo2);


        } catch (IOException e) {

            // Usaremos un try-catch por si se producen errores, el programa no existe o falla al iniciar (Paso 7)
            System.out.println("No se ha podido iniciar uno de los procesos: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
     }
}
